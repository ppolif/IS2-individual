package com.example.corestock.repository;

import com.example.corestock.entity.Vigencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VigenciaRepository extends JpaRepository<Vigencia, Long> {}
