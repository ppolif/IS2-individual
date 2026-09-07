package com.example.club.services;



import com.example.club.dto.DTOs;
import com.example.club.entities.Socio;
import com.example.club.entities.Usuario;
import com.example.club.repositories.SocioRepository;
import com.example.club.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void crearUsuario(DTOs.UsuarioDTO dto) throws Exception {
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new Exception("El correo ya está registrado");
        }

        Usuario u = new Usuario();
        u.setCorreo(dto.getCorreo());
        u.setClave(passwordEncoder.encode(dto.getClave()));
        u.setRol(dto.getRol());

        // Ejecuta un INSERT INTO en la base de datos[cite: 2]
        usuarioRepository.save(u);

        Socio s = new Socio();
        s.setNombre(dto.getNombreSocio());
        s.setApellido(dto.getApellidoSocio());
        s.setDni(dto.getDniSocio());
        s.setUsuario(u);

        socioRepository.save(s);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        // Ejecuta un SELECT * FROM tabla para traer todos los registros de golpe[cite: 2]
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(String id) throws Exception {
        // Ejecuta un SELECT * FROM tabla WHERE id = ?[cite: 2]
        return usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

    @Transactional
    public void actualizarUsuario(String id, DTOs.UsuarioDTO dto) throws Exception {
        Usuario u = buscarPorId(id);
        u.setCorreo(dto.getCorreo());
        if(dto.getClave() != null && !dto.getClave().isEmpty()){
            u.setClave(passwordEncoder.encode(dto.getClave()));
        }
        u.setRol(dto.getRol());
        // Al existir el registro, save ejecuta un UPDATE[cite: 2]
        usuarioRepository.save(u);
    }

    @Transactional
    public void eliminarUsuario(String id) {
        // Ejecuta un DELETE FROM tabla WHERE id = ?[cite: 2]
        usuarioRepository.deleteById(id);
    }
}
