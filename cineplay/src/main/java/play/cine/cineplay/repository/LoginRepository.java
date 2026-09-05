package play.cine.cineplay.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import play.cine.cineplay.model.Usuario;

import java.util.Optional;

@Repository
public class LoginRepository {
    private final JdbcTemplate template;

    public LoginRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Optional<Usuario> findByEmail(String email){
        String sql = "SELECT id_usuario AS id, cpf, nome, email, senha FROM usuario WHERE LOWER(email) = LOWER(?)";

        return template.query(sql, new BeanPropertyRowMapper<>(Usuario.class), email)
                .stream()
                .findFirst();
    }
}
