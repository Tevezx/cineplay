package play.cine.cineplay.service;

import org.springframework.stereotype.Service;
import play.cine.cineplay.model.Usuario;
import play.cine.cineplay.repository.UsuarioRepository;
import play.cine.cineplay.validations.UsuarioValidator;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioValidator usuarioValidator;

    public UsuarioService(UsuarioRepository repository, UsuarioValidator validator) {
        this.repository = repository;
        this.usuarioValidator = validator;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuarioValidator.isUsuarioValido(usuario);
        usuarioValidator.isEmailValido(usuario.getEmail());
        usuarioValidator.isEmailExiste(usuario.getEmail());
        usuarioValidator.isCpfValido(usuario.getCpf());

        return repository.save(usuario);
    }

    public Usuario updateById(Integer id, Usuario usuario){
        usuarioValidator.isIdExiste(id);
        usuarioValidator.isUsuarioValido(usuario);
        usuarioValidator.isEmailValido(usuario.getEmail());
        usuarioValidator.isEmailExiste(id, usuario.getEmail());
        usuarioValidator.isCpfValido(usuario.getCpf());

        return repository.updateById(id, usuario);
    }

    public void deletedById(Integer id){
        usuarioValidator.isIdExiste(id);
        repository.deletedById(id);
    }
}
