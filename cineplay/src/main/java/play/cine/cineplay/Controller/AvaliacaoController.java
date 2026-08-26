package play.cine.cineplay.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import play.cine.cineplay.model.Avaliacao;

import java.util.List;

@RestController
@RequestMapping("v1/avaliacoes")
public class AvaliacaoController {
    private final JdbcTemplate template;

    public AvaliacaoController(JdbcTemplate template) {
        this.template = template;
    }

    @GetMapping()
    public ResponseEntity<List<Avaliacao>> findAll() {
        String sql = "SELECT usuario_id_usuario AS id_usuario, filme_id_filme AS id_filme, nota, comentario FROM avaliacao";

        List<Avaliacao> avaliacoes = template.query(sql,
                new BeanPropertyRowMapper<>(Avaliacao.class));

        return ResponseEntity.ok(avaliacoes);
    }
}
