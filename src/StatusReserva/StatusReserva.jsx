import React, { useEffect, useState } from 'react';
import Header from "../Components/Header/Header";
import styles from "./StatusReserva.module.css";

function StatusReserva() {
  const [alugueis, setAlugueis] = useState([]);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const stored = localStorage.getItem('@Hospedagem:user');
    if (stored) {
      const usuarioLogado = JSON.parse(stored);
      setUser(usuarioLogado);
      
      // Busca apenas as reservas do cliente logado
      fetch(`http://localhost:8080/alugueis/cliente/${usuarioLogado.id}`)
        .then(res => res.json())
        .then(data => setAlugueis(data))
        .catch(() => alert("Erro ao carregar seu histórico"));
    }
  }, []);

  async function confirmarQuitacao(id) {
    const res = await fetch(`http://localhost:8080/alugueis/${id}/pagar`, { method: 'PUT' });
    if (res.ok) {
      alert("Pagamento validado com sucesso!");
      window.location.reload();
    } else {
      alert("Erro ao validar pagamento.");
    }
  }

  return (
    <div style={{ display: "flex" }}>
      <Header />
      <main className={styles.containerGeral} style={{marginLeft: '240px', width: 'calc(100vw - 240px)'}}>
        <div className={styles.caixa}>
          <header className={styles.secaoTopo}>
            <h1 className={styles.secaoTitulo}>Recibos de <span>Hospedagem</span></h1>
            <p className={styles.secaoSub}>Histórico oficial extraído via API</p>
          </header>

          {alugueis.length === 0 ? (
            <p style={{textAlign:'center', color:'#64748b'}}>Nenhum recibo de aluguel encontrado.</p>
          ) : (
            alugueis.map((item) => (
              <section key={item.id} className={styles.cardInfo} style={{marginBottom: '20px'}}>
                <div className={styles.infoGrupo}>
                  <span className={styles.statLabel}>Hóspede</span>
                  <p className={styles.statValMenor}>{item.cliente?.nome}</p>
                  <span className={styles.cardTag}>ID: {item.id}</span>
                </div>

                <div className={styles.detalhesPagamento}>
                  <div className={styles.itemLinha}>
                    <span>Status:</span> <strong>{item.pagamento?.status || 'PENDENTE'}</strong>
                  </div>
                  <div className={styles.resRodape}>
                     <span className={styles.resPreco}>Total pago</span>
                     <span className={styles.resPreco}><strong>R$ {item.valorFinal?.toFixed(2)}</strong></span>
                  </div>
                </div>
                
                <button className={styles.btnPrimario} onClick={() => confirmarQuitacao(item.id)}>
                  Confirmar Quitação
                </button>
              </section>
            ))
          )}
        </div>
      </main>
    </div>
  );
}

export default StatusReserva;