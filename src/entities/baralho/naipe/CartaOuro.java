package entities.baralho.naipe;

import entities.baralho.Carta;
import entities.enums.Naipes;

public class CartaOuro extends Carta {
    public CartaOuro(Integer valor, String nome) {
        super(Naipes.OURO, valor, nome);
    }
}