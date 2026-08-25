package play.cine.cineplay.Controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/filmes")
public class FilmeController {
    private final JdbcTemplate template;

    public FilmeController(JdbcTemplate template) {
        this.template = template;
    }
}
