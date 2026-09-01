package play.cine.cineplay.request;

import play.cine.cineplay.model.Filme;
import play.cine.cineplay.model.enums.Classificacao;
import play.cine.cineplay.model.enums.Genero;

import java.util.Date;

public record FilmeRequestDto(String titulo,
                              String sinopse,
                              Integer duracao,
                              Classificacao classificacao,
                              Genero genero,
                              Date dataLancamento,
                              String imagem_url) {
    public Filme toEntity() {
        Filme filme = new Filme();

        filme.setTitulo(titulo);
        filme.setSinopse(sinopse);
        filme.setDuracao(duracao);
        filme.setClassificacao(classificacao);
        filme.setGenero(genero);
        filme.setDataLancamento(dataLancamento);
        filme.setImagem_url(imagem_url);

        return filme;
    }
}
