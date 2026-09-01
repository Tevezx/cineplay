package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.request.FilmeRequestDto;
import play.cine.cineplay.response.FilmeResponseDto;
import play.cine.cineplay.service.FilmeService;

import java.util.List;

@RestController
@RequestMapping("v1/filmes")
public class FilmeController {
    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<FilmeResponseDto>> findAll() {
        var filmes = service.findAll().stream()
                .map(FilmeResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(filmes);
    }

    @PostMapping()
    public ResponseEntity<FilmeResponseDto> save(@RequestBody FilmeRequestDto filmeRequestDto) {
        var filmeSalvo = service.save(filmeRequestDto.toEntity());
        return ResponseEntity.status(201).body(FilmeResponseDto.fromEntity(filmeSalvo));
    }

    @PutMapping("{id}")
    public ResponseEntity<FilmeResponseDto> updateById(@PathVariable Integer id, @RequestBody FilmeRequestDto filmeRequestDto) {
        var filmeAtualizado = service.updateById(id, filmeRequestDto.toEntity());
        return ResponseEntity.ok(FilmeResponseDto.fromEntity(filmeAtualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        service.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
