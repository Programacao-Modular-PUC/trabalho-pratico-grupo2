package com.example.app.controller;

import com.example.app.model.Cliente;
import com.example.app.model.fidelidade.Beneficio;
import com.example.app.repository.ClienteRepository;
import com.example.app.service.FidelidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fidelidade")
@CrossOrigin(origins = "*")
public class FidelidadeController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FidelidadeService fidelidadeService;

    @GetMapping("/beneficios/{id}")
    public List<Beneficio> listar(@PathVariable Long id){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow();

        return fidelidadeService.listarBeneficios(cliente);
    }
}