package biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getNumero() { return numero; }

    public void setNumero(Integer numero) { this.numero = numero; }

    public Libro getLibro() { return libro; }

    public void setLibro(Libro libro) { this.libro = libro; }
}