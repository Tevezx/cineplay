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
        String sql = "SELECT id_usuario AS id, cpf, nome, email, senha FROM usuario";

        List<Usuario> usuarios = template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping()
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        if (usuarioValidator.isUsuarioValido(usuario)) {
            return ResponseEntity.badRequest().build();
        }

        if (!usuarioValidator.isEmailValido(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        usuarioValidator.isEmailExiste(usuario.getEmail());

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

        Number idGerado = keyHolder.getKey();
        usuario.setId(idGerado != null ? idGerado.intValue() : null);

        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateById(@PathVariable Integer id, @RequestBody Usuario usuario) {
        if (!usuarioValidator.isIdExiste(id)) {
            return ResponseEntity.notFound().build();
        }

        if (usuarioValidator.isUsuarioValido(usuario)) {
            return ResponseEntity.badRequest().build();
        }

        if (!usuarioValidator.isEmailValido(usuario.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        String sql = "UPDATE usuario SET cpf = ?, nome = ?, email = ?, senha = ? WHERE id_usuario = ?";


        template.update(sql,
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                id
        );

        return ResponseEntity.status(200).body(usuario);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        if (!usuarioValidator.isIdExiste(id)) {
            return ResponseEntity.notFound().build();
        }

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        template.update(sql, id);

        return ResponseEntity.status(200).build();
    }
}
