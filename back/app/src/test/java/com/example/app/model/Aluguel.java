package com.example.app.model;

public class Aluguel {
    private int id;
    private  java.util.Date dataEntrada;    
    private  java.util.Date dataSaiDate;  
    private int numeroDiarias; 
    private double valorFinal;     
    public Aluguel(int id, java.util.Date dataEntrada, java.util.Date dataSaiDate, int numeroDiarias, double valorFinal) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaiDate = dataSaiDate;
        this.numeroDiarias = numeroDiarias;
        this.valorFinal = valorFinal;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDataEntrada(java.util.Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    public void setDataSaiDate(java.util.Date dataSaiDate) {
        this.dataSaiDate = dataSaiDate;
    }
    public void setNumeroDiarias(int numeroDiarias) {
        this.numeroDiarias = numeroDiarias;
    }
    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getId() {
        return id;
    }
    public java.util.Date getDataEntrada() {
        return dataEntrada;
    }
    public java.util.Date getDataSaiDate() {
        return dataSaiDate;
    }   
    public int getNumeroDiarias() {
        return numeroDiarias;
    }
    public double getValorFinal() {
        return valorFinal;
    }

    public void registrarEntrada(){

    }   
    public void registrarSaida(){

    } 
    public void contarDiarias(){

    }
    public void calcularValorFinal(){

    }   
    public void gerarHistorico(){

    }   
}
