package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.model.Avaliacao;
import play.cine.cineplay.service.AvaliacaoService;

import java.util.List;

@RestController
@RequestMapping("v1/avaliacoes")
public class AvaliacaoController {
    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Avaliacao>> findAll() {
        var avaliacoes = service.findAll();
        return ResponseEntity.ok(avaliacoes);
    }

    @PostMapping()
    public ResponseEntity<Avaliacao> save(@RequestBody Avaliacao avaliacao) {
        var avaliacaoSalva = service.save(avaliacao);
        return ResponseEntity.status(201).body(avaliacaoSalva);
    }

    @PutMapping("{idUsuario}/{idFilme}")
    public ResponseEntity<Avaliacao> updateById(@PathVariable Integer idUsuario, @PathVariable Integer idFilme, @RequestBody Avaliacao avaliacao) {
        var avaliacaoAtualizada = service.updateById(idUsuario, idFilme, avaliacao);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    @DeleteMapping("{idUsuario}/{idFilme}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer idUsuario, @PathVariable Integer idFilme) {
        service.deletedById(idUsuario, idFilme);
        return ResponseEntity.noContent().build();
    }
}
