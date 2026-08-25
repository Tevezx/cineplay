package play.cine.cineplay.validations.filme;

import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Filme;

@Component
public class FilmeValidator {
    public Boolean validar(Filme filme) {
        return filme.getTitulo() == null || filme.getTitulo().isBlank()
                || filme.getClassificacao() == null
                || filme.getGenero() == null
                || filme.getDataLancamento() == null;
    }
}
