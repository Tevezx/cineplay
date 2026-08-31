package play.cine.cineplay.validations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Filme;
import play.cine.exception.NotFoundException;

@Component
public class FilmeValidator {
    private final JdbcTemplate template;

    public FilmeValidator(JdbcTemplate template) {
        this.template = template;
    }

    public void isFilmeValido(Filme filme) {
        if (filme.getTitulo() == null || filme.getTitulo().isBlank()
                || filme.getSinopse() == null || filme.getSinopse().isBlank()
                || filme.getDuracao() == null || filme.getDuracao() <= 0
                || filme.getClassificacao() == null
                || filme.getGenero() == null
                || filme.getDataLancamento() == null
                || filme.getImagem_url() == null || filme.getImagem_url().isBlank()) {
            throw new IllegalArgumentException("Dados do filme inválidos");
        }
    }

    public void isIdExiste(Integer id) {
        String sql = "SELECT COUNT(*) FROM filme WHERE id_filme = ?";

        Integer total = template.queryForObject(sql, Integer.class, id);

        if (total == null || total == 0) {
            throw new NotFoundException("Filme com id " + id + " não encontrado");
        }
    }
}
