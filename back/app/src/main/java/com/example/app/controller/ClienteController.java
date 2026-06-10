package com.example.app.controller;

import com.example.app.model.Cliente;
import com.example.app.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> listar() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable int id) {

        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {

        return repository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {

        repository.deleteById(id);
    }
}