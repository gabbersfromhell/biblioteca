package biblioteca.service;

import biblioteca.dto.LibroRequest;
import biblioteca.dto.LibroResponse;
import biblioteca.entity.Libro;
import biblioteca.exception.LibroNoEncontradoException;
import biblioteca.repository.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Page<LibroResponse> listarLibros(
            int pagina,
            int tamanio,
            String direccion) {

        if (pagina < 0) {
            throw new IllegalArgumentException(
                    "La página no puede ser negativa"
            );
        }

        if (tamanio <= 0) {
            throw new IllegalArgumentException(
                    "El tamaño debe ser mayor que 0"
            );
        }

        if (tamanio > 100) {
            throw new IllegalArgumentException(
                    "El tamaño máximo de página es 100"
            );
        }

        if (!direccion.equalsIgnoreCase("asc")
                && !direccion.equalsIgnoreCase("desc")) {

            throw new IllegalArgumentException(
                    "La dirección debe ser 'asc' o 'desc'"
            );
        }

        Sort orden;

        if (direccion.equalsIgnoreCase("desc")) {
            orden = Sort.by("titulo").descending();
        } else {
            orden = Sort.by("titulo").ascending();
        }

        Pageable pageable = PageRequest.of(
                pagina,
                tamanio,
                orden
        );

        Page<Libro> libros = libroRepository.findAll(pageable);

        return libros.map(this::convertirAResponse);
    }

    public LibroResponse guardarLibro(LibroRequest libroRequest) {

        Libro libro = new Libro();

        libro.setIsbn(libroRequest.getIsbn());
        libro.setTitulo(libroRequest.getTitulo());
        libro.setAutor(libroRequest.getAutor());

        Libro libroGuardado = libroRepository.save(libro);

        return convertirAResponse(libroGuardado);
    }

    public List<LibroResponse> guardarLibros(List<LibroRequest> librosRequest) {

        return librosRequest.stream()
                .map(libroRequest -> {

                    Libro libro = new Libro();

                    libro.setIsbn(libroRequest.getIsbn());
                    libro.setTitulo(libroRequest.getTitulo());
                    libro.setAutor(libroRequest.getAutor());

                    Libro libroGuardado = libroRepository.save(libro);

                    return convertirAResponse(libroGuardado);
                })
                .toList();
    }

    public LibroResponse buscarPorId(Long id) {

        Libro libro = buscarEntidadPorId(id);

        return convertirAResponse(libro);
    }

    public LibroResponse actualizarLibro(Long id, LibroRequest libroRequest) {

        Libro libro = buscarEntidadPorId(id);

        libro.setIsbn(libroRequest.getIsbn());
        libro.setTitulo(libroRequest.getTitulo());
        libro.setAutor(libroRequest.getAutor());

        Libro libroActualizado = libroRepository.save(libro);

        return convertirAResponse(libroActualizado);
    }

    public void eliminarLibro(Long id) {

        Libro libro = buscarEntidadPorId(id);

        libroRepository.delete(libro);
    }

    private Libro buscarEntidadPorId(Long id) {

        return libroRepository.findById(id)
                .orElseThrow(() ->
                        new LibroNoEncontradoException("Libro no encontrado"));
    }

    private LibroResponse convertirAResponse(Libro libro) {

        LibroResponse response = new LibroResponse();

        response.setId(libro.getId());
        response.setIsbn(libro.getIsbn());
        response.setTitulo(libro.getTitulo());
        response.setAutor(libro.getAutor());

        return response;
    }

    public List<LibroResponse> buscarPorTitulo(String titulo) {

        return libroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<LibroResponse> buscarPorAutor(String autor) {

        return libroRepository.findByAutorContainingIgnoreCase(autor)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<LibroResponse> buscarPorTituloYAutor(String titulo, String autor) {

        return libroRepository
                .findByTituloContainingIgnoreCaseAndAutorContainingIgnoreCase(titulo, autor)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public List<LibroResponse> buscarPorTituloOAutor(String titulo, String autor) {

        return libroRepository
                .findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(titulo, autor)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }
}