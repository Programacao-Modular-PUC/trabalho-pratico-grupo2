package com.example.app.model;

import jakarta.persistence.Entity;

@Entity
public class QuartoFamilia extends Quarto {

    private int quantidadeHospedes;
    private int quantidadeAmbientes;

    public QuartoFamilia() {}

    public QuartoFamilia(double valorBase, boolean possuiAR, boolean possuiHidro, int quantidadeHospedes, int quantidadeAmbientes) {
        super(valorBase, possuiAR, possuiHidro);
        this.quantidadeHospedes = quantidadeHospedes;
        this.quantidadeAmbientes = quantidadeAmbientes;
    }

    @Override
    public double calcularDiaria() {
        double total = valorBase;


        total += total * (quantidadeHospedes * 0.10);

        if (quantidadeHospedes >= 5) {
            total *= 0.88;
        }

        return total;
    }

    @Override
    public int getCapacidadeMaxima() {
        return quantidadeHospedes;
    }

    public int getQuantidadeHospedes() { return quantidadeHospedes; }
    public void setQuantidadeHospedes(int quantidadeHospedes) { this.quantidadeHospedes = quantidadeHospedes; }
    public int getQuantidadeAmbientes() { return quantidadeAmbientes; }
    public void setQuantidadeAmbientes(int quantidadeAmbientes) { this.quantidadeAmbientes = quantidadeAmbientes; }
}