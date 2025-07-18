package com.inven.sistemainventariobackend.modulos.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<Personal> listarTodo() {
        return personalRepository.findAll();
    }

    public Personal guardar(Personal p) {
        p.setCodigo(generarCodigo());
        return personalRepository.save(p);
    }

    public Personal actualizar(Long id, Personal nuevo) {
        Personal actual = personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
        nuevo.setId(id);
        return personalRepository.save(nuevo);
    }

    public void eliminar(Long id) {
        personalRepository.deleteById(id);
    }

    private String generarCodigo() {
        long total = personalRepository.count();
        return String.format("%03d", total + 1); // 001, 002, ...
    }
}

