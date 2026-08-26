package play.cine.cineplay.Controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/avaliacoes")
public class AvaliacaoController {
    private final JdbcTemplate template;

    public AvaliacaoController(JdbcTemplate template) {
        this.template = template;
    }
}
