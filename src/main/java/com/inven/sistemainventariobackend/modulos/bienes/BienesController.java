package com.inven.sistemainventariobackend.modulos.bienes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bienes")
public class BienesController {

    private final BienesService bienesService;

    @Autowired
    public BienesController(BienesService bienesService) {
        this.bienesService = bienesService;
    }

    @GetMapping
    public List<Bien> obtenerTodos() {
        return bienesService.obtenerTodosLosBienes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bien> obtenerPorId(@PathVariable Long id) {
        Bien bien = bienesService.obtenerPorId(id);
        if (bien != null) {
            return ResponseEntity.ok(bien);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Bien> crearBien(@RequestBody Bien bien) {
        Bien nuevo = bienesService.guardarBien(bien);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bien> actualizarBien(@PathVariable Long id, @RequestBody Bien bien) {
        Bien existente = bienesService.obtenerPorId(id);
        if (existente != null) {
            bien.setId(id); // Asegura que JPA lo trate como actualización
            return ResponseEntity.ok(bienesService.guardarBien(bien));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBien(@PathVariable Long id) {
        Bien existente = bienesService.obtenerPorId(id);
        if (existente != null) {
            bienesService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

