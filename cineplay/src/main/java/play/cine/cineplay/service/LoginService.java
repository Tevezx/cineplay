package play.cine.cineplay.service;

import org.springframework.stereotype.Service;
import play.cine.cineplay.model.Usuario;
import play.cine.cineplay.repository.LoginRepository;
import play.cine.exception.NotFoundException;

@Service
public class LoginService {
    private final LoginRepository repository;

    public LoginService(LoginRepository repository) {
        this.repository = repository;
    }

    public Usuario login(String email, String senha){
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email ou senha inválidos"));

        if (!usuario.getSenha().equals(senha)) {
            throw new NotFoundException("Email ou senha inválidos"); // mesma msg p/ não expor qual campo errou
        }
        return usuario;
    }
}
