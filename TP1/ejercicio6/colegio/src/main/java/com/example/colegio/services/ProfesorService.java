package com.example.colegio.services;


import com.example.colegio.dto.CambioClaveDTO;
import com.example.colegio.dto.RegistroProfesorDTO;
import com.example.colegio.entities.Profesor;
import com.example.colegio.entities.Usuario;
import com.example.colegio.enums.Rol;
import com.example.colegio.repositories.ProfesorRepository;
import com.example.colegio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Service: Informa a Spring que contiene lógica de negocio[cite: 5].
 */
@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * @Transactional: Abre y cierra transacciones de BD automáticamente[cite: 5].
     */
    @Transactional
    public void registrarProfesor(RegistroProfesorDTO dto) {
        Usuario u = new Usuario();
        u.setCorreo(dto.getCorreo());
        u.setClave(passwordEncoder.encode(dto.getClave()));
        u.setRol(Rol.PROFESOR);

        Profesor p = new Profesor();
        p.setNombre(dto.getNombre());
        p.setApellido(dto.getApellido());
        p.setDni(dto.getDni());
        p.setSexo(dto.getSexo());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setTitulo(dto.getTitulo());
        p.setUsuario(u);

        profesorRepo.save(p);
        enviarCorreoBienvenida(u.getCorreo(), p.getNombre());
    }

    @Transactional
    public void cambiarClave(String correo, CambioClaveDTO dto) throws Exception {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // El método matches valida si la clave ingresada coincide con el hash guardado[cite: 2]
        if (!passwordEncoder.matches(dto.getClaveActual(), usuario.getClave())) {
            throw new Exception("La contraseña actual es incorrecta");
        }

        // Si es correcta, encriptamos la nueva y actualizamos[cite: 2]
        usuario.setClave(passwordEncoder.encode(dto.getNuevaClave()));
        usuarioRepository.save(usuario);
    }

    private void enviarCorreoBienvenida(String correo, String nombre) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(correo);
        message.setSubject("Bienvenido al Sistema Académico");
        message.setText("Hola " + nombre + ", tu cuenta ha sido creada exitosamente.");
        mailSender.send(message);
    }

    @Transactional
    public void modificarProfesor(Long id, RegistroProfesorDTO dto) throws Exception {
        Profesor p = profesorRepo.findById(id)
                .orElseThrow(() -> new Exception("Profesor no encontrado"));

        p.setNombre(dto.getNombre());
        p.setApellido(dto.getApellido());
        p.setDni(dto.getDni());
        p.setSexo(dto.getSexo());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setTitulo(dto.getTitulo());

        // Como el registro ya existe en la BD, save() hace un UPDATE automático[cite: 9].
        profesorRepo.save(p);
    }

    @Transactional
    public void eliminarProfesor(Long id) {
        // Ejecuta un DELETE FROM tabla WHERE id = ?[cite: 16].
        // Esto también borrará al Usuario asociado gracias a la configuración en cascada de JPA.
        profesorRepo.deleteById(id);
    }
}
