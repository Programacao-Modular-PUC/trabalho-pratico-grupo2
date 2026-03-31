
import styles from '../Login/Login.module.css'
function Home() {
 

  return (
    <>
<main className={styles.principal}>
 
    <div className={styles.caixa}>
 
    
 
     
      <h1 className={styles.titulo}>Bem-vindo de volta</h1>
      <p className={styles.sub}>Entre na sua conta para continuar</p>
 
      <div className={styles.tipoCont}>
        <button className={`${styles.tipo} ${styles.tipoAtivo}`}>🏠 Anfitrião</button>
        <button className={styles.tipo}>🧳 Cliente</button>
      </div>
 
      
      <div className={styles.form}>
 
        <div className={styles.campo}>
          <label className={styles.label}>Nome</label>
          <input className={styles.input} type="text" placeholder="Seu nome completo" />
        </div>
 
        <div className={styles.campo}>
          <label className={styles.label}>E-mail</label>
          <input className={styles.input} type="email" placeholder="seu@email.com" />
        </div>
 
        <div className={styles.campo}>
          <label className={styles.label}>Senha</label>
          <input className={styles.input} type="password" placeholder="••••••••" />
        </div>
 
        <div className={styles.esqueci}>
          <a href="#" className={styles.link}>Esqueci minha senha</a>
        </div>
 
        <button className={styles.btnEntrar}>Entrar</button>
 
      </div>
 
      {/* Rodapé do card */}
      <p className={styles.cadastro}>
        Não tem conta?{' '}
        <a href="#" className={styles.link}>Cadastre-se grátis</a>
      </p>
 
    </div>
 
  </main>
    </>
  )
}

export default Home
