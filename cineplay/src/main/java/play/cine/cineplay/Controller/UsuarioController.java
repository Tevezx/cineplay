package play.cine.cineplay.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import play.cine.cineplay.model.Usuario;

import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {
    private final JdbcTemplate template;

    public UsuarioController(JdbcTemplate template) {
        this.template = template;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> findAll() {
        String sql = "SELECT * FROM usuario";

        List<Usuario> usuarios = template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        return ResponseEntity.ok(usuarios);
    }
}
