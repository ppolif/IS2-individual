package com.example.corestock.repository;

import com.example.corestock.entity.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Capa de Repositorio: Interfaz de Spring Data JPA.
 * @Repository indica que es un componente de acceso a datos que traduce excepciones SQL.
 */
@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, String> {

}
