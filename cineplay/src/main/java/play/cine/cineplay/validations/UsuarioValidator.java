package play.cine.cineplay.validations;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Usuario;
import play.cine.cineplay.request.UsuarioRequestDto;
import play.cine.cineplay.response.UsuarioResponseDto;
import play.cine.exception.EmailAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsuarioValidator {
    private static final String REGEXEMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN = Pattern.compile(REGEXEMAIL);
    private final JdbcTemplate template;

    public UsuarioValidator(JdbcTemplate template) {
        this.template = template;
    }

    public Boolean isUsuarioValido(Usuario usuario) {
        return usuario.getCpf() == null || usuario.getCpf().isBlank() || usuario.getCpf().length() != 11
                || usuario.getNome() == null || usuario.getNome().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getSenha() == null || usuario.getSenha().isBlank() || usuario.getSenha().length() <= 6;
    }

    public Boolean isIdExiste(Integer id) {
        String sql = "SELECT id_usuario AS id, cpf, nome, email, senha FROM usuario";

        List<Usuario> usuarios = template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public Boolean isEmailValido(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

    public void isEmailExiste(String email) {
        String sql = "SELECT id_usuario AS id, cpf, nome, email, senha FROM usuario";

        List<Usuario> usuarios = template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        Optional<Usuario> usuarioEncontrado = usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (usuarioEncontrado.isPresent()) {
            throw new EmailAlreadyExistsException("Email já cadastrado: " + email);
        }
    }
}
