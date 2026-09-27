package biblioteca.dto;

public class LibroResponse {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn;}

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }

    public void setAutor(String autor) { this.autor = autor; }
}
