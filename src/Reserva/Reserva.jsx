import React, { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from "../Components/Header/Header";
import styles from "../Reserva/Reserva.module.css";

function Reserva() {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [servicos, setServicos] = useState([]);
  const [pacotes, setPacotes] = useState([]);
  const [pacoteSelecionadoId, setPacoteSelecionadoId] = useState('');
  const [servicosSelecionados, setServicosSelecionados] = useState([]);
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
      alert("Acesso restrito! Por favor faca login.");
      navigate('/Login');
    } else {
      setUser(JSON.parse(stored));
    }
  }, [navigate]);

  useEffect(() => {
    async function carregarPacotes() {
      try {
        const [servicosRes, pacotesRes] = await Promise.all([
          fetch('http://localhost:8080/api/servicos'),
          fetch('http://localhost:8080/api/pacotes')
        ]);

        const servicosData = await servicosRes.json();
        const pacotesData = await pacotesRes.json();

        setServicos(servicosData);
        setPacotes(pacotesData);

        if (pacotesData.length > 0) {
          const pacotePadrao = pacotesData.find((pacote) => !pacote.personalizado) || pacotesData[0];
          setPacoteSelecionadoId(String(pacotePadrao.id));
          setServicosSelecionados((pacotePadrao.servicos || []).map((servico) => servico.id));
        }
      } catch (err) {
        setServicos([]);
        setPacotes([]);
      }
    }

    carregarPacotes();
  }, []);

  const pacoteSelecionado = useMemo(
    () => pacotes.find((pacote) => String(pacote.id) === String(pacoteSelecionadoId)),
    [pacotes, pacoteSelecionadoId]
  );

  const pacotePersonalizado = pacoteSelecionado?.personalizado;

  function handleChange(e) {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
  }

  function handlePacoteChange(e) {
    const pacoteId = e.target.value;
    const pacote = pacotes.find((item) => String(item.id) === String(pacoteId));

    setPacoteSelecionadoId(pacoteId);
    setServicosSelecionados((pacote?.servicos || []).map((servico) => servico.id));
  }

  function handleServicoChange(servicoId) {
    if (!pacotePersonalizado) return;

    setServicosSelecionados((atuais) => (
      atuais.includes(servicoId)
        ? atuais.filter((id) => id !== servicoId)
        : [...atuais, servicoId]
    ));
  }

  function calcularDiarias() {
    if (!form.entrada || !form.saida) return 0;
    const e = new Date(form.entrada);
    const s = new Date(form.saida);
    let dias = Math.ceil((s - e) / (1000 * 60 * 60 * 24));
    return dias > 0 ? dias : 1;
  }

  function calcularTotalServicos() {
    const diarias = Math.max(calcularDiarias(), 1);
    const hospedes = Math.max(Number(form.quantidadeHospedes) || 1, 1);

    return servicos
      .filter((servico) => servicosSelecionados.includes(servico.id))
      .reduce((total, servico) => {
        if (servico.tipoCobranca === 'POR_DIARIA') {
          return total + (servico.preco * diarias);
        }

        if (servico.tipoCobranca === 'POR_HOSPEDE') {
          return total + (servico.preco * hospedes);
        }

        return total + servico.preco;
      }, 0);
  }

  function calcularTotalOficial() {
    let valorBase = 100.0;
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
        totalQuarto *= 0.88;
      }
    }

    if (form.ar) totalQuarto += 30.0;
    if (form.hidro) totalQuarto += 50.0;

    return (totalQuarto * calcularDiarias()) + calcularTotalServicos();
  }

  async function handleSubmit(e) {
    e.preventDefault();

    const aluguelPayload = {
      dataEntrada: new Date(form.entrada),
      dataSaida: new Date(form.saida),
      numeroDiarias: calcularDiarias(),
      quantidadeHospedes: form.quarto === 'familia' ? Number(form.quantidadeHospedes) : 1,
      cliente: { id: user?.id },
      servicosAdicionais: servicosSelecionados.map((id) => ({ id }))
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
      } else {
        alert("Nao foi possivel salvar a reserva.");
      }
    } catch (err) {
      alert("Falha ao salvar no banco de dados.");
    }
  }

  return (
    <div style={{ display: "flex" }}>
      <Header />
      <main className={styles.principal} style={{ marginLeft: '240px', width: 'calc(100vw - 240px)' }}>
        <div className={styles.caixa} style={{ margin: '40px auto' }}>
          <h1 className={styles.titulo}>Reserva de Hospedagem</h1>
          <p className={styles.sub}>Monte sua hospedagem com servicos adicionais</p>

          <form className={styles.form} onSubmit={handleSubmit}>
            <div className={styles.campo}>
              <label className={styles.label}>Tipo de Quarto</label>
              <select className={styles.input} name="quarto" value={form.quarto} onChange={handleChange}>
                <option value="individual">Quarto Individual</option>
                <option value="duplo">Quarto Duplo (Casal)</option>
                <option value="familia">Quarto Familia</option>
              </select>
            </div>

            {form.quarto === 'individual' && (
              <div className={styles.campo}>
                <label className={styles.label}>Quantidade de Camas de Solteiro</label>
                <input type="number" name="camasSolteiro" className={styles.input} min="1" value={form.camasSolteiro} onChange={handleChange} />
              </div>
            )}

            {form.quarto === 'duplo' && (
              <>
                <div className={styles.campo}>
                  <label className={styles.label}>Padrao de Conforto da Cama</label>
                  <select className={styles.input} name="tipoCama" value={form.tipoCama} onChange={handleChange}>
                    <option value="CASAL">Casal Padrao</option>
                    <option value="QUEEN">Queen Size (+R$80)</option>
                    <option value="KING">King Size (+R$150)</option>
                  </select>
                </div>
                <label className={styles.checkLinha}>
                  <input type="checkbox" name="solicitouBerco" checked={form.solicitouBerco} onChange={handleChange} />
                  Desejo solicitar Berco no quarto (+R$40)
                </label>
              </>
            )}

            {form.quarto === 'familia' && (
              <div className={styles.linha}>
                <div className={styles.campo}>
                  <label className={styles.label}>Numero de Hospedes</label>
                  <input type="number" name="quantidadeHospedes" className={styles.input} min="1" value={form.quantidadeHospedes} onChange={handleChange} />
                </div>
                <div className={styles.campo}>
                  <label className={styles.label}>Numero de Ambientes</label>
                  <input type="number" name="quantidadeAmbientes" className={styles.input} min="1" value={form.quantidadeAmbientes} onChange={handleChange} />
                </div>
              </div>
            )}

            <div className={styles.linha}>
              <div className={styles.campo}>
                <label className={styles.label}>Entrada</label>
                <input type="date" className={styles.input} name="entrada" onChange={handleChange} />
              </div>
              <div className={styles.campo}>
                <label className={styles.label}>Saida</label>
                <input type="date" className={styles.input} name="saida" onChange={handleChange} />
              </div>
            </div>

            <label className={styles.checkLinha}>
              <input type="checkbox" name="ar" checked={form.ar} onChange={handleChange} />
              Ar-condicionado (+R$30)
            </label>
            <label className={styles.checkLinha}>
              <input type="checkbox" name="hidro" checked={form.hidro} onChange={handleChange} />
              Hidromassagem (+R$50)
            </label>

            <section className={styles.pacotes}>
              <div className={styles.campo}>
                <label className={styles.label}>Pacote de Hospedagem</label>
                <select className={styles.input} value={pacoteSelecionadoId} onChange={handlePacoteChange}>
                  <option value="">Sem pacote</option>
                  {pacotes.map((pacote) => (
                    <option key={pacote.id} value={pacote.id}>{pacote.nome}</option>
                  ))}
                </select>
              </div>

              <div className={styles.listaServicos}>
                {servicos.length === 0 ? (
                  <p className={styles.avisoServicos}>Servicos indisponiveis no momento.</p>
                ) : (
                  servicos.map((servico) => (
                    <label key={servico.id} className={styles.servicoItem}>
                      <input
                        type="checkbox"
                        checked={servicosSelecionados.includes(servico.id)}
                        disabled={!pacotePersonalizado}
                        onChange={() => handleServicoChange(servico.id)}
                      />
                      <span>
                        <strong>{servico.nome}</strong>
                        <small>R$ {servico.preco.toFixed(2)} - {servico.tipoCobranca}</small>
                      </span>
                    </label>
                  ))
                )}
              </div>
            </section>

            <div className={styles.resumo}>
              <p><strong>Total de Diarias:</strong> {calcularDiarias()}</p>
              <p><strong>Servicos:</strong> R$ {calcularTotalServicos().toFixed(2)}</p>
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
