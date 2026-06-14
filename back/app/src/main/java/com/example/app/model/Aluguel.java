package com.example.app.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alugueis")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;
    private int numeroDiarias;
    private int quantidadeHospedes;
    private double valorFinal;

    @Enumerated(EnumType.STRING)
    private StatusAluguel status;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

<<<<<<< HEAD
    public Aluguel() {}
=======
    public Aluguel(Date dataEntrada, Date dataSaida, int numeroDiarias, int quantidadeHospedes, Quarto quarto, Cliente cliente) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.numeroDiarias = numeroDiarias;
        this.quantidadeHospedes = quantidadeHospedes;
        this.quarto = quarto;
        this.cliente = cliente;
        this.valorFinal = calcularValorFinal();
        this.status = StatusAluguel.ATIVO;
    }
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65

    public double calcularValorFinal() {
        if (quarto == null) return 0.0;
        double valorDiariaQuarto = quarto.calcularDiaria();
        if (quarto.isPossuiAR()) valorDiariaQuarto += 30.0;
        if (quarto.isPossuiHidro()) valorDiariaQuarto += 50.0;
        return valorDiariaQuarto * this.numeroDiarias;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(Date dataEntrada) { this.dataEntrada = dataEntrada; }
    public Date getDataSaida() { return dataSaida; }
    public void setDataSaida(Date dataSaida) { this.dataSaida = dataSaida; }
    public int getNumeroDiarias() { return numeroDiarias; }
    public void setNumeroDiarias(int numeroDiarias) { this.numeroDiarias = numeroDiarias; }
    public int getQuantidadeHospedes() { return quantidadeHospedes; }
    public void setQuantidadeHospedes(int quantidadeHospedes) { this.quantidadeHospedes = quantidadeHospedes; }
    public double getValorFinal() { return valorFinal; }
    public void setValorFinal(double valorFinal) { this.valorFinal = valorFinal; }
    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
<<<<<<< HEAD
    public Pagamento getPagamento() { return pagamento; }
    public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
=======
    public StatusAluguel getStatus() { return status; }
    public void setStatus(StatusAluguel status) { this.status = status; }
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
}