package com.example.app.controller;

import com.example.app.model.PacoteHospedagem;
import com.example.app.service.PacoteHospedagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pacotes")
@CrossOrigin(origins = "*")
public class PacoteHospedagemController {

    @Autowired
    private PacoteHospedagemService service;

    @GetMapping
    public ResponseEntity<List<PacoteHospedagem>> listar() {
        return ResponseEntity.ok(service.listarAtivos());
    }

    @PostMapping
    public ResponseEntity<PacoteHospedagem> criar(@RequestBody PacoteHospedagem pacote) {
        return new ResponseEntity<>(service.criar(pacote), HttpStatus.CREATED);
    }
}
