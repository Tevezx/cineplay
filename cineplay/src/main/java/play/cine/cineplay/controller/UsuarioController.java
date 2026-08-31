package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.model.Usuario;
import play.cine.cineplay.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> findAll() {
        var usuarios = service.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping()
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        var usuarioSalvo = service.save(usuario);
        return ResponseEntity.status(201).body(usuarioSalvo);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateById(@PathVariable Integer id, @RequestBody Usuario usuario) {
        var usuarioUpdated = service.updateById(id, usuario);
        return ResponseEntity.status(200).body(usuarioUpdated);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        service.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
