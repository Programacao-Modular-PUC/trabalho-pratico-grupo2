package com.example.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrão de projeto Composite.
 *
 * PacoteHospedagem é o "composto": além de agrupar serviços adicionais
 * individuais (folhas), ele também pode agrupar outros pacotes
 * (subPacotes), permitindo combinações livres e arbitrariamente
 * aninhadas de serviços sem precisar criar uma subclasse fixa para
 * cada combinação (Pacote Economico, Familia, Premium, Personalizado, ...).
 *
 * Como PacoteHospedagem implementa a mesma interface ItemHospedagem que
 * ServicoAdicional, o cálculo de valor é feito de forma uniforme e
 * recursiva: calcularValor() soma os serviços diretos com o valor de
 * cada sub-pacote, que por sua vez soma os seus próprios itens.
 */
@Entity
@Table(name = "pacotes_hospedagem")
public class PacoteHospedagem implements ItemHospedagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private boolean personalizado;
    private boolean ativo = true;

    @ManyToMany
    @JoinTable(
        name = "pacote_servicos",
        joinColumns = @JoinColumn(name = "pacote_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<ServicoAdicional> servicos = new ArrayList<>();

    // Auto-relacionamento que viabiliza o Composite: um pacote pode
    // conter outros pacotes ("Pacote Premium" pode, por exemplo, ser
    // composto pelo "Pacote Economico" + serviços extras).
    @ManyToMany
    @JoinTable(
        name = "pacote_subpacotes",
        joinColumns = @JoinColumn(name = "pacote_id"),
        inverseJoinColumns = @JoinColumn(name = "subpacote_id")
    )
    private List<PacoteHospedagem> subPacotes = new ArrayList<>();

    public PacoteHospedagem() {}

    public PacoteHospedagem(String nome, String descricao, boolean personalizado, List<ServicoAdicional> servicos) {
        this(nome, descricao, personalizado, servicos, new ArrayList<>());
    }

    public PacoteHospedagem(String nome, String descricao, boolean personalizado,
                             List<ServicoAdicional> servicos, List<PacoteHospedagem> subPacotes) {
        this.nome = nome;
        this.descricao = descricao;
        this.personalizado = personalizado;
        this.servicos = servicos == null ? new ArrayList<>() : servicos;
        this.subPacotes = subPacotes == null ? new ArrayList<>() : subPacotes;
    }

    /** Implementação do Composite: soma serviços diretos + sub-pacotes (recursivo). */
    @Override
    public double calcularValor(int numeroDiarias, int quantidadeHospedes) {
        double totalServicosDiretos = servicos == null ? 0.0 : servicos.stream()
                .filter(ServicoAdicional::isAtivo)
                .mapToDouble(servico -> servico.calcularValor(numeroDiarias, quantidadeHospedes))
                .sum();

        double totalSubPacotes = subPacotes == null ? 0.0 : subPacotes.stream()
                .filter(PacoteHospedagem::isAtivo)
                .mapToDouble(sub -> sub.calcularValor(numeroDiarias, quantidadeHospedes))
                .sum();

        return totalServicosDiretos + totalSubPacotes;
    }

    /** Mantido por compatibilidade com o código já existente (HospedagemService, etc.). */
    public double calcularValorServicos(int numeroDiarias, int quantidadeHospedes) {
        return calcularValor(numeroDiarias, quantidadeHospedes);
    }

    public void adicionarServico(ServicoAdicional servico) {
        if (servico != null && !servicos.contains(servico)) {
            servicos.add(servico);
        }
    }

    public void adicionarSubPacote(PacoteHospedagem subPacote) {
        if (subPacote != null && !subPacote.equals(this) && !subPacotes.contains(subPacote)) {
            subPacotes.add(subPacote);
        }
    }

    public List<PacoteHospedagem> getSubPacotes() { return subPacotes; }
    public void setSubPacotes(List<PacoteHospedagem> subPacotes) {
        this.subPacotes = subPacotes == null ? new ArrayList<>() : subPacotes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isPersonalizado() { return personalizado; }
    public void setPersonalizado(boolean personalizado) { this.personalizado = personalizado; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public List<ServicoAdicional> getServicos() { return servicos; }
    public void setServicos(List<ServicoAdicional> servicos) {
        this.servicos = servicos == null ? new ArrayList<>() : servicos;
    }
}