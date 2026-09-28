package biblioteca.repository;

import biblioteca.entity.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

    boolean existsByLibroIdAndNumero(Long libroId, Integer numero);
    boolean existsByLibroIdAndNumeroAndIdNot(
            Long libroId,
            Integer numero,
            Long id
    );
}