package com.inven.sistemainventariobackend.modulos.inventario.service;

import com.inven.sistemainventariobackend.modulos.inventario.dto.InventarioRequest;
import com.inven.sistemainventariobackend.modulos.inventario.dto.InventarioResponse;
import com.inven.sistemainventariobackend.modulos.inventario.model.Inventario;
import com.inven.sistemainventariobackend.modulos.inventario.model.InventarioItem;
import com.inven.sistemainventariobackend.modulos.inventario.repository.InventarioItemRepository;
import com.inven.sistemainventariobackend.modulos.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvService {

    private final InventarioRepository repository;
    private final InventarioItemRepository itemRepository;

    public InvService(InventarioRepository repository, InventarioItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public InventarioResponse crear(InventarioRequest request) {
        Inventario inventario = new Inventario();
        inventario.setResponsable(request.responsable);
        inventario.setOficina(request.oficina);
        inventario.setTipoInventario(request.tipoInventario);
        inventario.setTipoRegistro(request.tipoRegistro);
        inventario.setFecha(request.fecha);

        Inventario guardado = repository.save(inventario);
        return mapToResponse(guardado);
    }

    public List<InventarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ Nuevo método para obtener items por inventarioId
    public List<InventarioItem> listarItemsPorInventarioId(Long inventarioId) {
        return itemRepository.findByInventario_Id(inventarioId);
    }

    private InventarioResponse mapToResponse(Inventario inventario) {
        InventarioResponse r = new InventarioResponse();
        r.id = inventario.getId();
        r.responsable = inventario.getResponsable();
        r.oficina = inventario.getOficina();
        r.tipoInventario = inventario.getTipoInventario();
        r.tipoRegistro = inventario.getTipoRegistro();
        r.fecha = inventario.getFecha();
        return r;
    }
}
