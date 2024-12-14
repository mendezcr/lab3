package com.example.mascota_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mascota_crud.model.Mascota;
import com.example.mascota_crud.service.MascotaService;

@Controller
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    // Nuevo mapeo para la página de inicio
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @RequestMapping("/mascotas")
    public String getAllMascotas(Model model){
        List<Mascota> mascotas = mascotaService.findAll();
        model.addAttribute("mascotas", mascotas);
        return "mascotas";
    }

    @GetMapping("/mascotas/nueva") 
    public String addUserForm(Model model) {
       model.addAttribute("mascota", new Mascota());
       return "crear-mascota";
    }

    @PostMapping("/mascotas")
    public String createMascota(@ModelAttribute Mascota mascota){
        mascotaService.save(mascota);
        return "redirect:/mascotas";
    }

    @GetMapping("/mascotas/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaService.findById(id);
        if(mascota.isPresent()){
            model.addAttribute("mascota", mascota.get());
            return "editar-mascota";
        } else {
            return "redirect:/mascotas";
        }
    }

    @PostMapping("/mascotas/actualizar/{id}")
    public String updateMascota(@PathVariable Long id, @ModelAttribute Mascota mascotaDetails){
        Optional<Mascota> optionalMascota = mascotaService.findById(id);
        if (optionalMascota.isPresent()){
            Mascota mascota = optionalMascota.get();
            mascota.setNombre(mascotaDetails.getNombre());
            mascota.setTipo(mascotaDetails.getTipo());
            mascota.setEdad(mascotaDetails.getEdad());
            mascota.setRaza(mascotaDetails.getRaza());
            mascotaService.save(mascota);

            return "redirect:/mascotas";
        } else {
            return "redirect:/mascotas";
        }
    }

    @PostMapping("/mascotas/eliminar/{id}")
    public String Mascota(@PathVariable Long id, Model model) {
        try {
            mascotaService.eliminarMascotaPorId(id);;
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "redirect:/mascotas";
    }

    @GetMapping("/consejos")
    public String consejosNombres() {
        return "consejos-nombres";
    }
}