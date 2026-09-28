package biblioteca.dto;

import jakarta.validation.constraints.NotNull;

public class EjemplarRequest {

    @NotNull(message = "El número de ejemplar es obligatorio")
    private Integer numero;

    @NotNull(message = "El libro es obligatorio")
    private Long libroId;

    public Integer getNumero() { return numero; }

    public void setNumero(Integer numero) { this.numero = numero; }

    public Long getLibroId() { return libroId; }

    public void setLibroId(Long libroId) { this.libroId = libroId; }
}