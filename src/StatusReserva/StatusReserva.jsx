import React, { useEffect, useState } from 'react';
import Header from "../Components/Header/Header";
import styles from "./StatusReserva.module.css";

function StatusReserva() {
  const [alugueis, setAlugueis] = useState([]);
  const [user, setUser] = useState(null);

  useEffect(() => {
    // 1. Carrega as informações do usuário logado na sessão
    const stored = localStorage.getItem('@Hospedagem:user');
    let usuarioLogado = null;
    
    if (stored) {
      usuarioLogado = JSON.parse(stored);
      setUser(usuarioLogado);
    }

    // 2. Busca todos os aluguéis do banco de dados MySQL via API
    fetch('http://localhost:8080/alugueis')
      .then(res => res.json())
      .then(data => {
        // REGRA DE FILTRO POR PERFIL:
        if (usuarioLogado && usuarioLogado.tipoPerfil === 'CLIENTE') {
          // Se for cliente comum, filtra para exibir estritamente as reservas dele
          const apenasMinhasReservas = data.filter(item => item.cliente?.id === usuarioLogado.id);
          setAlugueis(apenasMinhasReservas);
        } else {
          // Se for ANFITRIAO (como o glender), exibe todos os recibos do sistema para gestão
          setAlugueis(data);
        }
      })
      .catch(() => alert("Erro ao carregar recibos do banco MySQL"));
  }, []);

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
            alugueis.map((item, idx) => (
              <section key={idx} className={styles.cardInfo} style={{marginBottom: '20px'}}>
                <div className={styles.infoGrupo}>
                  <span className={styles.statLabel}>Hóspede Cadastrado</span>
                  {/* Exibe o nome associado à reserva vinda do banco ou o usuário logado */}
                  <p className={styles.statValMenor}>{item.cliente?.nome || user?.nome}</p>
                  <span className={styles.cardTag}>ID da Operação: {item.id}</span>
                </div>

                <div className={styles.detalhesPagamento}>
                  <div className={styles.itemLinha}>
                    <span>Check-in / Check-out:</span>
                    <strong>{new Date(item.dataEntrada).toLocaleDateString()} até {new Date(item.dataSaida).toLocaleDateString()}</strong>
                  </div>
                  <div className={styles.itemLinha}>
                    <span>Permanência faturada:</span>
                    <strong>{item.numeroDiarias} diárias</strong>
                  </div>
                  <div className={styles.resRodape}>
                     <span className={styles.resPreco}>Total pago (com taxas)</span>
                     <span className={styles.resPreco}><strong>R$ {item.valorFinal?.toFixed(2)}</strong></span>
                  </div>
                </div>
              </section>
            ))
          )}

          <div className={styles.acoes}>
            
            <button className={styles.btnPrimario} onClick={() => alert("Pagamento validado!")}>
              Confirmar Quitação
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default StatusReserva;