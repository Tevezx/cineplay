import { useState } from "react";
import { editarFilme } from "../../services/FilmeService";
import { useLoading } from "../../context/LoadingContext";
import styles from "../../styles/ModalCadastroFilme.module.css";

export function ModalEditar({ filme, onClose, onEditado }){

    const [dados, setDados] = useState({
        titulo: filme.titulo,
        sinopse: filme.sinopse,
        genero: filme.genero,
        classificacao: filme.classificacao,
        duracao: filme.duracao,
        dataLancamento: filme.dataLancamento,
        imagem_url: filme.imagem_url
    })

    const { startLoading, stopLoading } = useLoading();

    async function salvarFilme(event){
        event.preventDefault();
        try{
            startLoading();
            await editarFilme(filme.id, dados);
            onEditado?.();
        } catch (error) {
            console.error("Erro ao editar filme:", error);
            alert("Erro ao editar filme: " + (error.response?.data?.message || error.message));
        } finally {
            stopLoading();
        }
    }

    return (
        <div className={styles.overlay}>
            <div className={styles.modal}>
                <button className={styles.closeBtn} onClick={onClose} type="button">×</button>
                <h2 className={styles.title}>Editar Filme</h2>
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
                        Salvar Alterações
                    </button>
                </form>
            </div>
        </div>
    );
}
