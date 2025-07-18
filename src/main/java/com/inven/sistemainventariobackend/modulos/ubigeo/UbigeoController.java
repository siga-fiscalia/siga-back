package com.inven.sistemainventariobackend.modulos.ubigeo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubigeo")
@CrossOrigin(origins = "*")
public class UbigeoController {

    private final UbigeoService ubigeoService;

    @Autowired
    public UbigeoController(UbigeoService ubigeoService) {
        this.ubigeoService = ubigeoService;
    }

    @GetMapping("/departamentos")
    public List<String> obtenerDepartamentos() {
        return ubigeoService.getDepartamentos();
    }

    @GetMapping("/provincias")
    public List<String> obtenerProvincias(@RequestParam String departamento) {
        return ubigeoService.getProvincias(departamento);
    }

    @GetMapping("/distritos")
    public List<String> obtenerDistritos(@RequestParam String departamento, @RequestParam String provincia) {
        return ubigeoService.getDistritos(departamento, provincia);
    }
}


