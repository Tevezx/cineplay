package play.cine.cineplay.service;

import org.springframework.stereotype.Service;
import play.cine.cineplay.model.Avaliacao;
import play.cine.cineplay.repository.AvaliacaoRepository;
import play.cine.cineplay.validations.AvaliacaoValidator;

import java.util.List;

@Service
public class AvaliacaoService {
    private final AvaliacaoRepository repository;
    private final AvaliacaoValidator avaliacaoValidator;

    public AvaliacaoService(AvaliacaoRepository repository, AvaliacaoValidator avaliacaoValidator) {
        this.repository = repository;
        this.avaliacaoValidator = avaliacaoValidator;
    }

    public List<Avaliacao> findAll() {
        return repository.findAll();
    }

    public Avaliacao save(Avaliacao avaliacao) {
        avaliacaoValidator.isAvaliacaoValida(avaliacao);
        avaliacaoValidator.isIdNaoExiste(avaliacao.getId_usuario(), avaliacao.getId_filme());

        return repository.save(avaliacao);
    }

    public Avaliacao updateById(Integer idUsuario, Integer idFilme, Avaliacao avaliacao) {
        avaliacaoValidator.isAvaliacaoValida(avaliacao);
        avaliacaoValidator.isIdExiste(idUsuario, idFilme);

        return repository.updateById(idUsuario, idFilme, avaliacao);
    }

    public void deletedById(Integer idUsuario, Integer idFilme) {
        avaliacaoValidator.isIdExiste(idUsuario, idFilme);

        repository.deletedById(idUsuario, idFilme);
    }
}
