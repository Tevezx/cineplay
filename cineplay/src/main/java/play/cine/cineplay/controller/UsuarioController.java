package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.request.UsuarioRequestDto;
import play.cine.cineplay.response.UsuarioResponseDto;
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
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        var usuarios = service.findAll().stream()
                .map(UsuarioResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> save(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        var usuarioSalvo = service.save(usuarioRequestDto.toEntity());
        return ResponseEntity.status(201).body(UsuarioResponseDto.fromEntity(usuarioSalvo));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> updateById(@PathVariable Integer id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        var usuario = usuarioRequestDto.toEntity();
        usuario.setId(id);

        var usuarioUpdated = service.updateById(id, usuario);
        return ResponseEntity.status(200).body(UsuarioResponseDto.fromEntity(usuarioUpdated));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        service.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
