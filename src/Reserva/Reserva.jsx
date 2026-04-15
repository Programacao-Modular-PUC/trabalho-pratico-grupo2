import { useState } from "react";
import styles from "../Reserva/Reserva.module.css";

function Reserva() {
  const [form, setForm] = useState({
    cliente: "",
    residencia: "",
    quarto: "individual",
    entrada: "",
    saida: "",
    ar: false,
    hidro: false
  });

  function handleChange(e) {
    const { name, value, type, checked } = e.target;

    setForm({
      ...form,
      [name]: type === "checkbox" ? checked : value
    });
  }

  function calcularDiarias() {
    if (!form.entrada || !form.saida) return 0;

    const e = new Date(form.entrada);
    const s = new Date(form.saida);

    let dias = Math.ceil((s - e) / (1000 * 60 * 60 * 24));
    return dias > 0 ? dias : 1;
  }

  const diarias = calcularDiarias();

  let valorBase = form.quarto === "casal" ? 150 : 100;
  let adicionais = 0;

  if (form.ar) adicionais += 30;
  if (form.hidro) adicionais += 50;

  const total = (valorBase + adicionais) * diarias;

  function handleSubmit(e) {
    e.preventDefault();
    alert("Reserva realizada com sucesso!");
  }

  return (
    <main className={styles.principal}>
      <div className={styles.caixa}>
        <h1 className={styles.titulo}>Reserva de Hospedagem</h1>
        <p className={styles.sub}>Preencha os dados da sua estadia</p>

        <p style={{ textAlign: "center", fontSize: "12px", color: "#64748b" }}>
          Sistema de reservas - versão 1.1
        </p>

        <form className={styles.form} onSubmit={handleSubmit}>

          <div className={styles.campo}>
            <label className={styles.label}>Cliente</label>
            <input className={styles.input} name="cliente" onChange={handleChange} />
          </div>

          <div className={styles.campo}>
            <label className={styles.label}>Residência</label>
            <input className={styles.input} name="residencia" onChange={handleChange} />
          </div>

          <div className={styles.campo}>
            <label className={styles.label}>Tipo de Quarto</label>
            <select className={styles.input} name="quarto" onChange={handleChange}>
              <option value="individual">Individual</option>
              <option value="casal">Casal</option>
            </select>
          </div>

          <div className={styles.linha}>
            <div className={styles.campo}>
              <label className={styles.label}>Entrada</label>
              <input type="datetime-local" className={styles.input} name="entrada" onChange={handleChange} />
            </div>

            <div className={styles.campo}>
              <label className={styles.label}>Saída</label>
              <input type="datetime-local" className={styles.input} name="saida" onChange={handleChange} />
            </div>
          </div>

          <div className={styles.campo}>
            <label className={styles.label}>
              <input type="checkbox" name="ar" onChange={handleChange} />
              Ar-condicionado (+30)
            </label>
          </div>

          <div className={styles.campo}>
            <label className={styles.label}>
              <input type="checkbox" name="hidro" onChange={handleChange} />
              Hidromassagem (+50)
            </label>
          </div>

          <div className={styles.campo}>
            <p><strong>Diárias:</strong> {diarias}</p>
            <p><strong>Total:</strong> R$ {total}</p>
          </div>

          <button className={styles.btnCad} type="submit">
            Confirmar Reserva
          </button>

        </form>
      </div>
    </main>
  );
}

export default Reserva;