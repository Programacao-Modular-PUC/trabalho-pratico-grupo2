import React, { useState, useEffect } from "react"; 
import styles from "./Gestao.module.css";
import Header from "../Components/Header/Header";

function Gestao() {

  const frases = [
    "Controle reservas facilmente",
    "Organize espaços da sua residência",
    "Acompanhe tudo em um só lugar",
  ];
  const [indexFrase, setIndexFrase] = useState(0);
  const [texto, setTexto] = useState("");
  const [indexLetra, setIndexLetra] = useState(0);

  const [residencias, setResidencias] = useState([]);
  const [totalReservas, setTotalReservas] = useState(0);
  const [totalReceita, setTotalReceita] = useState(0);


  const [modalAberto, setModalAberto] = useState(false);
  const [novaResidencia, setNovaResidencia] = useState({
    endereco: "",
    contato: ""
  });


  useEffect(() => {
    const fraseAtual = frases[indexFrase];

    if (indexLetra < fraseAtual.length) {
      const timeout = setTimeout(() => {
        setTexto((prev) => prev + fraseAtual[indexLetra]);
        setIndexLetra((prev) => prev + 1);
      }, 50);

      return () => clearTimeout(timeout);
    } else {
      const timeout = setTimeout(() => {
        setTexto("");
        setIndexLetra(0);
        setIndexFrase((prev) => (prev + 1) % frases.length);
      }, 1000);

      return () => clearTimeout(timeout);
    }
  }, [indexLetra, indexFrase]);

  
  function carregarDadosDoBanco() {
    fetch("http://localhost:8080/residencias")
      .then((res) => res.json())
      .then((data) => setResidencias(data))
      .catch((err) => console.error("Erro ao buscar residências:", err));

    fetch("http://localhost:8080/alugueis")
      .then((res) => res.json())
      .then((data) => {
        setTotalReservas(data.length);
        const receitaSomada = data.reduce((acc, curr) => acc + (curr.valorFinal || 0), 0);
        setTotalReceita(receitaSomada);
      })
      .catch((err) => console.error("Erro ao buscar aluguéis:", err));
  }

 
  useEffect(() => {
    carregarDadosDoBanco();
  }, []);


  function handleInputChange(e) {
    setNovaResidencia({
      ...novaResidencia,
      [e.target.name]: e.target.value
    });
  }

  
  async function handleCadastrarResidencia(e) {
    e.preventDefault();

    if (!novaResidencia.endereco || !novaResidencia.contato) {
      alert("Por favor, preencha todos os campos!");
      return;
    }


    const payload = {
      endereco: novaResidencia.endereco,
      contato: novaResidencia.contato,
      listaQuartos: []
    };

    try {
      const response = await fetch("http://localhost:8080/residencias", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        alert("Residência cadastrada com sucesso!");
        setModalAberto(false); 
        setNovaResidencia({ endereco: "", contato: "" }); 
        carregarDadosDoBanco(); 
      } else {
        alert("Erro ao cadastrar a residência.");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("Erro.");
    }
  }

  return (
    <div style={{ display: "flex" }}>
      <Header />

      <main className={styles.principal}>
        
        <section className={styles.topo}>
          <h1>Gerencie suas reservas</h1>
          <p className={styles.subtitulo}>
            {texto}
            <span className={styles.cursor}>|</span>
          </p>
          <div className={styles.topoCta}>
            <button className={styles.btnPrimario} onClick={() => setModalAberto(true)}>
              Criar residência
            </button>
          </div>
        </section>

      
        {modalAberto && (
          <div style={{
            position: "fixed", top: 0, left: 0, width: "100vw", height: "100vh",
            backgroundColor: "rgba(0,0,0,0.4)", display: "flex", alignItems: "center",
            justifyContent: "center", zIndex: 1000
          }}>
            <div style={{
              background: "white", padding: "30px", borderRadius: "16px",
              width: "100%", maxWidth: "400px", boxShadow: "0 10px 25px rgba(0,0,0,0.1)"
            }}>
              <h2 style={{ fontFamily: "Playfair Display, serif", marginBottom: "15px", color: "#1d4ed8" }}>Nova Residência</h2>
              <form onSubmit={handleCadastrarResidencia} style={{ display: "flex", flexDirection: "column", gap: "12px" }}>
                <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
                  <label style={{ fontSize: "0.85rem", fontWeight: 500, color: "#475569" }}>Endereço Completo</label>
                  <input 
                    type="text" name="endereco" value={novaResidencia.endereco} onChange={handleInputChange}
                    placeholder="Ex: Av. dos Andradas, 3000 - BH" 
                    style={{ padding: "10px", border: "1.5px solid #dbeafe", borderRadius: "8px", outline: "none" }}
                  />
                </div>
                <div style={{ display: "flex", flexDirection: "column", gap: "4px" }}>
                  <label style={{ fontSize: "0.85rem", fontWeight: 500, color: "#475569" }}>Telefone / Contato</label>
                  <input 
                    type="text" name="contato" value={novaResidencia.contato} onChange={handleInputChange}
                    placeholder="Ex: (31) 99999-9999" 
                    style={{ padding: "10px", border: "1.5px solid #dbeafe", borderRadius: "8px", outline: "none" }}
                  />
                </div>
                <div style={{ display: "flex", gap: "10px", marginTop: "10px" }}>
                  <button type="submit" className={styles.btnPrimario} style={{ flex: 1, padding: "10px 0" }}>Salvar no Banco</button>
                  <button type="button" onClick={() => setModalAberto(false)} style={{
                    flex: 1, padding: "10px 0", background: "#f1f5f9", color: "#475569",
                    border: "1px solid #cbd5e1", borderRadius: "12px", cursor: "pointer"
                  }}>Cancelar</button>
                </div>
              </form>
            </div>
          </div>
        )}

      
        <div className={styles.barraStats}>
          <div className={styles.stat}>
            <span className={styles.statVal}>{residencias.length}</span>
            <span className={styles.statLabel}>Residências</span>
          </div>
          <div className={styles.stat}>
            <span className={styles.statVal}>{totalReservas}</span>
            <span className={styles.statLabel}>Reservas</span>
          </div>
          <div className={styles.stat}>
            <span className={styles.statVal}>R$ {totalReceita.toFixed(2)}</span>
            <span className={styles.statLabel}>Receita Real</span>
          </div>
        </div>

       
        <section className={styles.secao}>
          <div className={styles.secaoTopo}>
            <h2 className={styles.secaoTitulo}>
              O que você pode <span>fazer</span>
            </h2>
            <p className={styles.secaoSub}>
              Gerencie todos os aspectos das suas residências de forma simples.
            </p>
          </div>

          <div className={styles.gradeCards}>
            <div className={styles.card}>
              <span className={styles.cardIcone}>🏠</span>
              <h3 className={styles.cardTitulo}>Residências</h3>
              <p className={styles.cardDesc}>
                Cadastre e organize seus imóveis.
              </p>
            </div>

            <div className={styles.card}>
              <span className={styles.cardIcone}>📅</span>
              <h3 className={styles.cardTitulo}>Reservas</h3>
              <p className={styles.cardDesc}>Controle reservas facilmente.</p>
            </div>

            <div className={styles.card}>
              <span className={styles.cardIcone}>💰</span>
              <h3 className={styles.cardTitulo}>Financeiro</h3>
              <p className={styles.cardDesc}>Acompanhe ganhos e despesas.</p>
            </div>
          </div>
        </section>

        
        <section className={styles.secao}>
          <div className={styles.secaoTopo}>
            <h2 className={styles.secaoTitulo}>
              Suas <span>residências</span>
            </h2>
          </div>

          <div className={styles.gradeRes}>
            {residencias.length === 0 ? (
              <p style={{ color: "#64748b", fontSize: "14px" }}>
                Nenhuma residência cadastrada no MySQL local.
              </p>
            ) : (
              residencias.map((res) => (
                <div key={res.id} className={`${styles.cardRes} ${styles.r1}`}>
                  <div className={styles.resImg}>🏡</div>
                  <div className={styles.resCorpo}>
                    <h3 className={styles.resNome}>{res.endereco || "Residência Cadastrada"}</h3>
                    <p className={styles.resLoc}>Contato: {res.contato || "Não informado"}</p>
                  </div>
                </div>
              ))
            )}
          </div>
        </section>

       
        <footer className={styles.rodape}>
          <div className={styles.rodapeMarca}>Gestão Residencial</div>
          <div className={styles.rodapeCopy}>
            © 2026 Todos os direitos reservados
          </div>
        </footer>
      </main>
    </div>
  );
}

export default Gestao;