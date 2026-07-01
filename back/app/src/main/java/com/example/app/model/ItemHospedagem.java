package com.example.app.model;

/**
 * Componente do padrão de projeto Composite.
 *
 * Representa qualquer "item" que pode compor um pacote de hospedagem:
 * tanto um serviço adicional individual (folha) quanto um outro pacote
 * de hospedagem (composto), que por sua vez pode conter outros itens.
 *
 * Graças a essa abstração comum, o cliente do sistema (PacoteHospedagemService,
 * PacoteHospedagemBuilder, telas de montagem de pacote, etc.) pode tratar
 * serviços avulsos e sub-pacotes de forma uniforme, sem precisar saber
 * se está lidando com um item simples ou com uma combinação de itens.
 */
public interface ItemHospedagem {

    String getNome();

    /**
     * Calcula o valor do item (ou, no caso de um composto, a soma
     * recursiva de todos os itens que ele contém) para a quantidade
     * de diárias e de hóspedes informada.
     */
    double calcularValor(int numeroDiarias, int quantidadeHospedes);
}