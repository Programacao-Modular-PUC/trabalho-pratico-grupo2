package com.example.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicos_adicionais")
public class ServicoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;

    @Enumerated(EnumType.STRING)
    private TipoCobrancaServico tipoCobranca = TipoCobrancaServico.UNICA;

    private boolean ativo = true;

    public ServicoAdicional() {}

    public ServicoAdicional(String nome, double preco) {
        this(nome, null, preco, TipoCobrancaServico.UNICA);
    }

    public ServicoAdicional(String nome, String descricao, double preco, TipoCobrancaServico tipoCobranca) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoCobranca = tipoCobranca == null ? TipoCobrancaServico.UNICA : tipoCobranca;
    }

    public double calcularValor(int numeroDiarias, int quantidadeHospedes) {
        int diarias = Math.max(numeroDiarias, 1);
        int hospedes = Math.max(quantidadeHospedes, 1);

        if (tipoCobranca == TipoCobrancaServico.POR_DIARIA) {
            return preco * diarias;
        }

        if (tipoCobranca == TipoCobrancaServico.POR_HOSPEDE) {
            return preco * hospedes;
        }

        return preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public TipoCobrancaServico getTipoCobranca() { return tipoCobranca; }
    public void setTipoCobranca(TipoCobrancaServico tipoCobranca) {
        this.tipoCobranca = tipoCobranca == null ? TipoCobrancaServico.UNICA : tipoCobranca;
    }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
