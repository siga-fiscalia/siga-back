package com.inven.sistemainventariobackend.modulos.ubicacion;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin(origins = "*") // Ajusta el CORS según tu frontend
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    public List<Ubicacion> listar() {
        return ubicacionService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Ubicacion> crear(@Valid @RequestBody Ubicacion ubicacion) {
        return ResponseEntity.ok(ubicacionService.guardar(ubicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> actualizar(@PathVariable Long id, @Valid @RequestBody Ubicacion ubicacion) {
        return ResponseEntity.ok(ubicacionService.actualizar(id, ubicacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ubicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

