package com.example.app.service;

import com.example.app.model.ServicoAdicional;
import com.example.app.repository.ServicoAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoAdicionalService {

    @Autowired
    private ServicoAdicionalRepository repository;

    public List<ServicoAdicional> listarTodos() {
        return repository.findAll();
    }

    public List<ServicoAdicional> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public ServicoAdicional salvar(ServicoAdicional servico) {
        return repository.save(servico);
    }

    public ServicoAdicional buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
