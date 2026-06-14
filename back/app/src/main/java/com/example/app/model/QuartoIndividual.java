package com.example.app.model;

import jakarta.persistence.Entity;

@Entity
public class QuartoIndividual extends Quarto {

    private int quantidadeCamas;

    public QuartoIndividual() {}

    public QuartoIndividual(double valorBase, boolean possuiAR, boolean possuiHidro, int quantidadeCamas) {
        super(valorBase, possuiAR, possuiHidro);
        this.quantidadeCamas = quantidadeCamas;
    }

    @Override
    public double calcularDiaria() {

        if (quantidadeCamas <= 1) {
            return valorBase;
        }
        return valorBase + ((quantidadeCamas - 1) * 50.0);
    }

    @Override
    public int getCapacidadeMaxima() {
        return quantidadeCamas;
    }

    public int getQuantidadeCamas() { return quantidadeCamas; }
    public void setQuantidadeCamas(int quantidadeCamas) { this.quantidadeCamas = quantidadeCamas; }
}