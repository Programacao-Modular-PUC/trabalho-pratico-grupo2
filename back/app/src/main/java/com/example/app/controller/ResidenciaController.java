package com.example.app.controller;

import com.example.app.model.Residencia;
import com.example.app.repository.ResidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residencias")
@CrossOrigin(origins = "http://localhost:5173")
public class ResidenciaController {

    @Autowired
    private ResidenciaRepository repository;

    @GetMapping
    public List<Residencia> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Residencia> cadastrar(@RequestBody Residencia residencia) {
        Residencia nova = repository.save(residencia);
        return new ResponseEntity<>(nova, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}