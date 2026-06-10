package com.example.app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Residencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String endereco;
    private String contato;

    @OneToMany
    private List<Quarto> listaQuartos;

    public Residencia() {
    }

    public Residencia(String endereco,
                      String contato,
                      List<Quarto> listaQuartos) {

        this.endereco = endereco;
        this.contato = contato;
        this.listaQuartos = listaQuartos;
    }

    public int getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getContato() {
        return contato;
    }

    public List<Quarto> getListaQuartos() {
        return listaQuartos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setListaQuartos(List<Quarto> listaQuartos) {
        this.listaQuartos = listaQuartos;
    }
}