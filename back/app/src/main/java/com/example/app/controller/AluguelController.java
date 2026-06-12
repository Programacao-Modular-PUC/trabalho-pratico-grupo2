package com.example.app.controller;

import com.example.app.model.Aluguel;
import com.example.app.service.HospedagemService;
import com.example.app.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
@CrossOrigin(origins = "http://localhost:5173")
public class AluguelController {

    @Autowired
    private AluguelRepository repository;

    @Autowired
    private HospedagemService service;

    @GetMapping
    public List<Aluguel> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Aluguel aluguel) {
        try {
            Aluguel novo = service.registrarAluguel(aluguel);
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}