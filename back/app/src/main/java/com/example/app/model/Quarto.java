package com.example.app.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected double valorBase;
    protected boolean possuiAR;
    protected boolean possuiHidro;

    public Quarto() {}

    public Quarto(double valorBase, boolean possuiAR, boolean possuiHidro) {
        this.valorBase = valorBase;
        this.possuiAR = possuiAR;
        this.possuiHidro = possuiHidro;
    }

    public abstract double calcularDiaria();

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }
    public boolean isPossuiAR() { return possuiAR; }
    public void setPossuiAR(boolean possuiAR) { this.possuiAR = possuiAR; }
    public boolean isPossuiHidro() { return possuiHidro; }
    public void setPossuiHidro(boolean possuiHidro) { this.possuiHidro = possuiHidro; }
}