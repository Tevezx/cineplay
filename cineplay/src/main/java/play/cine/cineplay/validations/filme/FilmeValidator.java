package play.cine.cineplay.validations.filme;

import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Filme;

@Component
public class FilmeValidator {
    public void validarPost(Filme filme) {
        if (filme.getTitulo() == null || filme.getTitulo().isBlank()) {
            throw new IllegalArgumentException("titulo é obrigatório");
        }
        if (filme.getClassificacao() == null) {
            throw new IllegalArgumentException("classificacao é obrigatória");
        }
        if (filme.getGenero() == null) {
            throw new IllegalArgumentException("genero é obrigatório");
        }
        if (filme.getDataLancamento() == null) {
            throw new IllegalArgumentException("dataLancamento é obrigatória");
        }
    }
}
