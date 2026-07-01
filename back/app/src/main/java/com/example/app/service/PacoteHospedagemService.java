package com.example.app.service;

import com.example.app.model.PacoteHospedagem;
import com.example.app.model.ServicoAdicional;
import com.example.app.repository.PacoteHospedagemRepository;
import com.example.app.repository.ServicoAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacoteHospedagemService {

    @Autowired
    private PacoteHospedagemRepository pacoteRepository;

    @Autowired
    private ServicoAdicionalRepository servicoRepository;

    public List<PacoteHospedagem> listarAtivos() {
        return pacoteRepository.findByAtivoTrue();
    }

    public PacoteHospedagem criar(PacoteHospedagem pacote) {
        pacote.setServicos(buscarServicosPersistidos(pacote.getServicos()));
        return pacoteRepository.save(pacote);
    }

    private List<ServicoAdicional> buscarServicosPersistidos(List<ServicoAdicional> servicos) {
        if (servicos == null || servicos.isEmpty()) {
            return List.of();
        }

        List<Long> ids = servicos.stream()
                .map(ServicoAdicional::getId)
                .toList();

        List<ServicoAdicional> encontrados = servicoRepository.findAllById(ids);
        if (encontrados.size() != ids.size()) {
            throw new NoSuchElementException("Um ou mais servicos do pacote nao foram encontrados.");
        }

        return encontrados;
    }
}
