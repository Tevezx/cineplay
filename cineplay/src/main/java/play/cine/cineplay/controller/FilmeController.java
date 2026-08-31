package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.model.Filme;
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
    public ResponseEntity<List<Filme>> findAll() {
        var filmes = service.findAll();
        return ResponseEntity.ok(filmes);
    }

    @PostMapping()
    public ResponseEntity<Filme> save(@RequestBody Filme filme) {
        var filmeSalvo = service.save(filme);
        return ResponseEntity.status(201).body(filmeSalvo);
    }

    @PutMapping("{id}")
    public ResponseEntity<Filme> updateById(@PathVariable Integer id, @RequestBody Filme filme) {
        var filmeAtualizado = service.updateById(id, filme);
        return ResponseEntity.ok(filmeAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        service.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
