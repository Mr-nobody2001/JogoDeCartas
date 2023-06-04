package entities.baralho.naipe;

import entities.baralho.Carta;
import entities.enums.Naipes;

public class CartaCopas extends Carta {
    public CartaCopas(Integer valor, String nome) {
        super(Naipes.COPAS, valor, nome);
    }
}
