package play.cine.cineplay.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import play.cine.cineplay.model.Avaliacao;

import java.util.List;

@Repository
public class AvaliacaoRepository {
    private final JdbcTemplate template;

    public AvaliacaoRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Avaliacao> findAll() {
        String sql = "SELECT usuario_id_usuario AS id_usuario, filme_id_filme AS id_filme, nota, comentario FROM avaliacao";

        return template.query(sql, new BeanPropertyRowMapper<>(Avaliacao.class));
    }

    public Avaliacao save(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (usuario_id_usuario, filme_id_filme, nota, comentario) VALUES (?, ?, ?, ?)";

        template.update(sql,
                avaliacao.getId_usuario(),
                avaliacao.getId_filme(),
                avaliacao.getNota(),
                avaliacao.getComentario());

        return avaliacao;
    }

    public Avaliacao updateById(Integer idUsuario, Integer idFilme, Avaliacao avaliacao) {
        String sql = "UPDATE avaliacao SET nota = ?, comentario = ? WHERE usuario_id_usuario = ? AND filme_id_filme = ?";

        template.update(sql,
                avaliacao.getNota(),
                avaliacao.getComentario(),
                idUsuario,
                idFilme);

        avaliacao.setId_usuario(idUsuario);
        avaliacao.setId_filme(idFilme);

        return avaliacao;
    }

    public void deletedById(Integer idUsuario, Integer idFilme) {
        String sql = "DELETE FROM avaliacao WHERE usuario_id_usuario = ? AND filme_id_filme = ?";

        template.update(sql, idUsuario, idFilme);
    }

    public boolean existsById(Integer idUsuario, Integer idFilme) {
        String sql = "SELECT COUNT(*) FROM avaliacao WHERE usuario_id_usuario = ? AND filme_id_filme = ?";

        Integer total = template.queryForObject(sql, Integer.class, idUsuario, idFilme);

        return total != null && total > 0;
    }
}
