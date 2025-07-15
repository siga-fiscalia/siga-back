package com.inven.sistemainventariobackend.modulos.centrocosto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroController {

    @Autowired
    private CentroService centroService;

    @PostMapping
    public ResponseEntity<Centro> crear(@RequestBody Centro centro) {
        return ResponseEntity.ok(centroService.crearCentro(centro));
    }

    @GetMapping
    public ResponseEntity<List<Centro>> listar() {
        return ResponseEntity.ok(centroService.listarCentros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Centro> obtenerPorId(@PathVariable Long id) {
        Centro centro = centroService.obtenerPorId(id);
        if (centro != null) {
            return ResponseEntity.ok(centro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        centroService.eliminarCentro(id);
        return ResponseEntity.noContent().build();
    }
}
