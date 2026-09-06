import { useState } from "react";
import { FilmeService } from "../services/FilmeService";

export function ModalCadastroFilme(){

    const [dados, setDados] = useState({
        titulo: "",
        sinopse: "",
        genero: "",
        classificacao: "",
        duracao: 0,
        dataLancamento: "",
        imagem_url: ""
    })

    function cadastrarFilme(){
        try{
            FilmeService.cadastrarFilme({
            titulo: dados.titulo,
            sinopse: dados.sinopse,
            genero: dados.genero,
            classificacao: dados.classificacao,
            duracao: dados.duracao,
            dataLancamento: dados.dataLancamento,
            imagem_url: dados.imagem_url
        });

        console.log("Filme cadastrado com sucesso:", dados);

        } catch (error) {
            console.error("Erro ao cadastrar filme:", error);
        }
    }

    return (
        <div>
            <form>
                <input
                    type="text"
                    placeholder="Título"
                    value={dados.titulo}
                    onChange={(e) => setDados({ ...dados, titulo: e.target.value })}
                />
                <textarea
                    placeholder="Sinopse"
                    value={dados.sinopse}
                    onChange={(e) => setDados({ ...dados, sinopse: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Gênero"
                    value={dados.genero}
                    onChange={(e) => setDados({ ...dados, genero: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Classificação"
                    value={dados.classificacao}
                    onChange={(e) => setDados({ ...dados, classificacao: e.target.value })}
                />
                <input
                    type="number"
                    placeholder="Duração"
                    value={dados.duracao}
                    onChange={(e) => setDados({ ...dados, duracao: parseInt(e.target.value) })}
                />
                <input
                    type="date"
                    placeholder="Data de Lançamento"
                    value={dados.dataLancamento}
                    onChange={(e) => setDados({ ...dados, dataLancamento: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="URL da Imagem"
                    value={dados.imagem_url}
                    onChange={(e) => setDados({ ...dados, imagem_url: e.target.value })}
                />
                <button type="button" onClick={cadastrarFilme}>
                    Cadastrar Filme
                </button>
            </form>
        </div>
    );
}