import styles from "./Gestao.module.css";
import Header from "../Components/Header/Header";
import { useState, useEffect } from "react";

function Gestao() {
  const frases = [
    "Controle reservas facilmente",
    "Organize espaços da sua residência",
    "Acompanhe tudo em um só lugar",
  ];

  const [indexFrase, setIndexFrase] = useState(0);
  const [texto, setTexto] = useState("");
  const [indexLetra, setIndexLetra] = useState(0);

  useEffect(() => {
    const fraseAtual = frases[indexFrase];

    if (indexLetra < fraseAtual.length) {
      const timeout = setTimeout(() => {
        setTexto((prev) => prev + fraseAtual[indexLetra]);
        setIndexLetra((prev) => prev + 1);
      }, 50);

      return () => clearTimeout(timeout);
    } else {
      // terminou a frase
      const timeout = setTimeout(() => {
        setTexto("");
        setIndexLetra(0);
        setIndexFrase((prev) => (prev + 1) % frases.length); // 🔥 aqui está o segredo
      }, 1000);

      return () => clearTimeout(timeout);
    }
  }, [indexLetra, indexFrase]);
  return (
    <div style={{ display: "flex" }}>
      <Header />

      <main className={styles.principal}>
        {/* TOPO */}
        <section className={styles.topo}>
          <h1>Gerencie suas reservas</h1>
          <p className={styles.subtitulo}>
            {texto}
            <span className={styles.cursor}>|</span>
          </p>
          <div className={styles.topoCta}>
            <button className={styles.btnPrimario}>Criar residência</button>
          </div>
        </section>

        {/* STATS */}
        <div className={styles.barraStats}>
          <div className={styles.stat}>
            <span className={styles.statVal}>2</span>
            <span className={styles.statLabel}>Residências</span>
          </div>
          <div className={styles.stat}>
            <span className={styles.statVal}>12</span>
            <span className={styles.statLabel}>Reservas</span>
          </div>
          <div className={styles.stat}>
            <span className={styles.statVal}>R$ 2.400</span>
            <span className={styles.statLabel}>Receita</span>
          </div>
        </div>

        {/* FUNCIONALIDADES */}
        <section className={styles.secao}>
          <div className={styles.secaoTopo}>
            <h2 className={styles.secaoTitulo}>
              O que você pode <span>fazer</span>
            </h2>
            <p className={styles.secaoSub}>
              Gerencie todos os aspectos das suas residências de forma simples.
            </p>
          </div>

          <div className={styles.gradeCards}>
            <div className={styles.card}>
              <span className={styles.cardIcone}>🏠</span>
              <h3 className={styles.cardTitulo}>Residências</h3>
              <p className={styles.cardDesc}>
                Cadastre e organize seus imóveis.
              </p>
            </div>

            <div className={styles.card}>
              <span className={styles.cardIcone}>📅</span>
              <h3 className={styles.cardTitulo}>Reservas</h3>
              <p className={styles.cardDesc}>Controle reservas facilmente.</p>
            </div>

            <div className={styles.card}>
              <span className={styles.cardIcone}>💰</span>
              <h3 className={styles.cardTitulo}>Financeiro</h3>
              <p className={styles.cardDesc}>Acompanhe ganhos e despesas.</p>
            </div>
          </div>
        </section>

        {/* RESIDÊNCIAS */}
        <section className={styles.secao}>
          <div className={styles.secaoTopo}>
            <h2 className={styles.secaoTitulo}>
              Suas <span>residências</span>
            </h2>
          </div>

          <div className={styles.gradeRes}>
            <div className={`${styles.cardRes} ${styles.r1}`}>
              <div className={styles.resImg}>🏡</div>
              <div className={styles.resCorpo}>
                <h3 className={styles.resNome}>Casa de Praia</h3>
                <p className={styles.resLoc}>Rio de Janeiro</p>
              </div>
            </div>

            <div className={`${styles.cardRes} ${styles.r2}`}>
              <div className={styles.resImg}>🏢</div>
              <div className={styles.resCorpo}>
                <h3 className={styles.resNome}>Apartamento</h3>
                <p className={styles.resLoc}>São Paulo</p>
              </div>
            </div>
          </div>
        </section>

        {/* RODAPÉ */}
        <footer className={styles.rodape}>
          <div className={styles.rodapeMarca}>Gestão Residencial</div>
          <div className={styles.rodapeCopy}>
            © 2026 Todos os direitos reservados
          </div>
        </footer>
      </main>
    </div>
  );
}

export default Gestao;
