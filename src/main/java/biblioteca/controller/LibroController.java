package biblioteca.controller;

import biblioteca.entity.Libro;
import biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }

    @PostMapping("/libros")
    public Libro guardarLibro(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }

    @GetMapping("/libros/{id}")
    public Libro buscarPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    @PutMapping("/libros/{id}")
    public Libro actualizarLibro(@PathVariable Long id,
                                 @RequestBody Libro libro) {

        return libroService.actualizarLibro(id, libro);
    }
    @DeleteMapping("/libros/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}