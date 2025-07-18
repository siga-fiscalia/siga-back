package com.inven.sistemainventariobackend.modulos.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = "*")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public List<Personal> listar() {
        return personalService.listarTodo();
    }

    @PostMapping
    public ResponseEntity<Personal> guardar(@RequestBody Personal p) {
        return ResponseEntity.ok(personalService.guardar(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> actualizar(@PathVariable Long id, @RequestBody Personal p) {
        return ResponseEntity.ok(personalService.actualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        personalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

