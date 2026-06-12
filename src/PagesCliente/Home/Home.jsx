import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../Components/Header/Header';
import styles from '../Home/Home.module.css';

function Home() {
  const navigate = useNavigate();
  const [logado, setLogado] = useState(false);

  useEffect(() => {
    setLogado(!!localStorage.getItem('@Hospedagem:user'));
  }, []);

  return (
    <>
      <Header />
      <main className={styles.principal}>
        <section className={styles.topo}>
          <h1>Sua estadia começa aqui.</h1>
          <p>Quartos e residências universitárias e executivas de alto nível</p>
          {!logado && (
            <div className={styles.topoCta}>
              <button className={styles.btnPrimario} onClick={() => navigate('/Cadastro')}>Cadastre-se grátis</button>
            </div>
          )}
        </section>
 
        <div className={styles.barraStats}>
          <div className={styles.stat}><div className={styles.statVal}>48+</div><div className={styles.statLabel}>Residências</div></div>
          <div className={styles.stat}><div className={styles.statVal}>120+</div><div className={styles.statLabel}>Quartos flexíveis</div></div>
          <div className={styles.stat}><div className={styles.statVal}>3.2k</div><div className={styles.statLabel}>Hóspedes Satisfeitos</div></div>
          <div className={styles.stat}><div className={styles.statVal}>4.9★</div><div className={styles.statLabel}>Avaliação PUC</div></div>
        </div>
 
        <section className={styles.secao}>
          <div className={styles.secaoTopo}>
            <div className={styles.secaoTitulo}>Nossas <span>Soluções Polimórficas</span></div>
            <div className={styles.secaoSub}>Infraestrutura mapeada e precificada de forma justa e automatizada para a sua necessidade.</div>
          </div>
          <div className={styles.gradeCards}>
            <div className={styles.card}>
              <span className={styles.cardIcone}>🏠</span>
              <div className={styles.cardTitulo}>Quarto Individual</div>
              <div className={styles.cardDesc}>Ideal para solteiros. O valor aumenta de acordo com a quantidade de camas adicionadas no ambiente.</div>
            </div>
            <div className={styles.card}>
              <span className={styles.cardIcone}>👩‍❤️‍👨</span>
              <div className={styles.cardTitulo}>Quarto Duplo (Casal)</div>
              <div className={styles.cardDesc}>Quartos para casais com opções de camas Queen ou King e adição opcional de berço sob demanda.</div>
            </div>
            <div className={styles.card}>
              <span className={styles.cardIcone}>👨‍👩‍👧‍👦</span>
              <div className={styles.cardTitulo}>Quarto Família</div>
              <div className={styles.cardDesc}>Múltiplos ambientes, mistura de camas com descontos altamente progressivos e vantajosos para grupos.</div>
            </div>
          </div>
        </section>
      </main>
    </>
  );
}

export default Home;