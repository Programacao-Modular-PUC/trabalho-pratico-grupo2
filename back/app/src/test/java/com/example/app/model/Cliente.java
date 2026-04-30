package com.example.app.model;

import lombok.val;

public class Cliente {
    private int id;
    private String nome;
    private int cpf;
    
    public void setId(int id) {
        this.id = id;
    }   
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public int getCpf() {
        return cpf;
    }
    public int getId() {
        return id;
    }
    public Cliente() {
    }

    public Cliente(int id, String nome, int cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
    public void realizarReserva() {
        
    }
    public void realizarAluguel() {
        
    }   
}
