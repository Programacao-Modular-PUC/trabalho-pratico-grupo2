package com.example.app.service;

import com.example.app.model.*;
import com.example.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospedagemService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já está cadastrado no sistema.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente autenticar(String email, String senha, String tipoPerfil) {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
        if (clienteOpt.isPresent()) {
            Cliente c = clienteOpt.get();
            if (c.getSenha().equals(senha) && c.getTipoPerfil().equalsIgnoreCase(tipoPerfil)) {
                return c;
            }
        }
        throw new RuntimeException("Credenciais inválidas para o perfil selecionado.");
    }

    public Aluguel registrarAluguel(Aluguel aluguel) {
        aluguel.setValorFinal(aluguel.calcularValorFinal());
        return aluguelRepository.save(aluguel);
    }
}