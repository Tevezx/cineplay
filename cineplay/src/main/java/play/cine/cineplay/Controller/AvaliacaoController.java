package play.cine.cineplay.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.model.Avaliacao;
import play.cine.cineplay.validations.AvaliacaoValidator;

import java.util.List;

@RestController
@RequestMapping("v1/avaliacoes")
public class AvaliacaoController {
    private final JdbcTemplate template;
    private final AvaliacaoValidator avaliacaoValidator;

    public AvaliacaoController(JdbcTemplate template, AvaliacaoValidator avaliacaoValidator) {
        this.template = template;
        this.avaliacaoValidator = avaliacaoValidator;
    }

    @GetMapping()
    public ResponseEntity<List<Avaliacao>> findAll() {
        String sql = "SELECT usuario_id_usuario AS id_usuario, filme_id_filme AS id_filme, nota, comentario FROM avaliacao";

        List<Avaliacao> avaliacoes = template.query(sql,
                new BeanPropertyRowMapper<>(Avaliacao.class));

        return ResponseEntity.ok(avaliacoes);
    }

    @PostMapping()
    public ResponseEntity<Avaliacao> save(@RequestBody Avaliacao avaliacao) {
        if(!avaliacaoValidator.isValidarAvaliacao(avaliacao)){
            return ResponseEntity.badRequest().build();
        }

        String sql = "INSERT INTO avaliacao (usuario_id_usuario, filme_id_filme, nota, comentario) VALUES (?, ?, ?, ?)";

        template.update(sql,
                avaliacao.getId_usuario(),
                avaliacao.getId_filme(),
                avaliacao.getNota(),
                avaliacao.getComentario());

        return ResponseEntity.status(201).body(avaliacao);
    }
}
