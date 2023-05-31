package entities.baralho;

public abstract class Carta {
    private final Integer peso;
    private final Integer valor;
    private final String nome;

    public Carta(Integer peso, Integer valor, String nome) {
        this.peso = peso;
        this.valor = valor;
        this.nome = nome;
    }

    public Integer getPeso() {
        return peso;
    }

    public Integer getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}
