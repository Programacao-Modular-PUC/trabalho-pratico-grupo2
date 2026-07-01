package com.example.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrão de projeto Builder.
 *
 * Encapsula, passo a passo, a montagem de um PacoteHospedagem a partir
 * de serviços adicionais e/ou de outros pacotes (sub-pacotes), aplicando
 * validações no momento da construção (nome obrigatório, ao menos um
 * item selecionado, sem sub-pacote referenciando a si mesmo, etc.).
 *
 * Isso evita que a lógica de montagem fique espalhada em controllers/
 * services e evita a necessidade de criar uma classe fixa para cada
 * combinação de pacote (Economico, Familia, Premium, Personalizado...).
 * Qualquer combinação nova passa a ser apenas uma sequência de chamadas
 * a este builder.
 */
public class PacoteHospedagemBuilder {

    private String nome;
    private String descricao;
    private boolean personalizado;
    private final List<ServicoAdicional> servicos = new ArrayList<>();
    private final List<PacoteHospedagem> subPacotes = new ArrayList<>();

    public static PacoteHospedagemBuilder novo() {
        return new PacoteHospedagemBuilder();
    }

    public PacoteHospedagemBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PacoteHospedagemBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public PacoteHospedagemBuilder personalizado(boolean personalizado) {
        this.personalizado = personalizado;
        return this;
    }

    public PacoteHospedagemBuilder adicionarServico(ServicoAdicional servico) {
        if (servico != null) {
            servicos.add(servico);
        }
        return this;
    }

    public PacoteHospedagemBuilder adicionarServicos(List<ServicoAdicional> novosServicos) {
        if (novosServicos != null) {
            novosServicos.forEach(this::adicionarServico);
        }
        return this;
    }

    public PacoteHospedagemBuilder adicionarSubPacote(PacoteHospedagem subPacote) {
        if (subPacote != null) {
            subPacotes.add(subPacote);
        }
        return this;
    }

    public PacoteHospedagemBuilder adicionarSubPacotes(List<PacoteHospedagem> novosSubPacotes) {
        if (novosSubPacotes != null) {
            novosSubPacotes.forEach(this::adicionarSubPacote);
        }
        return this;
    }

    public PacoteHospedagem build() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalStateException("O pacote de hospedagem precisa de um nome.");
        }

        if (servicos.isEmpty() && subPacotes.isEmpty() && !personalizado) {
            throw new IllegalStateException(
                    "O pacote precisa conter ao menos um servico ou sub-pacote, "
                    + "a menos que seja um pacote personalizado montado posteriormente pelo cliente.");
        }

        PacoteHospedagem pacote = new PacoteHospedagem(nome, descricao, personalizado, servicos, subPacotes);
        return pacote;
    }
}