package com.example.sistema_usuario.model.persistencia;

import com.example.sistema_usuario.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CAPA DE REPOSITORIO (Data Access)
 * @Repository: Anotación de Spring que indica que este componente gestionará el acceso a la BD.
 * Heredar de JpaRepository nos da métodos CRUD automáticos (save, findById, delete, etc.) sin programar SQL.
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    // Método personalizado de Spring Data JPA.
    // Spring crea automáticamente la consulta SQL: SELECT * FROM usuario WHERE correo = ?
    Optional<Usuario> findByCorreo(String correo);
}
