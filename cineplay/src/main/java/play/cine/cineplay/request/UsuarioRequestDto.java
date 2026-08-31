package play.cine.cineplay.request;

import play.cine.cineplay.model.Usuario;

public record UsuarioRequestDto(String cpf, String nome, String email, String senha) {
    public Usuario toEntity() {
        Usuario u = new Usuario();
        u.setCpf(cpf);
        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(senha);
        return u;
    }
}
