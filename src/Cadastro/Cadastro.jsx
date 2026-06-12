
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../Cadastro/Cadastro.module.css';

function Cadastro() {
  const navigate = useNavigate();
  const [perfil, setPerfil] = useState('CLIENTE');
  const [formData, setFormData] = useState({
    nome: '', cpf: '', email: '', telefone: '', endereco: '', senha: '', confirmarSenha: ''
  });

  function handleChange(e) {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  async function handleSubmit() {
    if (formData.senha !== formData.confirmarSenha) {
      alert("As senhas não coincidem!");
      return;
    }

    const payload = {
      nome: formData.nome,
      cpf: formData.cpf,
      email: formData.email,
      telefone: formData.telefone,
      endereco: formData.endereco,
      senha: formData.senha,
      tipoPerfil: perfil
    };

    try {
      const response = await fetch('http://localhost:8080/clientes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        alert("Conta criada com sucesso!");
        navigate('/Login');
      } else {
        const err = await response.json();
        alert("Erro no cadastro: " + (err.erro || "Verifique os dados"));
      }
    } catch (e) {
      alert("Erro ao conectar com o servidor.");
    }
  }

  return (
    <main className={styles.principal}>
      <div className={styles.caixa}>
        <h1 className={styles.titulo}>Crie sua conta</h1>
        <p className={styles.sub}>Cadastre-se grátis e comece agora</p>
        
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
          <div className={styles.linha}>
            <div className={styles.campo}>
              <label className={styles.label}>Nome</label>
              <input className={styles.input} type="text" name="nome" onChange={handleChange} placeholder="Seu nome" />
            </div>
            <div className={styles.campo}>
              <label className={styles.label}>CPF</label>
              <input className={styles.input} type="text" name="cpf" onChange={handleChange} placeholder="000.000.000-00" />
            </div>
          </div>
          <div className={styles.campo}>
            <label className={styles.label}>E-mail</label>
            <input className={styles.input} type="email" name="email" onChange={handleChange} placeholder="seu@email.com" />
          </div>
          <div className={styles.campo}>
            <label className={styles.label}>Telefone</label>
            <input className={styles.input} type="tel" name="telefone" onChange={handleChange} placeholder="(00) 00000-0000" />
          </div>
          <div className={styles.campo}>
            <label className={styles.label}>Endereço</label>
            <input className={styles.input} type="text" name="endereco" onChange={handleChange} placeholder="Rua, número, bairro" />
          </div>
          <div className={styles.linha}>
            <div className={styles.campo}>
              <label className={styles.label}>Senha</label>
              <input className={styles.input} type="password" name="senha" onChange={handleChange} placeholder="••••••••" />
            </div>
            <div className={styles.campo}>
              <label className={styles.label}>Confirmar senha</label>
              <input className={styles.input} type="password" name="confirmarSenha" onChange={handleChange} placeholder="••••••••" />
            </div>
          </div>
          <button className={styles.btnCad} onClick={handleSubmit}>Criar conta</button>
        </div>
        <p className={styles.login}>
          Já tem conta? <a href="#" onClick={() => navigate('/Login')} className={styles.link}>Entrar</a>
        </p>
      </div>
    </main>
  );
}

export default Cadastro;