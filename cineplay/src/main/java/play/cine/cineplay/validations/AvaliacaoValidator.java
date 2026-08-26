package play.cine.cineplay.validations;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Avaliacao;

import java.util.List;

@Component
public class AvaliacaoValidator {
    private final JdbcTemplate template;

    public AvaliacaoValidator(JdbcTemplate template) {
        this.template = template;
    }

    public Boolean isValidarAvaliacao(Avaliacao avaliacao) {
        return avaliacao.getId_usuario() == null
                || avaliacao.getId_filme() == null
                || avaliacao.getNota() == null || avaliacao.getNota() < 0.0 && avaliacao.getNota() > 5.0
                || avaliacao.getComentario() == null || avaliacao.getComentario().isBlank();
    }

    public Boolean isIdExiste(Integer idUsuario, Integer idFilme) {
        String sql = "SELECT usuario_id_usuario AS id_usuario, filme_id_filme AS id_filme, nota, comentario FROM avaliacao";

        List<Avaliacao> avaliacoes = template.query(sql,
                new BeanPropertyRowMapper<>(Avaliacao.class));

        for (Avaliacao avaliacao : avaliacoes) {
            if(avaliacao.getId_usuario().equals(idUsuario) && avaliacao.getId_filme().equals(idFilme)){
                return true;
            }
        }

        return false;
    }
}
