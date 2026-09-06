package com.example.corestock.service;

import com.example.corestock.entity.Usuario;
import com.example.corestock.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementación de UserDetailsService que le indica a Spring Security
 * cómo buscar un usuario (por username) para el proceso de login.
 *
 * Al estar anotada con @Service, Spring Security la detecta automáticamente
 * como el UserDetailsService a usar durante la autenticación, junto con el
 * PasswordEncoder (BCrypt) ya definido en SecurityConfig.
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return usuario;
    }
}
