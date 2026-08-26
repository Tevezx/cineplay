package play.cine.cineplay.validations;

import play.cine.cineplay.model.Usuario;

public class UsuarioValidator {
    public Boolean validar(Usuario usuario) {
        return usuario.getCpf() == null || usuario.getCpf() <= 0
                || usuario.getNome() == null || usuario.getNome().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getSenha() == null || usuario.getSenha().isBlank() || usuario.getSenha().length() <= 6;
    }
}
