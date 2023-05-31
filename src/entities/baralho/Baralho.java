package entities.baralho;

import entities.baralho.naipe.CartaCopas;
import entities.baralho.naipe.CartaEspadas;
import entities.baralho.naipe.CartaOuro;
import entities.baralho.naipe.CartaPaus;

import java.util.ArrayList;
import java.util.List;

public class Baralho {
    List<CartaOuro> naipeOuro = new ArrayList<>(14);
    List<CartaCopas> naipeCopas = new ArrayList<>(14);
    List<CartaPaus> naipePaus = new ArrayList<>(14);
    List<CartaEspadas> naipeEspadas = new ArrayList<>(14);

    public Baralho() {
        montarBaralho();
    }

    public void montarBaralho() {
        this.montarNaipeOuro();
        this.montarNaipeCopas();
        this.montarNaipePaus();
        this.montarNaipeEspadas();
    }

    public void montarNaipeOuro() {
        int valor;

        for (valor = 1; valor <= 10; valor++) {
            naipeOuro.add(new CartaOuro(valor, valor + "o"));
        }

        naipeOuro.add(new CartaOuro(valor, "Jo"));
        naipeOuro.add(new CartaOuro(++valor, "Qo"));
        naipeOuro.add(new CartaOuro(++valor, "Ko"));
        naipeOuro.add(new CartaOuro(++valor, "Ao"));
    }

    public void montarNaipeCopas() {
        int valor;

        for (valor = 1; valor <= 10; valor++) {
            naipeCopas.add(new CartaCopas(valor, valor + "c"));
        }

        naipeCopas.add(new CartaCopas(valor, "Jc"));
        naipeCopas.add(new CartaCopas(++valor, "Qc"));
        naipeCopas.add(new CartaCopas(++valor, "Kc"));
        naipeCopas.add(new CartaCopas(++valor, "Ac"));
    }

    public void montarNaipePaus() {
        int valor;

        for (valor = 1; valor <= 10; valor++) {
            naipePaus.add(new CartaPaus(valor, valor + "p"));
        }

        naipePaus.add(new CartaPaus(valor, "Jp"));
        naipePaus.add(new CartaPaus(++valor, "Qp"));
        naipePaus.add(new CartaPaus(++valor, "Kp"));
        naipePaus.add(new CartaPaus(++valor, "Ap"));
    }

    public void montarNaipeEspadas() {
        int valor;

        for (valor = 1; valor <= 10; valor++) {
            naipeEspadas.add(new CartaEspadas(valor, valor + "e"));
        }

        naipeEspadas.add(new CartaEspadas(valor, "Je"));
        naipeEspadas.add(new CartaEspadas(++valor, "Qe"));
        naipeEspadas.add(new CartaEspadas(++valor, "Ke"));
        naipeEspadas.add(new CartaEspadas(++valor, "Ae"));
    }

    public void removerCartaOuro(int index) {
        this.getNaipeOuro().remove(index);
    }

    public void removerCartaCopas(int index) {
        this.getNaipeCopas().remove(index);
    }

    public void removerCartaPaus(int index) {
        this.getNaipePaus().remove(index);
    }

    public void removerCartaEspadas(int index) {
        this.getNaipeEspadas().remove(index);
    }

    public List<CartaOuro> getNaipeOuro() {
        return naipeOuro;
    }

    public List<CartaCopas> getNaipeCopas() {
        return naipeCopas;
    }

    public List<CartaPaus> getNaipePaus() {
        return naipePaus;
    }

    public List<CartaEspadas> getNaipeEspadas() {
        return naipeEspadas;
    }
}
