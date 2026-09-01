package play.cine.cineplay.response;

import play.cine.cineplay.model.Filme;
import play.cine.cineplay.model.enums.Classificacao;
import play.cine.cineplay.model.enums.Genero;

import java.util.Date;

public record FilmeResponseDto(
        Integer id,
        String titulo,
        String sinopse,
        Integer duracao,
        Classificacao classificacao,
        Genero genero,
        Date dataLancamento,
        String imagem_url
) {
    public static FilmeResponseDto fromEntity(Filme filme) {
        return new FilmeResponseDto(filme.getId(),
                filme.getTitulo(),
                filme.getSinopse(),
                filme.getDuracao(),
                filme.getClassificacao(),
                filme.getGenero(),
                filme.getDataLancamento(),
                filme.getImagem_url());
    }
}
