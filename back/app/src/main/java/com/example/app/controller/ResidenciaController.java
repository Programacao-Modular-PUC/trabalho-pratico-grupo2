package com.example.app.controller;

import com.example.app.model.Residencia;
import com.example.app.repository.ResidenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residencias")
public class ResidenciaController {

    @Autowired
    private ResidenciaRepository repository;

    @GetMapping
    public List<Residencia> listar() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Residencia buscar(@PathVariable int id) {

        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Residencia cadastrar(@RequestBody Residencia residencia) {

        return repository.save(residencia);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {

        repository.deleteById(id);
    }
}