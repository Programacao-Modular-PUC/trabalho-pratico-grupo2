package com.example.app.service;

import com.example.app.exception.CapacidadeExcedidaException;
import com.example.app.exception.DataInvalidaException;
import com.example.app.exception.QuartoIndisponivelException;
import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.Aluguel;
import com.example.app.model.Cliente;
import com.example.app.model.Pagamento;
import com.example.app.model.Quarto;
import com.example.app.model.QuartoDuplo;
import com.example.app.model.StatusAluguel;
import com.example.app.repository.AluguelRepository;
import com.example.app.repository.ClienteRepository;
import com.example.app.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HospedagemService {

    private static final String STATUS_PENDENTE = "PENDENTE";
    private static final String STATUS_CONCLUIDO = "CONCLUIDO";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail ja esta cadastrado no sistema.");
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
        throw new RuntimeException("Credenciais invalidas para o perfil selecionado.");
    }

    public Aluguel registrarAluguel(Aluguel aluguel) {
        if (aluguel.getQuarto() == null || aluguel.getQuarto().getId() == null) {
            throw new RecursoNaoPermitidoException("E necessario informar um quarto valido para registrar o aluguel.");
        }

        Quarto quarto = quartoRepository.findById(aluguel.getQuarto().getId())
                .orElseThrow(() -> new NoSuchElementException("Quarto nao encontrado."));

        if (!quarto.isDisponivel()) {
            throw new QuartoIndisponivelException("O quarto selecionado nao esta disponivel para locacao.");
        }

        validarDatas(aluguel);

        if (aluguel.getQuantidadeHospedes() <= 0) {
            throw new CapacidadeExcedidaException("A quantidade de hospedes deve ser maior que zero.");
        }

        if (aluguel.getQuantidadeHospedes() > quarto.getCapacidadeMaxima()) {
            throw new CapacidadeExcedidaException(
                    "A quantidade de hospedes (" + aluguel.getQuantidadeHospedes() +
                    ") excede a capacidade maxima do quarto (" + quarto.getCapacidadeMaxima() + ").");
        }

        if (quarto instanceof QuartoDuplo quartoDuplo) {
            quartoDuplo.validarSolicitacaoBerco();
        }

        quarto.setDisponivel(false);
        quartoRepository.save(quarto);

        Aluguel aluguelSalvo = aluguelRepository.save(aluguel);

        Cliente cliente = aluguel.getCliente();

        cliente.setQuantidadeHospedagens(
        cliente.getQuantidadeHospedagens() + 1
);

clienteRepository.save(cliente);

return aluguelSalvo;
    }

    public Aluguel cancelarAluguel(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Aluguel nao encontrado."));

        if (aluguel.getStatus() == StatusAluguel.CANCELADO) {
            throw new RecursoNaoPermitidoException("Este aluguel ja se encontra cancelado.");
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
            throw new NoSuchElementException("Cliente nao encontrado.");
        }
        return aluguelRepository.findByClienteIdOrderByDataEntradaDesc(clienteId);
    }

    @Transactional
    public Aluguel confirmarPagamento(Long aluguelId) {
        Aluguel aluguel = aluguelRepository.findByIdForPaymentUpdate(aluguelId)
                .orElseThrow(() -> new NoSuchElementException("Aluguel nao encontrado."));

        Pagamento pagamento = aluguel.getPagamento();
        if (pagamento == null) {
            throw new IllegalStateException("Nao existe pagamento vinculado a este aluguel.");
        }

        String statusAtual = pagamento.getStatus();
        if (STATUS_CONCLUIDO.equalsIgnoreCase(statusAtual)) {
            throw new IllegalStateException("Pagamento ja foi concluido.");
        }

        if (!STATUS_PENDENTE.equalsIgnoreCase(statusAtual)) {
            throw new IllegalStateException("Apenas pagamentos pendentes podem ser concluidos.");
        }

        pagamento.setStatus(STATUS_CONCLUIDO);
        return aluguelRepository.save(aluguel);
    }

    private Pagamento criarPagamentoPendente(Aluguel aluguel) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(aluguel.getValorFinal());
        pagamento.setStatus(STATUS_PENDENTE);
        return pagamento;
    }

    private void validarDatas(Aluguel aluguel) {
        if (aluguel.getDataEntrada() == null || aluguel.getDataSaida() == null) {
            throw new DataInvalidaException("As datas de entrada e saida sao obrigatorias.");
        }

        if (!aluguel.getDataSaida().after(aluguel.getDataEntrada())) {
            throw new DataInvalidaException("A data de saida deve ser posterior a data de entrada.");
        }

        if (aluguel.getNumeroDiarias() <= 0) {
            throw new DataInvalidaException("O numero de diarias deve ser maior que zero.");
        }
    }

    public Cliente buscarClientePorId(Long id) {
    return clienteRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado."));
    }
}
