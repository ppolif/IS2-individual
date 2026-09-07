package com.example.club.repositories;

import com.example.club.entities.PagoCuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoCuotaRepository extends JpaRepository<PagoCuota, Long> {
}
