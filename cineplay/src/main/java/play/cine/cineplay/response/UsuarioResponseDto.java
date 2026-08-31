package play.cine.cineplay.response;

import play.cine.cineplay.model.Usuario;

public record UsuarioResponseDto(Integer id, String cpf, String nome, String email) {
    public static UsuarioResponseDto fromEntity(Usuario usuario) {
        return new UsuarioResponseDto(usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
