package biblioteca.repository;

import biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutor_NombreContainingIgnoreCase(String autor);

    List<Libro> findByTituloContainingIgnoreCaseAndAutor_NombreContainingIgnoreCase(
            String titulo, String autor);

    List<Libro> findByTituloContainingIgnoreCaseOrAutor_NombreContainingIgnoreCase(
            String titulo, String autor);
}