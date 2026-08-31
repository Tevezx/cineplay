package play.cine.cineplay.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import play.cine.cineplay.model.Filme;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FilmeRepository {
    private final JdbcTemplate template;

    public FilmeRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Filme> findAll() {
        String sql = "SELECT id_filme AS id, titulo, sinopse, duracao, classificacao, genero, " +
                "dt_lancamento AS dataLancamento, img_url AS imagem_url FROM filme";

        return template.query(sql, new BeanPropertyRowMapper<>(Filme.class));
    }

    public Filme save(Filme filme) {
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

        return filme;
    }

    public Filme updateById(Integer id, Filme filme) {
        String sql = "UPDATE filme SET " +
                "titulo = ?, sinopse = ?, duracao = ?, classificacao = ?, genero = ?, dt_lancamento = ?, img_url = ? " +
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
        return filme;
    }

    public void deletedById(Integer id) {
        String sql = "DELETE FROM filme WHERE id_filme = ?";

        template.update(sql, id);
    }
}
