package com.example.app.controller;

import com.example.app.model.Aluguel;
import com.example.app.repository.AluguelRepository;
import com.example.app.service.HospedagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Aluguel>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Aluguel>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.buscarHistoricoPorCliente(clienteId));
    }

    @GetMapping("/reservas/cliente/{clienteId}")
    public ResponseEntity<List<Aluguel>> listarReservasPorCliente(@PathVariable Long clienteId) {
        return listarPorCliente(clienteId);
    }

    @PostMapping
    public ResponseEntity<Aluguel> cadastrar(@RequestBody Aluguel aluguel) {
        Aluguel novo = service.registrarAluguel(aluguel);
        return new ResponseEntity<>(novo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Aluguel> cancelar(@PathVariable Long id) {
        Aluguel cancelado = service.cancelarAluguel(id);
        return ResponseEntity.ok(cancelado);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Aluguel> confirmarPagamento(@PathVariable Long id) {
        Aluguel aluguel = service.confirmarPagamento(id);
        return ResponseEntity.ok(aluguel);
    }
}
