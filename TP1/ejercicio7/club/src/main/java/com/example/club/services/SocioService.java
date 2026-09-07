package com.example.club.services;


import com.example.club.entities.Socio;
import com.example.club.repositories.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @Transactional(readOnly = true)
    public List<Socio> listarTodos() {
        return socioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Socio> buscarPorNombreODni(String query) {
        // Asumiendo un método personalizado en el repositorio
        return socioRepository.findByNombreODni(query);
    }

    @Transactional(readOnly = true)
    public Socio buscarPorId(Long id) throws Exception {
        return socioRepository.findById(id).orElseThrow(() -> new Exception("Socio no encontrado"));
    }
}
