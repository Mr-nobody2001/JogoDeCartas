package entities.baralho;

import entities.enums.Naipes;

public abstract class Carta {
    private final Naipes naipe;
    private final Integer valor;
    private final String nome;

    public Carta(Naipes naipe, Integer valor, String nome) {
        this.naipe = naipe;
        this.valor = valor;
        this.nome = nome;
    }

    public Naipes getNaipe() {
        return naipe;
    }

    public Integer getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}
