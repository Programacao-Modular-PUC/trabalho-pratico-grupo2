package com.example.app.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "residencias")
public class Residencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String contato;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "residencia_quartos",
        joinColumns = @JoinColumn(name = "residencia_id"),
        inverseJoinColumns = @JoinColumn(name = "quarto_id")
    )
    private List<Quarto> listaQuartos;

    public Residencia() {}

    public Residencia(String endereco, String contato, List<Quarto> listaQuartos) {
        this.endereco = endereco;
        this.contato = contato;
        this.listaQuartos = listaQuartos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    public List<Quarto> getListaQuartos() { return listaQuartos; }
    public void setListaQuartos(List<Quarto> listaQuartos) { this.listaQuartos = listaQuartos; }
}