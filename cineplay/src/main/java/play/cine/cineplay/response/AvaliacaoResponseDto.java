package play.cine.cineplay.response;

import play.cine.cineplay.model.Avaliacao;

public record AvaliacaoResponseDto(Integer id_usuario, Integer id_filme, Integer nota, String comentario) {
    public static AvaliacaoResponseDto fromEntity(Avaliacao avaliacao) {
        return new AvaliacaoResponseDto(avaliacao.getId_usuario(),
                avaliacao.getId_filme(),
                avaliacao.getNota(),
                avaliacao.getComentario());
    }
}
