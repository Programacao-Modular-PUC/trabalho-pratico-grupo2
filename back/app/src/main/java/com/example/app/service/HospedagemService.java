package com.example.app.service;

import com.example.app.exception.CapacidadeExcedidaException;
import com.example.app.exception.DataInvalidaException;
import com.example.app.exception.QuartoIndisponivelException;
import com.example.app.exception.RecursoNaoPermitidoException;
import com.example.app.model.*;
import com.example.app.repository.*;
import com.example.app.exception.DataInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.NoSuchElementException;
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
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
<<<<<<< HEAD
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
=======
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
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65

        return aluguelRepository.save(aluguel);
    }

<<<<<<< HEAD
    public List<Aluguel> listarReservasPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente nao encontrado.");
=======
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
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
        }
        return aluguelRepository.findByClienteIdOrderByDataEntradaDesc(clienteId);
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
    }
}
