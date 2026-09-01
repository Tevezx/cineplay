package play.cine.cineplay.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import play.cine.cineplay.model.Usuario;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate template;

    public UsuarioRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Usuario> findAll() {
        String sql = "SELECT id_usuario AS id, cpf, nome, email, senha FROM usuario";

        return template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));
    }

    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuario (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, usuario.getCpf());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());

            return statement;
        }, keyHolder);

        Number idGerado = keyHolder.getKey();
        usuario.setId(idGerado != null ? idGerado.intValue() : null);

        return usuario;
    }

    public Usuario updateById(Integer id, Usuario usuario) {
        String sql = "UPDATE usuario SET cpf = ?, nome = ?, email = ?, senha = ? WHERE id_usuario = ?";

        template.update(sql,
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                id
        );

        return usuario;
    }

    public void deletedById(Integer id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        template.update(sql, id);
    }
}
