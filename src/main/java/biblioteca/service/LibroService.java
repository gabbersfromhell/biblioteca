package biblioteca.service;

import biblioteca.entity.Libro;
import biblioteca.exception.LibroNoEncontradoException;
import biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado"));
    }
}
