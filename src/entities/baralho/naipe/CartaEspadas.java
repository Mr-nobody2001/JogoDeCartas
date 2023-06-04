package entities.baralho.naipe;

import entities.baralho.Carta;
import entities.enums.Naipes;

public class CartaEspadas extends Carta {
    public CartaEspadas(Integer valor, String nome) {
        super(Naipes.ESPADAS, valor, nome);
    }
}
