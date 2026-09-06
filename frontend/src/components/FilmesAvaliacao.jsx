import { listarFilmes } from "../services/FilmeService";
import { listarUsuarios } from "../services/UsuarioService";
import {
    buscarAvaliacoes,
    avaliarFilme,
    atualizarAvaliacao,
    deletarAvaliacao,
} from "../services/AvaliacaoService";

import { useState, useEffect } from "react";
import styles from "../styles/FilmesCadastrados.module.css";
import avaliacaoStyles from "../styles/FilmesAvaliacao.module.css";

const NOTAS = [1, 2, 3, 4, 5];

export function FilmesAvaliacao() {
    const [filmes, setFilmes] = useState([]);
    const [avaliacoes, setAvaliacoes] = useState([]);
    const [usuarios, setUsuarios] = useState([]);
    const [rascunhos, setRascunhos] = useState({});

    const usuario = JSON.parse(localStorage.getItem("usuario") || "null");

    useEffect(() => {
        listarFilmes().then(setFilmes);
        listarUsuarios().then(setUsuarios);
        buscarAvaliacoes().then(setAvaliacoes);
    }, []);

    function nomeDoUsuario(idUsuario) {
        if (usuario && idUsuario === usuario.id) return "Você";
        return usuarios.find((u) => u.id === idUsuario)?.nome ?? "Usuário";
    }

    async function enviarAvaliacao(filme, rascunho, minhaAvaliacao) {
        const payload = {
            id_usuario: usuario.id,
            id_filme: filme.id,
            nota: Number(rascunho.nota),
            comentario: rascunho.comentario,
        };

        try {
            if (minhaAvaliacao) {
                await atualizarAvaliacao(usuario.id, filme.id, payload);
            } else {
                await avaliarFilme(payload);
            }
            buscarAvaliacoes().then(setAvaliacoes);
        } catch (error) {
            console.error("Erro ao avaliar filme:", error);
            alert("Erro ao avaliar filme: " + (error.response?.data?.message || error.message));
        }
    }

    async function removerAvaliacao(filme) {
        try {
            await deletarAvaliacao(usuario.id, filme.id);
            buscarAvaliacoes().then(setAvaliacoes);
        } catch (error) {
            console.error("Erro ao remover avaliação:", error);
            alert("Erro ao remover avaliação: " + (error.response?.data?.message || error.message));
        }
    }

    return (
        <div className={styles.wrapper}>
            <div className={styles.header}>
                <h1 className={styles.title}>Avaliar Filmes</h1>
            </div>
            {!usuario && (
                <p className={styles.noFilmes}>Faça login para avaliar os filmes.</p>
            )}
            <div className={styles.grid}>
                {filmes.map((filme) => {
                    const avaliacoesDoFilme = avaliacoes.filter((a) => a.id_filme === filme.id);
                    const media = avaliacoesDoFilme.length
                        ? (avaliacoesDoFilme.reduce((soma, a) => soma + a.nota, 0) / avaliacoesDoFilme.length).toFixed(1)
                        : null;
                    const minhaAvaliacao = usuario && avaliacoesDoFilme.find((a) => a.id_usuario === usuario.id);
                    const rascunho = rascunhos[filme.id] ?? {
                        nota: minhaAvaliacao?.nota ?? 5,
                        comentario: minhaAvaliacao?.comentario ?? "",
                    };

                    return (
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
                                <p className={avaliacaoStyles.mediaNota}>
                                    {media ? `Nota média: ${media} (${avaliacoesDoFilme.length} avaliações)` : "Ainda sem avaliações"}
                                </p>
                                {avaliacoesDoFilme.length > 0 && (
                                    <ul className={avaliacaoStyles.listaComentarios}>
                                        {avaliacoesDoFilme.map((a) => (
                                            <li key={a.id_usuario} className={avaliacaoStyles.comentarioItem}>
                                                <strong>{nomeDoUsuario(a.id_usuario)}</strong> ({a.nota}/5): {a.comentario}
                                            </li>
                                        ))}
                                    </ul>
                                )}
                                {usuario && (
                                    <div className={avaliacaoStyles.avaliacaoForm}>
                                        <select
                                            className={avaliacaoStyles.notaSelect}
                                            value={rascunho.nota}
                                            onChange={(e) =>
                                                setRascunhos((atual) => ({
                                                    ...atual,
                                                    [filme.id]: { ...rascunho, nota: e.target.value },
                                                }))
                                            }
                                        >
                                            {NOTAS.map((nota) => (
                                                <option key={nota} value={nota}>
                                                    {nota} estrela{nota > 1 ? "s" : ""}
                                                </option>
                                            ))}
                                        </select>
                                        <textarea
                                            className={avaliacaoStyles.comentario}
                                            placeholder="Comentário"
                                            value={rascunho.comentario}
                                            onChange={(e) =>
                                                setRascunhos((atual) => ({
                                                    ...atual,
                                                    [filme.id]: { ...rascunho, comentario: e.target.value },
                                                }))
                                            }
                                        />
                                        <div className={styles.acoes}>
                                            <button
                                                className={styles.editBtn}
                                                onClick={() => enviarAvaliacao(filme, rascunho, minhaAvaliacao)}
                                                type="button"
                                            >
                                                {minhaAvaliacao ? "Atualizar" : "Avaliar"}
                                            </button>
                                            {minhaAvaliacao && (
                                                <button
                                                    className={styles.deleteBtn}
                                                    onClick={() => removerAvaliacao(filme)}
                                                    type="button"
                                                >
                                                    Remover
                                                </button>
                                            )}
                                        </div>
                                    </div>
                                )}
                            </div>
                        </div>
                    );
                })}
            </div>
            {filmes.length === 0 && (
                <p className={styles.noFilmes}>Nenhum filme cadastrado.</p>
            )}
        </div>
    );
}
