package com.inven.sistemainventariobackend.modulos.inventario.controller;

import com.inven.sistemainventariobackend.modulos.inventario.dto.InventarioRequest;
import com.inven.sistemainventariobackend.modulos.inventario.dto.InventarioResponse;
import com.inven.sistemainventariobackend.modulos.inventario.dto.EnumOptionDTO;
import com.inven.sistemainventariobackend.modulos.inventario.enums.TipoRegistro;
import com.inven.sistemainventariobackend.modulos.inventario.model.InventarioItem;
import com.inven.sistemainventariobackend.modulos.inventario.repository.InventarioItemRepository;
import com.inven.sistemainventariobackend.modulos.inventario.service.InvService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventario")
@CrossOrigin
public class InvController {

    private final InvService service;
    private final InventarioItemRepository itemRepository;

    public InvController(InvService service, InventarioItemRepository itemRepository) {
        this.service = service;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public ResponseEntity<InventarioResponse> crear(@RequestBody InventarioRequest request) {
        return ResponseEntity.ok(service.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // ✅ Endpoint para listar tipos de registro
    @GetMapping("/tipos-registro")
    public ResponseEntity<List<EnumOptionDTO>> obtenerTiposRegistro() {
        List<EnumOptionDTO> tipos = Arrays.stream(TipoRegistro.values())
                .map(tipo -> new EnumOptionDTO(tipo.name(), tipo.getLabel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(tipos);
    }

    // ✅ Nuevo endpoint para listar los items de un inventario específico
    @GetMapping("/items/{inventarioId}")
    public ResponseEntity<List<InventarioItem>> listarItemsPorInventario(@PathVariable Long inventarioId) {
        List<InventarioItem> items = itemRepository.findByInventario_Id(inventarioId);
        return ResponseEntity.ok(items);
    }
}



