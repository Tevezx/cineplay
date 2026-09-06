import { useState } from "react";
import { cadastrarFilme } from "../services/FilmeService";
import styles from "../styles/ModalCadastroFilme.module.css";

export function ModalCadastroFilme({ onClose, onCadastrado }) {

    const [dados, setDados] = useState({
        titulo: "",
        sinopse: "",
        genero: "",
        classificacao: "",
        duracao: 0,
        dataLancamento: "",
        imagem_url: ""
    })

    async function salvarFilme(event) {
        event.preventDefault();
        try {
            await cadastrarFilme(dados);
            onCadastrado?.();
        } catch (error) {
            console.error("Erro ao cadastrar filme:", error);
            alert("Erro ao cadastrar filme: " + (error.response?.data?.message || error.message));
        }
    }

    return (
        <div className={styles.overlay}>
            <div className={styles.modal}>
                <button className={styles.closeBtn} onClick={onClose} type="button">×</button>
                <h2 className={styles.title}>Cadastro de Filme</h2>
                <form onSubmit={salvarFilme}>
                    <input
                        className={styles.input}
                        type="text"
                        placeholder="Título"
                        value={dados.titulo}
                        onChange={(e) => setDados({ ...dados, titulo: e.target.value })}
                    />
                    <textarea
                        className={styles.textarea}
                        placeholder="Sinopse"
                        value={dados.sinopse}
                        onChange={(e) => setDados({ ...dados, sinopse: e.target.value })}
                    />
                    <input
                        className={styles.input}
                        type="text"
                        placeholder="Gênero"
                        value={dados.genero}
                        onChange={(e) => setDados({ ...dados, genero: e.target.value })}
                    />
                    <input
                        className={styles.input}
                        type="text"
                        placeholder="Classificação"
                        value={dados.classificacao}
                        onChange={(e) => setDados({ ...dados, classificacao: e.target.value })}
                    />
                    <input
                        className={styles.input}
                        type="number"
                        placeholder="Duração"
                        value={dados.duracao}
                        onChange={(e) => setDados({ ...dados, duracao: parseInt(e.target.value) })}
                    />
                    <input
                        className={styles.input}
                        type="date"
                        placeholder="Data de Lançamento"
                        value={dados.dataLancamento}
                        onChange={(e) => setDados({ ...dados, dataLancamento: e.target.value })}
                    />
                    <input
                        className={styles.input}
                        type="text"
                        placeholder="URL da Imagem"
                        value={dados.imagem_url}
                        onChange={(e) => setDados({ ...dados, imagem_url: e.target.value })}
                    />
                    <button className={styles.submitBtn} type="submit">
                        Cadastrar Filme
                    </button>
                </form>
            </div>
        </div>
    );
}
