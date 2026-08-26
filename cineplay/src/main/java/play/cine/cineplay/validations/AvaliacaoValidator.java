package play.cine.cineplay.validations;

import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Avaliacao;

@Component
public class AvaliacaoValidator {
    public Boolean isValidarAvaliacao(Avaliacao avaliacao) {
        return avaliacao.getId_usuario() == null
                || avaliacao.getId_filme() == null
                || avaliacao.getNota() == null || avaliacao.getNota() < 0.0 && avaliacao.getNota() > 5.0
                || avaliacao.getComentario() == null || avaliacao.getComentario().isBlank();
    }
}
