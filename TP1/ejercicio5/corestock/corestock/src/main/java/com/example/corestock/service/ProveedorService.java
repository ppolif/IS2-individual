package com.example.corestock.service;

import com.example.corestock.dto.ProveedorDTO;
import com.example.corestock.entity.Proveedor;
import com.example.corestock.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Service avisa al framework que la clase tiene un rol de servicio o lógica de negocio[cite: 4].
 */
@Service
public class ProveedorService {

    /**
     * @Autowired implementa la Inyección de Dependencias: Spring construye el repositorio y lo inyecta aquí[cite: 4].
     */
    @Autowired
    private ProveedorRepository proveedorRepository;

    /**
     * @Transactional asegura que si ocurre un error, se haga un rollback automático para no dejar datos inconsistentes[cite: 4].
     */
    @Transactional
    public void crearProveedor(ProveedorDTO dto) throws Exception {
        validar(dto);
        Proveedor p = new Proveedor();
        p.setCuit(dto.getCuit());
        p.setRazonSocial(dto.getRazonSocial());
        proveedorRepository.save(p);
    }

    public void validar(ProveedorDTO dto) throws Exception {
        if (dto.getRazonSocial() == null || dto.getRazonSocial().isEmpty()) {
            throw new Exception("La razón social no puede estar vacía");
        }
    }

    @Transactional
    public void modificarProveedor(Long cuit, ProveedorDTO dto) throws Exception {
        validar(dto);
        Proveedor p = proveedorRepository.findById(cuit)
                .orElseThrow(() -> new Exception("Proveedor no encontrado"));
        p.setRazonSocial(dto.getRazonSocial());
        proveedorRepository.save(p);
    }

    @Transactional
    public void eliminarProveedor(Long cuit) {
        proveedorRepository.deleteById(cuit);
    }

    @Transactional(readOnly = true)
    public List<ProveedorDTO> listarProveedores() {
        return proveedorRepository.findAll().stream().map(p -> {
            ProveedorDTO dto = new ProveedorDTO();
            dto.setCuit(p.getCuit());
            dto.setRazonSocial(p.getRazonSocial());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean existeProveedor(Long cuit) {
        return proveedorRepository.existsById(cuit);
    }

    @Transactional(readOnly = true)
    public ProveedorDTO buscarProveedor(Long cuit) throws Exception {
        Proveedor p = proveedorRepository.findById(cuit)
                .orElseThrow(() -> new Exception("Proveedor no encontrado"));
        ProveedorDTO dto = new ProveedorDTO();
        dto.setCuit(p.getCuit());
        dto.setRazonSocial(p.getRazonSocial());
        return dto;
    }
}