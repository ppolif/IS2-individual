package com.example.club.repositories;

import com.example.club.entities.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {


    @Query("SELECT s FROM Socio s WHERE s.nombre LIKE %:q% OR s.apellido LIKE %:q% OR CAST(s.dni AS string) LIKE %:q%")
    List<Socio> findByNombreODni(@Param("q") String q);
}
