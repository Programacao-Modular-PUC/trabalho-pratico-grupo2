package com.example.app.controller;

import com.example.app.model.Quarto;
import com.example.app.repository.QuartoRepository;
import com.example.app.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
@CrossOrigin(origins = "http://localhost:5173")
public class QuartoController {

    @Autowired
    private QuartoRepository repository;

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public List<Quarto> listar() {
        return repository.findAll();
    }

    @GetMapping("/disponiveis")
    public List<Quarto> listarDisponiveis() {
        return quartoService.listarDisponiveis();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Quarto>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(quartoService.buscarPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<Quarto> cadastrar(@RequestBody Quarto quarto) {
        Quarto novo = repository.save(quarto);
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
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
