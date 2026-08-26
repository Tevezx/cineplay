package play.cine.cineplay.Controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("{id}")
public class UsuarioController {
    private final JdbcTemplate template;

    public UsuarioController(JdbcTemplate template) {
        this.template = template;
    }
}
