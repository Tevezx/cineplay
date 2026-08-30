package play.cine.cineplay.controller;

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



        return ResponseEntity.status(200).body(usuario);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        if (!usuarioValidator.isIdExiste(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).build();
    }
}
