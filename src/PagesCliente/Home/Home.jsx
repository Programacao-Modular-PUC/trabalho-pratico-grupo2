import React from 'react'; // <-- ESSA LINHA PRECISA SER A PRIMEIRA DO ARQUIVO!
import Header from '../../Components/Header/Header' 
import styles from '../Home/Home.module.css'
function Home() {
 

  return (
    <>
 <Header/>
 <main className={styles.principal}>
 
 
    <section className={styles.topo}>
      <h1>Sua estadia começa aqui.</h1>
      <p>Quartos e residências</p>
      <div className={styles.topoCta}>
        <button className={styles.btnPrimario}>Cadastre-se grátis</button>
      </div>
    </section>
 
   
    <div className={styles.barraStats}>
      <div className={styles.stat}>
        <div className={styles.statVal}>48+</div>
        <div className={styles.statLabel}>Residências cadastradas</div>
      </div>
      <div className={styles.stat}>
        <div className={styles.statVal}>120+</div>
        <div className={styles.statLabel}>Quartos disponíveis</div>
      </div>
      <div className={styles.stat}>
        <div className={styles.statVal}>3.2k</div>
        <div className={styles.statLabel}>Hóspedes satisfeitos</div>
      </div>
      <div className={styles.stat}>
        <div className={styles.statVal}>4.9★</div>
        <div className={styles.statLabel}>Avaliação média</div>
      </div>
    </div>
 
   
    <section className={styles.secao}>
      <div className={styles.secaoTopo}>
        <div className={styles.secaoTitulo}>O que <span>fazemos</span></div>
        <div className={styles.secaoSub}>Uma plataforma completa para quem quer hospedar ou se hospedar  com tranquilidade.</div>
      </div>
      <div className={styles.gradeCards}>
        <div className={styles.card}>
          <span className={styles.cardIcone}>🏡</span>
          <div className={styles.cardTitulo}>Gerenciamento de Residências</div>
          <div className={styles.cardDesc}>Proprietários cadastram suas casas e quartos com facilidade, definindo valores base, diferenciais e disponibilidade em tempo real.</div>
          <div className={styles.cardTag}>Para donos</div>
        </div>
        <div className={styles.card}>
          <span className={styles.cardIcone}>📅</span>
          <div className={styles.cardTitulo}>Reservas Sem Complicação</div>
          <div className={styles.cardDesc}>Faça reservas futuras com segurança. Nosso sistema verifica disponibilidade automaticamente e bloqueia datas para evitar conflitos.</div>
          <div className={styles.cardTag}>Para hóspedes</div>
        </div>
        <div className={styles.card}>
          <span className={styles.cardIcone}>🧮</span>
          <div className={styles.cardTitulo}>Cálculo Automático de Diárias</div>
          <div className={styles.cardDesc}>O valor total é calculado com base no período, tipo de quarto e itens adicionais como ar-condicionado e hidromassagem.</div>
          <div className={styles.cardTag}>Automático</div>
        </div>
        <div className={styles.card}>
          <span className={styles.cardIcone}>🧾</span>
          <div className={styles.cardTitulo}>Emissão de Recibos</div>
          <div className={styles.cardDesc}>Todo aluguel gera um recibo detalhado com datas, diárias, valores e dados do hóspede. Tudo organizado e impresso na tela.</div>
          <div className={styles.cardTag}>Documentação</div>
        </div>
        <div className={styles.card}>
          <span className={styles.cardIcone}>📊</span>
          <div className={styles.cardTitulo}>Histórico Completo</div>
          <div className={styles.cardDesc}>Acompanhe o histórico de hospedagens por residência. Dados organizados para donos e hóspedes consultarem quando quiserem.</div>
          <div className={styles.cardTag}>Gestão</div>
        </div>
        <div className={styles.card}>
          <span className={styles.cardIcone}>🔒</span>
          <div className={styles.cardTitulo}>Cadastro e Autenticação</div>
          <div className={styles.cardDesc}>Perfis seguros para clientes com CPF, endereço e contato. Autenticação para garantir que só você acessa suas reservas e dados.</div>
          <div className={styles.cardTag}>Segurança</div>
        </div>
      </div>
    </section>
 
    <section className={styles.galeria}>
      <div className={styles.galeriaRotulo}>📸</div>
      <div className={styles.galeriaFila}>
        <div className={`${styles.galTile} ${styles.t1}`}>
          <div className={styles.tileInt}></div>
          
        </div>
        <div className={`${styles.galTile} ${styles.t2}`}>
          <div className={styles.tileInt}></div>
          
        </div>
        <div className={`${styles.galTile} ${styles.t3}`}>
          <div className={styles.tileInt}></div>
         
        </div>
        <div className={`${styles.galTile} ${styles.t4}`}>
          <div className={styles.tileInt}></div>
        
        </div>
        <div className={`${styles.galTile} ${styles.t5}`}>
          <div className={styles.tileInt}></div>
          
        </div>
      </div>
    </section>
 
  
    <section className={styles.secao}>
      <div className={styles.secaoTopo}>
        <div className={styles.secaoTitulo}>Residências em <span>destaque</span></div>
        <div className={styles.secaoSub}>Faça login para ver preços completos e realizar sua reserva.</div>
      </div>
      <div className={styles.gradeRes}>
        <div className={`${styles.cardRes} ${styles.r1}`}>
          <div className={styles.resImg}>🏡</div>
          <div className={styles.resCorpo}>
         
            <div className={styles.resLoc}>📍Contagem</div>
            <div className={styles.resTags}>
              <span className={`${styles.tag} ${styles.azul}`}>Ar-condicionado</span>
              <span className={`${styles.tag} ${styles.verde}`}>Vista pro Mar</span>
            </div>
          </div>
          <div className={styles.resRodape}>
            <div className={styles.resPreco}>a partir de <strong>R$150</strong>/noite</div>
            <button className={styles.btnVer}>Ver 🔒</button>
          </div>
        </div>
        <div className={`${styles.cardRes} ${styles.r2}`}>
          <div className={styles.resImg}>🌴</div>
          <div className={styles.resCorpo}>
  
            <div className={styles.resLoc}>📍Buritis</div>
            <div className={styles.resTags}>
              <span className={`${styles.tag} ${styles.verde}`}>Hidromassagem</span>
              <span className={`${styles.tag} ${styles.azul}`}>Wi-Fi</span>
            </div>
          </div>
          <div className={styles.resRodape}>
            <div className={styles.resPreco}>a partir de <strong>R$200</strong>/noite</div>
            <button className={styles.btnVer}>Ver 🔒</button>
          </div>
        </div>
        <div className={`${styles.cardRes} ${styles.r3}`}>
          <div className={styles.resImg}>🌺</div>
          <div className={styles.resCorpo}>
            
            <div className={styles.resLoc}>📍Centro</div>
            <div className={styles.resTags}>
              <span className={`${styles.tag} ${styles.rosa}`}>Café incluído</span>
              <span className={`${styles.tag} ${styles.azul}`}>Ar-condicionado</span>
            </div>
          </div>
          <div className={styles.resRodape}>
            <div className={styles.resPreco}>a partir de <strong>R$120</strong>/noite</div>
            <button className={styles.btnVer}>Ver 🔒</button>
          </div>
        </div>
      </div>
    </section>
 
  </main>
    </>
  )
}

export default Home
