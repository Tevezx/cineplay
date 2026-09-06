import { useEffect, useState } from "react";
import { listarFilmes, deletarFilme } from "../services/FilmeService";
import styles from "../styles/FilmesCadastrados.module.css";
import { ModalCadastroFilme } from "./modais/ModalCadastroFilme";
import { ModalExclusao } from "./modais/ModalExclusão";

export function FilmesCadastrados() {
    const [filmes, setFilmes] = useState([]);
    const [modalAberto, setModalAberto] = useState(false);
    const [filmeParaExcluir, setFilmeParaExcluir] = useState(null);

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

    function abrirExclusao(filme) {
        setFilmeParaExcluir(filme);
    }

    function fecharExclusao() {
        setFilmeParaExcluir(null);
    }

    async function confirmarExclusao() {
        await deletarFilme(filmeParaExcluir.id);
        setFilmeParaExcluir(null);
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
                            <button className={styles.deleteBtn} onClick={() => abrirExclusao(filme)}>
                                Excluir
                            </button>
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
            {filmeParaExcluir && (
                <ModalExclusao onClose={fecharExclusao} onExcluido={confirmarExclusao} />
            )}
        </div>
    );
}
