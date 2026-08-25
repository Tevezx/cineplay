package play.cine.cineplay.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import play.cine.cineplay.model.Filme;

import java.util.List;

@RestController
@RequestMapping("v1/filmes")
public class FilmeController {
    private final JdbcTemplate template;

    public FilmeController(JdbcTemplate template) {
        this.template = template;
    }

    @GetMapping()
    public ResponseEntity<List<Filme>> findAll(){
        String sql = "SELECT * FROM filme";

        List<Filme> filmes = template.query(sql,
                new BeanPropertyRowMapper<>(Filme.class));

        return ResponseEntity.ok(filmes);
    }
}
