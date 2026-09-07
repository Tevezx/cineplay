import { useEffect, useState } from "react";
import { listarFilmes, deletarFilme } from "../services/FilmeService";
import { useLoading } from "../context/LoadingContext";
import styles from "../styles/FilmesCadastrados.module.css";
import { ModalCadastroFilme } from "./modais/ModalCadastroFilme";
import { ModalExclusao } from "./modais/ModalExclusão";
import { ModalEditar } from "./modais/ModalEditar";

export function FilmesCadastrados() {
    const [filmes, setFilmes] = useState([]);
    const [modalAberto, setModalAberto] = useState(false);
    const [filmeParaExcluir, setFilmeParaExcluir] = useState(null);
    const [filmeParaEditar, setFilmeParaEditar] = useState(null);
    const { startLoading, stopLoading } = useLoading();

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
        startLoading();
        listarFilmes().then(setFilmes).finally(stopLoading);
    }

    function abrirExclusao(filme) {
        setFilmeParaExcluir(filme);
    }

    function fecharExclusao() {
        setFilmeParaExcluir(null);
    }

    async function confirmarExclusao() {
        try {
            startLoading();
            await deletarFilme(filmeParaExcluir.id);
            setFilmeParaExcluir(null);
            const filmesAtualizados = await listarFilmes();
            setFilmes(filmesAtualizados);
        } finally {
            stopLoading();
        }
    }

    function abrirEdicao(filme) {
        setFilmeParaEditar(filme);
    }

    function fecharEdicao() {
        setFilmeParaEditar(null);
    }

    function filmeEditado() {
        setFilmeParaEditar(null);
        startLoading();
        listarFilmes().then(setFilmes).finally(stopLoading);
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
                            <div className={styles.acoes}>
                                <button className={styles.editBtn} onClick={() => abrirEdicao(filme)}>
                                    Editar
                                </button>
                                <button className={styles.deleteBtn} onClick={() => abrirExclusao(filme)}>
                                    Excluir
                                </button>
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
            {filmeParaExcluir && (
                <ModalExclusao onClose={fecharExclusao} onExcluido={confirmarExclusao} />
            )}
            {filmeParaEditar && (
                <ModalEditar filme={filmeParaEditar} onClose={fecharEdicao} onEditado={filmeEditado} />
            )}
        </div>
    );
}
