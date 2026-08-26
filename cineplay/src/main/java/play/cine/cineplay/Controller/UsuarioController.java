package play.cine.cineplay.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.model.Usuario;
import play.cine.cineplay.validations.UsuarioValidator;

import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {
    private final JdbcTemplate template;
    private final UsuarioValidator usuarioValidator;

    public UsuarioController(JdbcTemplate template, UsuarioValidator usuarioValidator) {
        this.template = template;
        this.usuarioValidator = usuarioValidator;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> findAll() {
        String sql = "SELECT * FROM usuario";

        List<Usuario> usuarios = template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping()
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        if (usuarioValidator.isUsuarioValido(usuario)) {
            return ResponseEntity.badRequest().build();
        }

        if (!usuarioValidator.isEmailValido(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        String sql = "INSERT INTO usuario (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, usuario.getCpf());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());

            return statement;
        }, keyHolder);

        Integer idGerado = keyHolder.getKeyAs(Integer.class);
        usuario.setId(idGerado);

        return ResponseEntity.status(201).body(usuario);
    }
}
