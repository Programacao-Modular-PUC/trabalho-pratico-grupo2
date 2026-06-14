package com.example.app.controller;

import com.example.app.model.Aluguel;
import com.example.app.service.HospedagemService;
import com.example.app.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/cliente/{clienteId}")
<<<<<<< HEAD
    public ResponseEntity<?> listarPorCliente(@PathVariable Long clienteId) {
        try {
            List<Aluguel> reservas = service.listarReservasPorCliente(clienteId);
            return ResponseEntity.ok(reservas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping("/reservas/cliente/{clienteId}")
    public ResponseEntity<?> listarReservasPorCliente(@PathVariable Long clienteId) {
        return listarPorCliente(clienteId);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<?> confirmarPagamento(@PathVariable Long id) {
        try {
            Aluguel aluguel = service.confirmarPagamento(id);
            return ResponseEntity.ok(aluguel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Aluguel> cadastrar(@RequestBody Aluguel aluguel) {
        try {
            Aluguel novo = service.registrarAluguel(aluguel);
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
=======
    public ResponseEntity<List<Aluguel>> historicoPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.buscarHistoricoPorCliente(clienteId));
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
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
}
