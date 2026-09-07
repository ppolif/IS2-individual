package com.example.club.services;


import com.example.club.dto.DTOs;
import com.example.club.entities.Familia;
import com.example.club.repositories.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FamiliaService {

    @Autowired
    private FamiliaRepository familiaRepository;

    @Transactional
    public void crearFamilia(DTOs.FamiliaDTO dto) {
        Familia familia = new Familia();
        familia.setApellidoFamilia(dto.getApellidoFamilia());
        familiaRepository.save(familia);
    }

    @Transactional(readOnly = true)
    public List<Familia> listarFamilias() {
        return familiaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Familia buscarPorId(Long id) throws Exception {
        return familiaRepository.findById(id).orElseThrow(() -> new Exception("Familia no encontrada"));
    }

    @Transactional
    public void modificarFamilia(Long id, DTOs.FamiliaDTO dto) throws Exception {
        Familia familia = buscarPorId(id);
        familia.setApellidoFamilia(dto.getApellidoFamilia());
        familiaRepository.save(familia);
    }

    @Transactional
    public void eliminarFamilia(Long id) {
        familiaRepository.deleteById(id);
    }
}
