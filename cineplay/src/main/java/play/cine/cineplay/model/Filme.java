package play.cine.cineplay.model;

import play.cine.cineplay.enums.Classificacao;
import play.cine.cineplay.enums.Genero;

import java.util.Date;
import java.util.Objects;

public class Filme {
    private Integer id;
    private String titulo;
    private String sinopse;
    private Integer duracao;
    private Classificacao classificacao;
    private Genero genero;
    private Date dataLancamento;
    private String imagem_url;

    public Filme() {
    }

    public Filme(Integer id, String titulo, String sinopse, Integer duracao, Classificacao classificacao, Genero genero, Date dataLancamento, String imagem_url) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.imagem_url = imagem_url;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", duracao=" + duracao +
                ", classificacao=" + classificacao +
                ", genero=" + genero +
                ", dataLancamento=" + dataLancamento +
                ", imagem_url='" + imagem_url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }
}
