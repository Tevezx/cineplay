package play.cine.cineplay.validations.filme;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Filme;

@Component
public class FilmeValidator {
    private final JdbcTemplate template;

    public FilmeValidator(JdbcTemplate template) {
        this.template = template;
    }

    public Boolean validar(Filme filme) {
        return filme.getTitulo() == null || filme.getTitulo().isBlank()
                || filme.getSinopse() == null || filme.getSinopse().isBlank()
                || filme.getDuracao() == null || filme.getDuracao() <= 0
                || filme.getClassificacao() == null
                || filme.getGenero() == null
                || filme.getDataLancamento() == null
                || filme.getImagem_url() == null || filme.getImagem_url().isBlank();
    }

    public Boolean idExiste(Integer id) {
        String sql = "SELECT COUNT(*) FROM filme WHERE id_filme = ?";

        Integer total = template.queryForObject(sql, Integer.class, id);

        return total != null && total > 0;
    }
}
