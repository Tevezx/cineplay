package play.cine.cineplay.validations;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Usuario;
import play.cine.exception.EmailAlreadyExistsException;
import play.cine.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsuarioValidator {
    private static final String REGEXEMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String REGEXCPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

    private static final Pattern PATTERNEMAIL = Pattern.compile(REGEXEMAIL);
    private static final Pattern PATTERNCPF = Pattern.compile(REGEXCPF);

    private final JdbcTemplate template;

    public UsuarioValidator(JdbcTemplate template) {
        this.template = template;
    }

    public void isUsuarioValido(Usuario usuario) {
        if (usuario.getCpf() == null || usuario.getCpf().isBlank() || usuario.getCpf().length() != 11
                || usuario.getNome() == null || usuario.getNome().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getSenha() == null || usuario.getSenha().isBlank() || usuario.getSenha().length() <= 6) {
            throw new IllegalArgumentException("Dados do usuário inválidos");
        }
    }

    public void isIdExiste(Integer id) {
        String sql = "SELECT id_usuario AS id, cpf, nome, email, senha FROM usuario";

        List<Usuario> usuarios = template.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        boolean existe = usuarios.stream()
                .anyMatch(usuario -> usuario.getId().equals(id));

        if (!existe) {
            throw new NotFoundException("Usuário com id " + id + " não encontrado");
        }
    }

    public void isEmailValido(String email) {
        if (email == null || !PATTERNEMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
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

    public void isCpfValido(String cpf) {
        if (cpf == null || !PATTERNCPF.matcher(cpf).matches()) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
    }
}
