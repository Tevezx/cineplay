package play.cine.cineplay.enums;

public enum Classificacao {
    L10(10),
    L12(12),
    L14(14);

    private final int idade;

    Classificacao(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
}
