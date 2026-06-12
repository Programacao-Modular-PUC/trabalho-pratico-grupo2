import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../Header/Header.module.css';

function Header() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = localStorage.getItem('@Hospedagem:user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  function handleLogout() {
    localStorage.removeItem('@Hospedagem:user');
    setUser(null);
    navigate('/');
  }

  return (
    <aside className={styles.lateral}>
      <div className={styles.lateralLogo}>
        <div className={styles.marca} onClick={() => navigate('/')} style={{cursor:'pointer'}}>PUC STAY</div>
        <div className={styles.slogan}>Hospedagem Ideal</div>
      </div>
 
      <nav className={styles.lateralNav}>
        <div className={styles.rotuloNav}>Menu</div>
        <div className={`${styles.itemNav} ${styles.ativo}`} onClick={() => navigate('/')}>
          <span className={styles.iconeNav}>🏠</span> Início
        </div>
        
        {user?.tipoPerfil === 'ANFITRIAO' && (
          <div className={styles.itemNav} onClick={() => navigate('/Gestao')}>
            <span className={styles.iconeNav}>🏡</span> Gestão Imóveis
          </div>
        )}

        <div className={styles.itemNav} onClick={() => navigate('/reserva')}>
          <span className={styles.iconeNav}>📅</span> Nova Reserva
        </div>

        <div className={styles.itemNav} onClick={() => navigate('/status')}>
          <span className={styles.iconeNav}>🧾</span> Recibos / Status
        </div>
      </nav>
 
      <div className={styles.lateralPerfil}>
        <div className={styles.avatar}>👤</div>
        <div className={styles.rotuloPerf}>Conta</div>
        <div className={styles.nomePerf}>{user ? user.nome : 'Visitante'}</div>
        <div className={styles.statusPerf}>{user ? user.tipoPerfil : 'Entre para acessar tudo'}</div>
        <div className={styles.acoesPerf}>
          {user ? (
            <button className={styles.btnEntrar} onClick={handleLogout}>Sair</button>
          ) : (
            <>
              <button className={styles.btnEntrar} onClick={() => navigate('/Login')}>Entrar</button>
              <button className={styles.btnCad} onClick={() => navigate('/Cadastro')}>Cadastrar</button>
            </>
          )}
        </div>
      </div>
    </aside>
  );
}

export default Header;