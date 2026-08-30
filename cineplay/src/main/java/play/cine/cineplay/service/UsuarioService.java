package play.cine.cineplay.service;

import org.springframework.stereotype.Service;
import play.cine.cineplay.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
}
