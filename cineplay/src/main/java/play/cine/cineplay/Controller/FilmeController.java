package play.cine.cineplay.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.model.Filme;
import play.cine.cineplay.request.FilmeRequestDto;
import play.cine.cineplay.response.FilmeResponseDto;
import play.cine.cineplay.validations.FilmeValidator;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("v1/filmes")
public class FilmeController {
    private final JdbcTemplate template;
    private final FilmeValidator filmeValidator;

    public FilmeController(JdbcTemplate template, FilmeValidator filmeValidator) {
        this.template = template;
        this.filmeValidator = filmeValidator;
    }

    @GetMapping()
    public ResponseEntity<List<FilmeResponseDto>> findAll() {
        String sql = "SELECT id_filme AS id, titulo, sinopse, duracao, classificacao, genero, " +
                "dt_lancamento AS dataLancamento, img_url AS imagem_url FROM filme";

        List<Filme> filmes = template.query(sql,
                new BeanPropertyRowMapper<>(Filme.class));

        List<FilmeResponseDto> response = filmes.stream()
                .map(FilmeResponseDto::fromEntity)
                .toList();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<FilmeResponseDto> save(@RequestBody FilmeRequestDto filmeRequestDto) {
        Filme filme = filmeRequestDto.toEntity();

        if (filmeValidator.isFilmeValido(filme) && filmeValidator.isIdExiste(filme.getId())) {
            return ResponseEntity.badRequest().build();
        }

        String sql = "INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, filme.getTitulo());
            statement.setString(2, filme.getSinopse());
            statement.setInt(3, filme.getDuracao());
            statement.setString(4, filme.getClassificacao().name());
            statement.setString(5, filme.getGenero().name());
            statement.setDate(6, new java.sql.Date(filme.getDataLancamento().getTime()));
            statement.setString(7, filme.getImagem_url());

            return statement;
        }, keyHolder);

        Number idGerado = keyHolder.getKey();
        filme.setId(idGerado != null ? idGerado.intValue() : null);

        return ResponseEntity.status(201).body(FilmeResponseDto.fromEntity(filme));
    }

    @PutMapping("{id}")
    public ResponseEntity<FilmeResponseDto> updateById(@PathVariable Integer id, @RequestBody FilmeRequestDto filmeRequestDto) {
        Filme filme = filmeRequestDto.toEntity();

        if (filmeValidator.isFilmeValido(filme)) {
            return ResponseEntity.badRequest().build();
        }

        if (!filmeValidator.isIdExiste(id)) {
            return ResponseEntity.notFound().build();
        }

        String sql = "UPDATE filme SET " +
                "titulo = ?, sinopse = ?, duracao = ?, classificacao = ?, genero = ?, dt_lancamento = ?, img_url = ?" +
                "WHERE id_filme = ?";

        template.update(sql,
                filme.getTitulo(),
                filme.getSinopse(),
                filme.getDuracao(),
                filme.getClassificacao().name(),
                filme.getGenero().name(),
                new java.sql.Date(filme.getDataLancamento().getTime()),
                filme.getImagem_url(),
                id);

        filme.setId(id);
        return ResponseEntity.ok(FilmeResponseDto.fromEntity(filme));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        if (!filmeValidator.isIdExiste(id)) {
            return ResponseEntity.notFound().build();
        }

        String sql = "DELETE FROM filme WHERE id_filme = ?";

        template.update(sql, id);

        return ResponseEntity.noContent().build();
    }
}
