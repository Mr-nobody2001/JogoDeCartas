package entities.jogo;

import entities.baralho.Carta;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private Double pontos;
    private final List<Carta> cartas;
    private final List<Carta> complementos;

    public Jogador(String nome, List<Carta> cartas) {
        this.nome = nome;
        this.cartas = cartas;
        this.complementos = new ArrayList<>();
    }

    public Double gerarPontuacao() {
        double soma = 0;

        for (Carta temp : this.cartas) {
            soma += temp.getNaipe().getValue() * temp.getValor();
        }

        for (Carta temp : this.complementos) {
            soma += temp.getValor() / 10.0;
        }

        return soma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public Double getPontos() {
        return pontos;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public List<Carta> getComplementos() {
        return complementos;
    }
}
