package entities.baralho.naipe;

import entities.baralho.Carta;
import entities.enums.Naipes;

public class CartaPaus extends Carta {
    public CartaPaus(Integer valor, String nome) {
        super(Naipes.PAUS, valor, nome);
    }
}
