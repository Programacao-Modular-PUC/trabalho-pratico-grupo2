package com.example.app.service;

import com.example.app.model.ServicoAdicional;
import com.example.app.model.catalogo.CatalogoServicosSingleton;
import com.example.app.repository.ServicoAdicionalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoAdicionalService {

    @Autowired
    private ServicoAdicionalRepository repository;

    /**
     * Garante que o catálogo global (Singleton) reflita o banco de dados
     * assim que a aplicação sobe, e não apenas depois do primeiro cadastro.
     */
    @PostConstruct
    public void carregarCatalogoInicial() {
        CatalogoServicosSingleton.getInstance().atualizar(repository.findByAtivoTrue());
    }

    public List<ServicoAdicional> listarTodos() {
        return repository.findAll();
    }

    public List<ServicoAdicional> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public ServicoAdicional salvar(ServicoAdicional servico) {
        ServicoAdicional salvo = repository.save(servico);
        // Mantém o catálogo global (Singleton) sempre consistente com o
        // banco de dados, em um único ponto de atualização.
        CatalogoServicosSingleton.getInstance().registrar(salvo);
        return salvo;
    }

    public ServicoAdicional buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}