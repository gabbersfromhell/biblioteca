package biblioteca.controller;

import biblioteca.dto.LibroRequest;
import biblioteca.dto.LibroResponse;
import biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // LISTAR LIBROS CON PAGINACIÓN Y ORDENACIÓN
    @GetMapping("/libros")
    public Page<LibroResponse> listarLibros(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "asc") String direccion) {

        return libroService.listarLibros(pagina, tamanio, direccion);
    }

    // CREAR UN LIBRO
    @PostMapping("/libros")
    public LibroResponse guardarLibro(
            @Valid @RequestBody LibroRequest libroRequest) {

        return libroService.guardarLibro(libroRequest);
    }

    // CREAR VARIOS LIBROS
    @PostMapping("/libros/lote")
    public List<LibroResponse> guardarLibros(
            @Valid @RequestBody List<LibroRequest> librosRequest) {

        return libroService.guardarLibros(librosRequest);
    }

    // BUSCAR LIBRO POR ID
    @GetMapping("/libros/{id}")
    public LibroResponse buscarPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    // BUSCAR POR TÍTULO
    @GetMapping("/libros/buscar")
    public List<LibroResponse> buscarPorTitulo(
            @RequestParam String titulo) {

        return libroService.buscarPorTitulo(titulo);
    }

    // BUSCAR POR AUTOR
    @GetMapping("/libros/buscar/autor")
    public List<LibroResponse> buscarPorAutor(
            @RequestParam String autor) {

        return libroService.buscarPorAutor(autor);
    }

    // BUSCAR POR TÍTULO Y AUTOR
    @GetMapping("/libros/buscar/filtro")
    public List<LibroResponse> buscarPorTituloYAutor(
            @RequestParam String titulo,
            @RequestParam String autor) {

        return libroService.buscarPorTituloYAutor(titulo, autor);
    }

    // BUSCAR POR TÍTULO O AUTOR
    @GetMapping("/libros/buscar/filtro-or")
    public List<LibroResponse> buscarPorTituloOAutor(
            @RequestParam String titulo,
            @RequestParam String autor) {

        return libroService.buscarPorTituloOAutor(titulo, autor);
    }

    // ACTUALIZAR LIBRO
    @PutMapping("/libros/{id}")
    public LibroResponse actualizarLibro(
            @PathVariable Long id,
            @Valid @RequestBody LibroRequest libroRequest) {

        return libroService.actualizarLibro(id, libroRequest);
    }

    // ELIMINAR LIBRO
    @DeleteMapping("/libros/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}