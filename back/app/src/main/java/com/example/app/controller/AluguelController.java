package com.example.app.controller;

import com.example.app.model.Aluguel;
import com.example.app.repository.AluguelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelRepository repository;

    @GetMapping
    public List<Aluguel> listar() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluguel buscar(@PathVariable int id) {

        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Aluguel cadastrar(@RequestBody Aluguel aluguel) {

        aluguel.setValorFinal(aluguel.calcularValorFinal());

        return repository.save(aluguel);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {

        repository.deleteById(id);
    }
}