package com.example.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private String senha;
    private String tipoPerfil; 
    private int quantidadeHospedagens;

    public Cliente() {}

    public Cliente(String nome, String cpf, String email, String telefone, String endereco, String senha, String tipoPerfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
        this.tipoPerfil = tipoPerfil;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getTipoPerfil() { return tipoPerfil; }
    public void setTipoPerfil(String tipoPerfil) { this.tipoPerfil = tipoPerfil; }
    public int getQuantidadeHospedagens() {  return quantidadeHospedagens; }

    public void setQuantidadeHospedagens(int quantidadeHospedagens) {  this.quantidadeHospedagens = quantidadeHospedagens;}

}