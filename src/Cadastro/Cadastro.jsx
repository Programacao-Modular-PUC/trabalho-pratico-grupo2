import styles from '../Cadastro/Cadastro.module.css'
function Cadastro() {
 

  return (
    <>
    
<main className={styles.principal}>
 
    <div className={styles.caixa}>
 
      <div className={styles.topo}>

    
      </div>
 
      <h1 className={styles.titulo}>Crie sua conta</h1>
      <p className={styles.sub}>Cadastre-se grátis e comece agora</p>
 
      
      <div className={styles.tipoCont}>
        <button className={`${styles.tipo} ${styles.tipoAtivo}`}>🏠 Anfitrião</button>
        <button className={styles.tipo}>🧳 Cliente</button>
      </div>
 
    
      <div className={styles.form}>
 
        <div className={styles.linha}>
          <div className={styles.campo}>
            <label className={styles.label}>Nome</label>
            <input className={styles.input} type="text" placeholder="Seu nome" />
          </div>
          <div className={styles.campo}>
            <label className={styles.label}>CPF</label>
            <input className={styles.input} type="text" placeholder="000.000.000-00" />
          </div>
        </div>
 
        <div className={styles.campo}>
          <label className={styles.label}>E-mail</label>
          <input className={styles.input} type="email" placeholder="seu@email.com" />
        </div>
 
        <div className={styles.campo}>
          <label className={styles.label}>Telefone</label>
          <input className={styles.input} type="tel" placeholder="(00) 00000-0000" />
        </div>
 
        <div className={styles.campo}>
          <label className={styles.label}>Endereço</label>
          <input className={styles.input} type="text" placeholder="Rua, número, bairro" />
        </div>
 
        <div className={styles.linha}>
          <div className={styles.campo}>
            <label className={styles.label}>Senha</label>
            <input className={styles.input} type="password" placeholder="••••••••" />
          </div>
          <div className={styles.campo}>
            <label className={styles.label}>Confirmar senha</label>
            <input className={styles.input} type="password" placeholder="••••••••" />
          </div>
        </div>
 
        <button className={styles.btnCad}>Criar conta</button>
 
      </div>
 
   
      <p className={styles.login}>
        Já tem conta?{' '}
        <a href="#" className={styles.link}>Entrar</a>
      </p>
 
    </div>
 
  </main>

    </>
  )
}

export default Cadastro
