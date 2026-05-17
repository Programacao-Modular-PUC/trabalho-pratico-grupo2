package com.example.app.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date dataEntrada;
    private Date dataSaida;
    private int numeroDiarias;
    private double valorFinal;

    @ManyToOne
    private Quarto quarto;

    @ManyToOne
    private Cliente cliente;

    public Aluguel() {
    }

    public Aluguel(Date dataEntrada,
                   Date dataSaida,
                   int numeroDiarias,
                   Quarto quarto,
                   Cliente cliente) {

        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.numeroDiarias = numeroDiarias;
        this.quarto = quarto;
        this.cliente = cliente;

        this.valorFinal = calcularValorFinal();
    }

    public double calcularValorFinal() {

        return quarto.calcularDiaria() * numeroDiarias;
    }

    public int getId() {
        return id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public int getNumeroDiarias() {
        return numeroDiarias;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setNumeroDiarias(int numeroDiarias) {
        this.numeroDiarias = numeroDiarias;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}