package biblioteca.dto;

public class EjemplarResponse {

    private Long id;
    private Integer numero;
    private Long libroId;
    private String titulo;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getNumero() { return numero; }

    public void setNumero(Integer numero) { this.numero = numero; }

    public Long getLibroId() { return libroId; }

    public void setLibroId(Long libroId) { this.libroId = libroId; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
}