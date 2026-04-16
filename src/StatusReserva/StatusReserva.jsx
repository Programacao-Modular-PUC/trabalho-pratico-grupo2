import { useState } from "react";
import styles from "./StatusReserva.module.css";

function StatusReserva() {
  const [reserva] = useState({
    cliente: "Gustavo Paulino",
    cpf: "123.456.789-00",
    residencia: "Pousada Marau - Centro",
    quarto: "Casal",
    entrada: "2026-04-20T14:00",
    saida: "2026-04-22T13:00",
    valorBase: 150,
    ar: true,
    hidro: false
  });

  function calcularDiariasOficial(entrada, saida) {
    const dataIn = new Date(entrada);
    const dataOut = new Date(saida);
    let diffms = dataOut - dataIn;
    let dias = Math.floor(diffms / (1000 * 60 * 60 * 24));
    if (dataOut.getHours() >= 12) dias += 1;
    return dias > 0 ? dias : 1;
  }

  const numDiarias = calcularDiariasOficial(reserva.entrada, reserva.saida);
  const adicionais = (reserva.ar ? 30 : 0) + (reserva.hidro ? 50 : 0);
  const totalPagar = (reserva.valorBase + adicionais) * numDiarias;

  return (
    <main className={styles.containerGeral}>
      <div className={styles.caixa}>
        <header className={styles.secaoTopo}>
          <h1 className={styles.secaoTitulo}>Status da <span>Hospedagem</span></h1>
          <p className={styles.secaoSub}>Confira os detalhes da sua estadia em Maraú</p>
        </header>

        <section className={styles.cardInfo}>
          <div className={styles.infoGrupo}>
            <span className={styles.statLabel}>Hóspede Principal</span>
            <p className={styles.statValMenor}>{reserva.cliente}</p>
            <span className={styles.cardTag}>CPF: {reserva.cpf}</span>
          </div>

          <div className={styles.infoGrupo}>
            <span className={styles.statLabel}>Localização</span>
            <p className={styles.resNome}>{reserva.residencia}</p>
            <p className={styles.resLoc}>Quarto: {reserva.quarto}</p>
          </div>

          <div className={styles.detalhesPagamento}>
            <div className={styles.itemLinha}>
              <span>Check-in:</span>
              <strong>{new Date(reserva.entrada).toLocaleDateString()} às 12h</strong>
            </div>
            <div className={styles.itemLinha}>
              <span>Check-out:</span>
              <strong>{new Date(reserva.saida).toLocaleDateString()} às {new Date(reserva.saida).getHours()}h</strong>
            </div>
            <div className={styles.itemLinha}>
              <span>Permanência:</span>
              <strong>{numDiarias} diárias</strong>
            </div>
            
            <div className={styles.resRodape}>
               <span className={styles.resPreco}>Total à pagar</span>
               <span className={styles.resPreco}><strong>R$ {totalPagar.toFixed(2)}</strong></span>
            </div>
          </div>
        </section>

        <div className={styles.acoes}>
          {/* BOTÃO CORRIGIDO AQUI */}
          <button className={styles.btnVoltar} onClick={() => window.print()}>
            Imprimir Recibo
          </button>
          <button className={styles.btnPrimario}>
            Confirmar Pagamento
          </button>
        </div>
      </div>
    </main>
  );
}

export default StatusReserva;