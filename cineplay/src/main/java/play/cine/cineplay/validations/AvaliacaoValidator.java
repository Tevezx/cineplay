package play.cine.cineplay.validations;

import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Avaliacao;
import play.cine.cineplay.repository.AvaliacaoRepository;
import play.cine.exception.AvaliacaoAlreadyExistsException;
import play.cine.exception.NotFoundException;

@Component
public class AvaliacaoValidator {
    private final AvaliacaoRepository repository;

    public AvaliacaoValidator(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public void isAvaliacaoValida(Avaliacao avaliacao) {
        if (avaliacao.getId_usuario() == null
                || avaliacao.getId_filme() == null
                || avaliacao.getNota() == null || avaliacao.getNota() < 0 || avaliacao.getNota() > 5
                || avaliacao.getComentario() == null || avaliacao.getComentario().isBlank()) {
            throw new IllegalArgumentException("Dados da avaliação inválidos");
        }
    }

    public void isIdExiste(Integer idUsuario, Integer idFilme) {
        if (!repository.existsById(idUsuario, idFilme)) {
            throw new NotFoundException("Avaliação não encontrada para usuario " + idUsuario + " e filme " + idFilme);
        }
    }

    public void isIdNaoExiste(Integer idUsuario, Integer idFilme) {
        if (repository.existsById(idUsuario, idFilme)) {
            throw new AvaliacaoAlreadyExistsException("Já existe uma avaliação para usuario " + idUsuario + " e filme " + idFilme);
        }
    }
}
