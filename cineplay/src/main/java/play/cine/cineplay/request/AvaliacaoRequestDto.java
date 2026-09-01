package play.cine.cineplay.request;

import play.cine.cineplay.model.Avaliacao;

public record AvaliacaoRequestDto(Integer id_usuario, Integer id_filme, Integer nota, String comentario) {
    public Avaliacao toEntity() {
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setId_usuario(id_usuario);
        avaliacao.setId_filme(id_filme);
        avaliacao.setNota(nota);
        avaliacao.setComentario(comentario);

        return avaliacao;
    }
}
