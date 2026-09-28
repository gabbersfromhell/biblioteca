package biblioteca.controller;

import biblioteca.dto.EjemplarRequest;
import biblioteca.dto.EjemplarResponse;
import biblioteca.service.EjemplarService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {

    private final EjemplarService ejemplarService;

    public EjemplarController(EjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
    }

    @PostMapping
    public EjemplarResponse guardarEjemplar(
            @Valid @RequestBody EjemplarRequest ejemplarRequest) {

        return ejemplarService.guardarEjemplar(ejemplarRequest);
    }

    @GetMapping
    public List<EjemplarResponse> listarEjemplares() {

        return ejemplarService.listarEjemplares();
    }

    @GetMapping("/{id}")
    public EjemplarResponse buscarPorId(@PathVariable Long id) {

        return ejemplarService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public EjemplarResponse actualizarEjemplar(
            @PathVariable Long id,
            @Valid @RequestBody EjemplarRequest ejemplarRequest) {

        return ejemplarService.actualizarEjemplar(id, ejemplarRequest);
    }

    @DeleteMapping("/{id}")
    public void eliminarEjemplar(@PathVariable Long id) {

        ejemplarService.eliminarEjemplar(id);
    }
}