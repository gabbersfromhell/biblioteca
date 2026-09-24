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

    public Libro actualizarLibro(Long id, Libro datosLibro) {
        Libro libro = buscarPorId(id);
        libro.setIsbn(datosLibro.getIsbn());
        libro.setTitulo(datosLibro.getTitulo());
        libro.setAutor(datosLibro.getAutor());
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        Libro libro = buscarPorId(id);
        libroRepository.delete(libro);
    }
}
