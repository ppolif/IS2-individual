package com.example.club.services;


import com.example.club.dto.DTOs;
import com.example.club.entities.PagoCuota;
import com.example.club.enums.EstadoPago;
import com.example.club.repositories.PagoCuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PagoCuotaService {

    @Autowired
    private PagoCuotaRepository pagoRepository;

    @Autowired
    private SocioService socioService;

    @Transactional
    public void crearPago(DTOs.PagoCuotaDTO dto) throws Exception {
        PagoCuota p = new PagoCuota();
        p.setSocio(socioService.buscarPorId(dto.getIdSocio()));
        p.setMonto(dto.getMonto());
        p.setFormaPago(dto.getFormaPago());
        p.setComprobante(dto.getComprobante());
        p.setEstado(EstadoPago.PAGADA);
        p.setFechaPago(LocalDate.now());
        pagoRepository.save(p);
    }

    @Transactional(readOnly = true)
    public List<PagoCuota> listarTodos() {
        return pagoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PagoCuota buscarPorId(Long id) throws Exception {
        return pagoRepository.findById(id).orElseThrow(() -> new Exception("Pago no encontrado"));
    }

    @Transactional
    public void actualizarPago(Long id, DTOs.PagoCuotaDTO dto) throws Exception {
        PagoCuota p = buscarPorId(id);
        p.setMonto(dto.getMonto());
        p.setFormaPago(dto.getFormaPago());
        p.setComprobante(dto.getComprobante());
        pagoRepository.save(p);
    }

    @Transactional
    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}
