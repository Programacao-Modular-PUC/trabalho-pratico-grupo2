import React from 'react';
import styles from '../Header/Header.module.css'
function Header() {
 

  return (
    <>
    
<aside className={styles.lateral}>
 
 
    <div className={styles.lateralLogo}>
      <div className={styles.marca}>LOGO</div>
      <div className={styles.slogan}>LOGO</div>
    </div>
 
    {/* Nav */}
    <nav className={styles.lateralNav}>
      <div className={styles.rotuloNav}>Menu</div>
 
      <div className={`${styles.itemNav} ${styles.ativo}`}>
        <span className={styles.iconeNav}>🏠</span>
        Início
      </div>
 
      <div className={`${styles.itemNav} ${styles.bloq}`}>
        <span className={styles.iconeNav}>🏡</span>
        Residências
        <span className={styles.iconeBloq}>🔒</span>
      </div>
 
     
 
      <div className={`${styles.itemNav} ${styles.bloq}`}>
        <span className={styles.iconeNav}>📅</span>
        Reservas
        <span className={styles.iconeBloq}>🔒</span>
      </div>
 
      <div className={`${styles.itemNav} ${styles.bloq}`}>
        <span className={styles.iconeNav}>📋</span>
        Histórico
        <span className={styles.iconeBloq}>🔒</span>
      </div>
 
      <div className={`${styles.itemNav} ${styles.bloq}`}>
        <span className={styles.iconeNav}>🧾</span>
        Recibos
        <span className={styles.iconeBloq}>🔒</span>
      </div>
 
      <div className={`${styles.itemNav} ${styles.bloq}`}>
        <span className={styles.iconeNav}>👤</span>
        Meu Perfil
        <span className={styles.iconeBloq}>🔒</span>
      </div>
    </nav>
 
  
    <div className={styles.lateralPerfil}>
      <div className={styles.avatar}>👤</div>
      <div className={styles.rotuloPerf}>Conta</div>
      <div className={styles.nomePerf}>Visitante</div>
      <div className={styles.statusPerf}>Entre para acessar tudo</div>
      <div className={styles.acoesPerf}>
        <button className={styles.btnEntrar}>Entrar</button>
        <button className={styles.btnCad}>Cadastrar</button>
      </div>
    </div>
 
  </aside>

    </>
  )
}

export default Header
