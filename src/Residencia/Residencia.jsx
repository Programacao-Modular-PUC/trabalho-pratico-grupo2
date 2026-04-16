import styles from "../Residencia/Residencia.module.css";
import Header from "../Components/Header/Header";

function Residencia() {
  return (
    <div style={{ display: "flex" }}>
      <Header />

      <main className={styles.principal}>
        {/* 🔝 TOPO */}
        <section className={styles.topo}>
          <div className={styles.galeriaTopo}>
            {/* FOTO GRANDE */}
            <div className={styles.imgPrincipal}>
              <img src="/imagenscasa/casa.jpg" alt="principal" />
            </div>

            {/* GRID LADO DIREITO */}
            <div className={styles.gridImgs}>
              <img src="/imagenscasa/casa.jpg" alt="" />
              <img src="/imagenscasa/casa.jpg" alt="" />
              <img src="/imagenscasa/casa.jpg" alt="" />
              <img src="/imagenscasa/casa.jpg" alt="" />
            </div>
          </div>
        </section>

        {/* 📊 CARDS (RESUMO) */}
        <section className={styles.secao}>
          <div className={styles.gradeCards}>
            {/* Receita */}
            <div className={styles.card}>
              <span className={styles.cardIcone}>💰</span>
              <h3 className={styles.cardTitulo}>Receita</h3>
              <p className={styles.cardDesc}>R$ 2.400 este mês</p>
            </div>

            {/* Reservas */}
            <div className={styles.card}>
              <span className={styles.cardIcone}>📅</span>
              <h3 className={styles.cardTitulo}>Reservas</h3>
              <p className={styles.cardDesc}>
                12 reservas
                <br />
                Próxima: 20/04
              </p>
            </div>
          </div>
        </section>

        {/* 📋 DESCRIÇÃO */}
        <section className={styles.secao}>
          <div className={styles.secaoTopo}>
            <h2 className={styles.secaoTitulo}>
              Sobre a <span>residência</span>
            </h2>
          </div>

          <p className={styles.secaoSub}>
            Casa confortável com vista para o mar, perfeita para descanso e
            lazer em família.
          </p>
        </section>
      </main>
    </div>
  );
}

export default Residencia;
