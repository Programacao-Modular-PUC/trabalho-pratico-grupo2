package com.example.app.service;

import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.Quarto;
import com.example.app.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public List<Quarto> listarTodos() {
        return quartoRepository.findAll();
    }

    public List<Quarto> listarDisponiveis() {
        return quartoRepository.findByDisponivelTrue();
    }

    public List<Quarto> buscarPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new RecursoNaoPermitidoException("É necessário informar o tipo de quarto para realizar a busca.");
        }

        switch (tipo.trim().toLowerCase()) {
            case "individual":
                return quartoRepository.findAllIndividuais();
            case "casal":
                return quartoRepository.findAllDuplos();
            default:
                throw new RecursoNaoPermitidoException(
                        "Tipo de quarto inválido: '" + tipo + "'. Os tipos permitidos são 'individual' e 'casal'.");
        }
    }
}
