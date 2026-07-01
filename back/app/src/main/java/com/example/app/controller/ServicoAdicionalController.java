package com.example.app.controller;

import com.example.app.model.ServicoAdicional;
import com.example.app.service.ServicoAdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*") // Ajuste para a porta do seu Vite/React se necessário
public class ServicoAdicionalController {

    @Autowired
    private ServicoAdicionalService service;

    @GetMapping
    public ResponseEntity<List<ServicoAdicional>> listar() {
        return ResponseEntity.ok(service.listarAtivos());
    }

    @PostMapping
    public ResponseEntity<ServicoAdicional> criar(@RequestBody ServicoAdicional servico) {
        return ResponseEntity.ok(service.salvar(servico));
    }
}
