import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../Login/Login.module.css';

function Login() {
  const navigate = useNavigate();
  const [perfil, setPerfil] = useState('CLIENTE');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  async function handleLogin() {
    try {
      const response = await fetch('http://localhost:8080/clientes/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha, tipoPerfil: perfil })
      });

      if (response.ok) {
        const user = await response.json();
        localStorage.setItem('@Hospedagem:user', JSON.stringify(user));
        alert(`Logado com sucesso como ${user.nome}!`);
        
        if (user.tipoPerfil === 'ANFITRIAO') {
          navigate('/Gestao');
        } else {
          navigate('/');
        }
      } else {
        const err = await response.json();
        alert(err.erro || "Falha na autenticação.");
      }
    } catch (e) {
      alert("Erro de conexão.");
    }
  }

  return (
    <main className={styles.principal}>
      <div className={styles.caixa}>
        <h1 className={styles.titulo}>Bem-vindo de volta</h1>
        <p className={styles.sub}>Entre na sua conta para continuar</p>
   
        <div className={styles.tipoCont}>
          <button 
            className={`${styles.tipo} ${perfil === 'ANFITRIAO' ? styles.tipoAtivo : ''}`}
            onClick={() => setPerfil('ANFITRIAO')}
          >🏠 Anfitrião</button>
          <button 
            className={`${styles.tipo} ${perfil === 'CLIENTE' ? styles.tipoAtivo : ''}`}
            onClick={() => setPerfil('CLIENTE')}
          >🧳 Cliente</button>
        </div>
   
        <div className={styles.form}>
          <div className={styles.campo}>
            <label className={styles.label}>E-mail</label>
            <input className={styles.input} type="email" placeholder="seu@email.com" value={email} onChange={e => setEmail(e.target.value)} />
          </div>
          <div className={styles.campo}>
            <label className={styles.label}>Senha</label>
            <input className={styles.input} type="password" placeholder="••••••••" value={senha} onChange={e => setSenha(e.target.value)} />
          </div>
          <button className={styles.btnEntrar} onClick={handleLogin}>Entrar</button>
        </div>
        <p className={styles.cadastro}>
          Não tem conta? <a href="#" onClick={() => navigate('/Cadastro')} className={styles.link}>Cadastre-se grátis</a>
        </p>
      </div>
    </main>
  );
}

export default Login;