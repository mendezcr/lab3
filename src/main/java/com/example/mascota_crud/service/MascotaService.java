package com.example.mascota_crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mascota_crud.model.Mascota;
import com.example.mascota_crud.repository.MascotaRepository;

@Service
public class MascotaService {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> findAll(){
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> findById(Long id){
        return mascotaRepository.findById(id);
    }

    public Mascota save (Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    public void deleteById(Long id){
        mascotaRepository.deleteById(id);
    }

    public void eliminarMascotaPorId(Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Mascota con ID " + id + " no encontrada.");
        }
    }

}
