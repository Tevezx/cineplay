import { useEffect, useState } from "react";
import { listarFilmes } from "../services/FilmeService";
import styles from "../styles/FilmesCadastrados.module.css";
import { ModalCadastroFilme } from "./ModalCadastroFilme";

export function FilmesCadastrados() {
    const [filmes, setFilmes] = useState([]);
    const [modalAberto, setModalAberto] = useState(false);

    useEffect(() => {
        listarFilmes().then(setFilmes);
    }, []);

    function cadastrarFilme() {
        setModalAberto(true);
    }

    function fecharModal() {
        setModalAberto(false);
    }

    function filmeCadastrado() {
        setModalAberto(false);
        listarFilmes().then(setFilmes);
    }

    return (
        <div className={styles.wrapper}>
            <div className={styles.header}>
                <h1 className={styles.title}>Filmes Cadastrados</h1>
                <button className={styles.button} onClick={cadastrarFilme}>
                    Cadastrar Novo Filme
                </button>
            </div>
            <div className={styles.grid}>
                {filmes.map((filme) => (
                    <div key={filme.id} className={styles.card}>
                        <img
                            className={styles.poster}
                            src={filme.imagem_url}
                            alt={filme.titulo}
                        />
                        <div className={styles.info}>
                            <h2 className={styles.filmeTitulo}>{filme.titulo}</h2>
                            <p className={styles.sinopse}>{filme.sinopse}</p>
                            <div className={styles.meta}>
                                <span>{filme.genero}</span>
                                <span>{filme.classificacao}</span>
                                <span>{filme.duracao} min</span>
                                <span>{filme.dataLancamento}</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            {filmes.length === 0 && (
                <p className={styles.noFilmes}>Nenhum filme cadastrado.</p>
            )}
            {modalAberto && (
                <ModalCadastroFilme onClose={fecharModal} onCadastrado={filmeCadastrado} />
            )}
        </div>
    );
}
