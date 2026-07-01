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

@Entity
@Table(name = "pacotes_hospedagem")
public class PacoteHospedagem {

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

    public PacoteHospedagem() {}

    public PacoteHospedagem(String nome, String descricao, boolean personalizado, List<ServicoAdicional> servicos) {
        this.nome = nome;
        this.descricao = descricao;
        this.personalizado = personalizado;
        this.servicos = servicos == null ? new ArrayList<>() : servicos;
    }

    public double calcularValorServicos(int numeroDiarias, int quantidadeHospedes) {
        if (servicos == null) {
            return 0.0;
        }

        return servicos.stream()
                .filter(ServicoAdicional::isAtivo)
                .mapToDouble(servico -> servico.calcularValor(numeroDiarias, quantidadeHospedes))
                .sum();
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
