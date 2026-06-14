package com.example.app.service;

import com.example.app.model.*;
import com.example.app.repository.*;
import com.example.app.exception.DataInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HospedagemService {

    private static final String STATUS_PENDENTE = "PENDENTE";
    private static final String STATUS_CONCLUIDO = "CONCLUIDO";

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
        // 1. Validação de Regra de Negócio (exceção que você vai criar)
        if (aluguel.getDataSaida().before(aluguel.getDataEntrada())) {
            throw new DataInvalidaException("A data de saída não pode ser anterior à de entrada!");
        }

        // 2. Cálculo do valor
        aluguel.setValorFinal(aluguel.calcularValorFinal());

        // 3. Inicializa o Pagamento como PENDENTE para que o objeto exista no banco
        Pagamento pag = new Pagamento();
        pag.setValor(aluguel.getValorFinal());
        pag.setStatus(STATUS_PENDENTE);
        aluguel.setPagamento(pag);

        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listarReservasPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente nao encontrado.");
        }
        return aluguelRepository.findByClienteIdOrderByDataEntradaDesc(clienteId);
    }

    @Transactional
    public Aluguel confirmarPagamento(Long aluguelId) {
        Aluguel aluguel = aluguelRepository.findByIdForPaymentUpdate(aluguelId)
                .orElseThrow(() -> new IllegalArgumentException("Aluguel nao encontrado."));

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
}
