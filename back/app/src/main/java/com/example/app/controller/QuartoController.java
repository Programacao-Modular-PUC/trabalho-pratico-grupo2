package com.example.app.controller;

import com.example.app.model.Quarto;
import com.example.app.repository.QuartoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private QuartoRepository repository;

    @GetMapping
    public List<Quarto> listar() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Quarto buscar(@PathVariable int id) {

        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Quarto cadastrar(@RequestBody Quarto quarto) {

        return repository.save(quarto);
    }

    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {

        repository.deleteById(id);
    }
}