package com.example.mascota_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mascota_crud.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    
}