import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from "../Components/Header/Header";
import styles from "../Reserva/Reserva.module.css";

function Reserva() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [form, setForm] = useState({
    quarto: 'individual',
    entrada: '',
    saida: '',
    ar: false,
    hidro: false,
    camasSolteiro: 1,
    tipoCama: 'CASAL',
    solicitouBerco: false,
    quantidadeHospedes: 1,
    quantidadeAmbientes: 1
  });

  useEffect(() => {
    const stored = localStorage.getItem('@Hospedagem:user');
    if (!stored) {
      alert("Acesso restrito! Por favor faça login.");
      navigate('/Login');
    } else {
      setUser(JSON.parse(stored));
    }
  }, [navigate]);

  function handleChange(e) {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
  }

  function calcularDiarias() {
    if (!form.entrada || !form.saida) return 0;
    const e = new Date(form.entrada);
    const s = new Date(form.saida);
    let dias = Math.ceil((s - e) / (1000 * 60 * 60 * 24));
    return dias > 0 ? dias : 1;
  }

  function calcularTotalOficial() {
    let valorBase = 100.0; // Valor base padrão simulado do quarto
    let totalQuarto = valorBase;

    if (form.quarto === 'individual') {
      if (parseInt(form.camasSolteiro) > 1) {
        totalQuarto = valorBase + ((parseInt(form.camasSolteiro) - 1) * 50.0);
      }
    } else if (form.quarto === 'duplo') {
      if (form.tipoCama === 'QUEEN') totalQuarto += 80.0;
      if (form.tipoCama === 'KING') totalQuarto += 150.0;
      if (form.solicitouBerco) totalQuarto += 40.0;
    } else if (form.quarto === 'familia') {
      totalQuarto += valorBase * (parseInt(form.quantidadeHospedes) * 0.10);
      if (parseInt(form.quantidadeHospedes) >= 5) {
        totalQuarto *= 0.88; // Desconto progressivo de 12%
      }
    }

    if (form.ar) totalQuarto += 30.0;
    if (form.hidro) totalQuarto += 50.0;

    return totalQuarto * calcularDiarias();
  }

  async function handleSubmit(e) {
    e.preventDefault();
    

    const aluguelPayload = {
      dataEntrada: new Date(form.entrada),
      dataSaida: new Date(form.saida),
      numeroDiarias: calcularDiarias(),
      quantidadeHospedes: form.quarto === 'familia' ? form.quantidadeHospedes : 1,
      cliente: { id: user?.id }
    };

    try {
      const res = await fetch('http://localhost:8080/alugueis', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(aluguelPayload)
      });
      if (res.ok) {
        alert("Reserva persistida e calculada com sucesso!");
        navigate('/status');
      }
    } catch(err) {
      alert("Falha ao salvar no banco de dados.");
    }
  }

  return (
    <div style={{ display: "flex" }}>
      <Header />
      <main className={styles.principal} style={{marginLeft: '240px', width: 'calc(100vw - 240px)'}}>
        <div className={styles.caixa} style={{margin: '40px auto'}}>
          <h1 className={styles.titulo}>Reserva de Hospedagem</h1>
          <p className={styles.sub}>Preencha as regras da Sprint 2</p>

          <form className={styles.form} onSubmit={handleSubmit}>
            <div className={styles.campo}>
              <label className={styles.label}>Tipo de Quarto</label>
              <select className={styles.input} name="quarto" value={form.quarto} onChange={handleChange}>
                <option value="individual">Quarto Individual</option>
                <option value="duplo">Quarto Duplo (Casal)</option>
                <option value="familia">Quarto Família</option>
              </select>
            </div>

            {form.quarto === 'individual' && (
              <div className={styles.campo}>
                <label className={styles.label}>Quantidade de Camas de Solteiro</label>
                <input type="number" name="camasSolteiro" className={styles.input} min="1" value={form.camasSolteiro} onChange={handleChange}/>
              </div>
            )}

            {form.quarto === 'duplo' && (
              <>
                <div className={styles.campo}>
                  <label className={styles.label}>Padrão de Conforto da Cama</label>
                  <select className={styles.input} name="tipoCama" value={form.tipoCama} onChange={handleChange}>
                    <option value="CASAL">Casal Padrão</option>
                    <option value="QUEEN">Queen Size (+R$80)</option>
                    <option value="KING">King Size (+R$150)</option>
                  </select>
                </div>
                <div className={styles.campo}>
                  <label className={styles.label}>
                    <input type="checkbox" name="solicitouBerco" checked={form.solicitouBerco} onChange={handleChange} />
                    Desejo solicitar Berço no quarto (+R$40)
                  </label>
                </div>
              </>
            )}

            {form.quarto === 'familia' && (
              <div className={styles.linha}>
                <div className={styles.campo}>
                  <label className={styles.label}>Nº de Hóspedes</label>
                  <input type="number" name="quantidadeHospedes" className={styles.input} min="1" value={form.quantidadeHospedes} onChange={handleChange}/>
                </div>
                <div className={styles.campo}>
                  <label className={styles.label}>Nº de Ambientes</label>
                  <input type="number" name="quantidadeAmbientes" className={styles.input} min="1" value={form.quantidadeAmbientes} onChange={handleChange}/>
                </div>
              </div>
            )}

            <div className={styles.linha}>
              <div className={styles.campo}>
                <label className={styles.label}>Entrada</label>
                <input type="date" className={styles.input} name="entrada" onChange={handleChange} />
              </div>
              <div className={styles.campo}>
                <label className={styles.label}>Saída</label>
                <input type="date" className={styles.input} name="saida" onChange={handleChange} />
              </div>
            </div>

            <div className={styles.campo}>
              <label className={styles.label}>
                <input type="checkbox" name="ar" checked={form.ar} onChange={handleChange} /> Ar-condicionado (+R$30)
              </label>
            </div>
            <div className={styles.campo}>
              <label className={styles.label}>
                <input type="checkbox" name="hidro" checked={form.hidro} onChange={handleChange} /> Hidromassagem (+R$50)
              </label>
            </div>

            <div className={styles.resumo} style={{background: '#f1f5f9', padding: '15px', borderRadius: '10px'}}>
              <p><strong>Total de Diárias:</strong> {calcularDiarias()}</p>
              <p><strong>Valor Estimado Total:</strong> R$ {calcularTotalOficial().toFixed(2)}</p>
            </div>

            <button className={styles.btnCad} type="submit">Confirmar</button>
          </form>
        </div>
      </main>
    </div>
  );
}

export default Reserva;