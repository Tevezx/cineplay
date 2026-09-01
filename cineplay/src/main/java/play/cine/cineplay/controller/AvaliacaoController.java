package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.request.AvaliacaoRequestDto;
import play.cine.cineplay.response.AvaliacaoResponseDto;
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
    public ResponseEntity<List<AvaliacaoResponseDto>> findAll() {
        var avaliacoes = service.findAll().stream()
                .map(AvaliacaoResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(avaliacoes);
    }

    @PostMapping()
    public ResponseEntity<AvaliacaoResponseDto> save(@RequestBody AvaliacaoRequestDto avaliacaoRequestDto) {
        var avaliacaoSalva = service.save(avaliacaoRequestDto.toEntity());
        return ResponseEntity.status(201).body(AvaliacaoResponseDto.fromEntity(avaliacaoSalva));
    }

    @PutMapping("{idUsuario}/{idFilme}")
    public ResponseEntity<AvaliacaoResponseDto> updateById(@PathVariable Integer idUsuario, @PathVariable Integer idFilme, @RequestBody AvaliacaoRequestDto avaliacaoRequestDto) {
        var avaliacao = avaliacaoRequestDto.toEntity();
        avaliacao.setId_usuario(idUsuario);
        avaliacao.setId_filme(idFilme);

        var avaliacaoAtualizada = service.updateById(idUsuario, idFilme, avaliacao);
        return ResponseEntity.ok(AvaliacaoResponseDto.fromEntity(avaliacaoAtualizada));
    }

    @DeleteMapping("{idUsuario}/{idFilme}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer idUsuario, @PathVariable Integer idFilme) {
        service.deletedById(idUsuario, idFilme);
        return ResponseEntity.noContent().build();
    }
}
