package com.inven.sistemainventariobackend.modulos.ubigeo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UbigeoService {

    private List<Departamento> departamentos;
    private List<Provincia> provincias;
    private List<Distrito> distritos;

    public UbigeoService() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream dptoStream = getClass().getClassLoader().getResourceAsStream("ubigeo/ubigeo_peru_2016_departamentos.json");
            InputStream provStream = getClass().getClassLoader().getResourceAsStream("ubigeo/ubigeo_peru_2016_provincias.json");
            InputStream distStream = getClass().getClassLoader().getResourceAsStream("ubigeo/ubigeo_peru_2016_distritos.json");

            departamentos = mapper.readValue(dptoStream, new TypeReference<>() {});
            provincias = mapper.readValue(provStream, new TypeReference<>() {});
            distritos = mapper.readValue(distStream, new TypeReference<>() {});

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar archivos JSON de ubigeo", e);
        }
    }

    public List<String> getDepartamentos() {
        return departamentos.stream()
                .map(Departamento::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getProvincias(String departamentoNombre) {
        // Buscar ID del departamento por nombre
        Optional<Departamento> dep = departamentos.stream()
                .filter(d -> d.getName().equalsIgnoreCase(departamentoNombre))
                .findFirst();

        if (dep.isEmpty()) return List.of();

        String depId = dep.get().getId();

        return provincias.stream()
                .filter(p -> p.getDepartment_id().equals(depId))
                .map(Provincia::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getDistritos(String departamentoNombre, String provinciaNombre) {
        // Buscar ID del departamento
        Optional<Departamento> dep = departamentos.stream()
                .filter(d -> d.getName().equalsIgnoreCase(departamentoNombre))
                .findFirst();

        if (dep.isEmpty()) return List.of();
        String depId = dep.get().getId();

        // Buscar ID de la provincia dentro del departamento
        Optional<Provincia> prov = provincias.stream()
                .filter(p -> p.getName().equalsIgnoreCase(provinciaNombre) && p.getDepartment_id().equals(depId))
                .findFirst();

        if (prov.isEmpty()) return List.of();
        String provId = prov.get().getId();

        return distritos.stream()
                .filter(d -> d.getDepartment_id().equals(depId) && d.getProvince_id().equals(provId))
                .map(Distrito::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}


