package play.cine.cineplay.service;

import org.springframework.stereotype.Service;
import play.cine.cineplay.model.Filme;
import play.cine.cineplay.repository.FilmeRepository;
import play.cine.cineplay.validations.FilmeValidator;

import java.util.List;

@Service
public class FilmeService {
    private final FilmeRepository repository;
    private final FilmeValidator filmeValidator;

    public FilmeService(FilmeRepository repository, FilmeValidator filmeValidator) {
        this.repository = repository;
        this.filmeValidator = filmeValidator;
    }

    public List<Filme> findAll() {
        return repository.findAll();
    }

    public Filme save(Filme filme) {
        filmeValidator.isFilmeValido(filme);

        return repository.save(filme);
    }

    public Filme updateById(Integer id, Filme filme) {
        filmeValidator.isFilmeValido(filme);
        filmeValidator.isIdExiste(id);

        return repository.updateById(id, filme);
    }

    public void deletedById(Integer id) {
        filmeValidator.isIdExiste(id);

        repository.deletedById(id);
    }
}
