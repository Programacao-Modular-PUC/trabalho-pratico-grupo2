package com.example.app.model;

public class Pagamento {
    private int id; 
    private double valor;
    private String status;
    public Pagamento(int id, double valor, String status) {
        this.id = id;
        this.valor = valor;
        this.status = status;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public double getValor() {
        return valor;
    }
    public String getStatus() {
        return status;
    }

    public void registrarValor(){

    }   
    public void registrarPagamento(){

    }   
    public void confirmarPagamento(){

    }
    public void vincularPagamentoAluguel(){

    }
    
}
