package com.example.corestock.service;

import com.example.corestock.dto.RegistroDTO;
import com.example.corestock.enumeraciones.RolUsuario;
import com.example.corestock.entity.Usuario;
import com.example.corestock.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registrarUsuario(RegistroDTO dto) throws Exception {
        if(!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new Exception("Las contraseñas no coinciden");
        }
        Usuario u = new Usuario();
        u.setUsername(dto.getUsername());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRol(RolUsuario.OPERADOR);
        usuarioRepository.save(u);
    }
}
