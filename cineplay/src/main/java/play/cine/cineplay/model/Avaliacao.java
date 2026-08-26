package play.cine.cineplay.model;

public class Avaliacao {
    private Integer id_usuario;
    private Integer id_filme;
    private Integer nota;
    private String comentario;

    public Avaliacao() {
    }

    public Avaliacao(Integer id_usuario, Integer id_filme, Integer nota, String comentario) {
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.nota = nota;
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id_usuario=" + id_usuario +
                ", id_filme=" + id_filme +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_filme() {
        return id_filme;
    }

    public void setId_filme(Integer id_filme) {
        this.id_filme = id_filme;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
