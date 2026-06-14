package com.example.app.service;

import com.example.app.exception.CapacidadeExcedidaException;
import com.example.app.exception.DataInvalidaException;
import com.example.app.exception.QuartoIndisponivelException;
import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.*;
import com.example.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HospedagemService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private QuartoRepository quartoRepository;

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
        if (aluguel.getQuarto() == null || aluguel.getQuarto().getId() == null) {
            throw new RecursoNaoPermitidoException("É necessário informar um quarto válido para registrar o aluguel.");
        }

        Quarto quarto = quartoRepository.findById(aluguel.getQuarto().getId())
                .orElseThrow(() -> new NoSuchElementException("Quarto não encontrado."));

        if (!quarto.isDisponivel()) {
            throw new QuartoIndisponivelException("O quarto selecionado não está disponível para locação.");
        }

        validarDatas(aluguel);

        if (aluguel.getQuantidadeHospedes() <= 0) {
            throw new CapacidadeExcedidaException("A quantidade de hóspedes deve ser maior que zero.");
        }

        if (aluguel.getQuantidadeHospedes() > quarto.getCapacidadeMaxima()) {
            throw new CapacidadeExcedidaException(
                    "A quantidade de hóspedes (" + aluguel.getQuantidadeHospedes() +
                    ") excede a capacidade máxima do quarto (" + quarto.getCapacidadeMaxima() + ").");
        }

        if (quarto instanceof QuartoDuplo quartoDuplo) {
            quartoDuplo.validarSolicitacaoBerco();
        }

        aluguel.setQuarto(quarto);
        aluguel.setStatus(StatusAluguel.ATIVO);
        aluguel.setValorFinal(aluguel.calcularValorFinal());

        quarto.setDisponivel(false);
        quartoRepository.save(quarto);

        return aluguelRepository.save(aluguel);
    }

    public Aluguel cancelarAluguel(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Aluguel não encontrado."));

        if (aluguel.getStatus() == StatusAluguel.CANCELADO) {
            throw new RecursoNaoPermitidoException("Este aluguel já se encontra cancelado.");
        }

        aluguel.setStatus(StatusAluguel.CANCELADO);

        Quarto quarto = aluguel.getQuarto();
        if (quarto != null) {
            quarto.setDisponivel(true);
            quartoRepository.save(quarto);
        }

        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> buscarHistoricoPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new NoSuchElementException("Cliente não encontrado.");
        }
        return aluguelRepository.findByClienteIdOrderByDataEntradaDesc(clienteId);
    }

    private void validarDatas(Aluguel aluguel) {
        if (aluguel.getDataEntrada() == null || aluguel.getDataSaida() == null) {
            throw new DataInvalidaException("As datas de entrada e saída são obrigatórias.");
        }

        if (!aluguel.getDataSaida().after(aluguel.getDataEntrada())) {
            throw new DataInvalidaException("A data de saída deve ser posterior à data de entrada.");
        }

        if (aluguel.getNumeroDiarias() <= 0) {
            throw new DataInvalidaException("O número de diárias deve ser maior que zero.");
        }
    }
}
