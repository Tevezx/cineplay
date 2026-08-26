package play.cine.cineplay.validations;

import org.springframework.stereotype.Component;
import play.cine.cineplay.model.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsuarioValidator {
    private static final String REGEXEMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN = Pattern.compile(REGEXEMAIL);

    public Boolean isUsuarioValido(Usuario usuario) {
        return usuario.getCpf() == null || usuario.getCpf().isBlank() || usuario.getCpf().length() != 11
                || usuario.getNome() == null || usuario.getNome().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getSenha() == null || usuario.getSenha().isBlank() || usuario.getSenha().length() <= 6;
    }

    public Boolean isEmailValido(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
