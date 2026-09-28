package biblioteca.service;

import biblioteca.dto.EjemplarRequest;
import biblioteca.dto.EjemplarResponse;
import biblioteca.entity.Ejemplar;
import biblioteca.entity.Libro;
import biblioteca.exception.EjemplarDuplicadoException;
import biblioteca.exception.EjemplarNoEncontradoException;
import biblioteca.repository.EjemplarRepository;
import biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final LibroRepository libroRepository;

    public EjemplarService(EjemplarRepository ejemplarRepository,
                           LibroRepository libroRepository) {

        this.ejemplarRepository = ejemplarRepository;
        this.libroRepository = libroRepository;
    }

    public EjemplarResponse guardarEjemplar(EjemplarRequest ejemplarRequest) {

        Libro libro = libroRepository.findById(ejemplarRequest.getLibroId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Libro no encontrado"));

        boolean existe = ejemplarRepository.existsByLibroIdAndNumero(
                ejemplarRequest.getLibroId(),
                ejemplarRequest.getNumero()
        );

        if (existe) {
            throw new EjemplarDuplicadoException(
                    "Ya existe ese número de ejemplar para este libro");
        }

        Ejemplar ejemplar = new Ejemplar();

        ejemplar.setNumero(ejemplarRequest.getNumero());
        ejemplar.setLibro(libro);

        Ejemplar ejemplarGuardado = ejemplarRepository.save(ejemplar);

        return convertirAResponse(ejemplarGuardado);
    }

    public List<EjemplarResponse> listarEjemplares() {

        return ejemplarRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public EjemplarResponse buscarPorId(Long id) {

        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() ->
                        new EjemplarNoEncontradoException(
                                "Ejemplar no encontrado"));

        return convertirAResponse(ejemplar);
    }

    public EjemplarResponse actualizarEjemplar(
            Long id,
            EjemplarRequest ejemplarRequest) {

        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() ->
                        new EjemplarNoEncontradoException(
                                "Ejemplar no encontrado"));

        Libro libro = libroRepository.findById(ejemplarRequest.getLibroId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Libro no encontrado"));

        boolean existe = ejemplarRepository
                .existsByLibroIdAndNumeroAndIdNot(
                        ejemplarRequest.getLibroId(),
                        ejemplarRequest.getNumero(),
                        id
                );

        if (existe) {
            throw new EjemplarDuplicadoException(
                    "Ya existe ese número de ejemplar para este libro");
        }

        ejemplar.setNumero(ejemplarRequest.getNumero());
        ejemplar.setLibro(libro);

        Ejemplar ejemplarActualizado =
                ejemplarRepository.save(ejemplar);

        return convertirAResponse(ejemplarActualizado);
    }

    public void eliminarEjemplar(Long id) {

        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() ->
                        new EjemplarNoEncontradoException(
                                "Ejemplar no encontrado"));

        ejemplarRepository.delete(ejemplar);
    }

    private EjemplarResponse convertirAResponse(Ejemplar ejemplar) {

        EjemplarResponse response = new EjemplarResponse();

        response.setId(ejemplar.getId());
        response.setNumero(ejemplar.getNumero());
        response.setLibroId(ejemplar.getLibro().getId());
        response.setTitulo(ejemplar.getLibro().getTitulo());

        return response;
    }
}