package service;

import entities.baralho.Baralho;
import entities.baralho.Carta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class GerarMao {
    private static final Random random = new Random();

    public static List<Carta> gerar(Baralho baralho, int numeroCartas) {
        List<Carta> mao = new ArrayList<>(numeroCartas);
        List<Integer> naipes = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        int index, naipe, numero;

        naipe = 0;

        for (int j = 0; j < numeroCartas; j++) {
            if (j % 2 == 0) {
                numero = random.nextInt(0, naipes.size());

                naipe = naipes.get(numero);
                naipes.remove(numero);
            }

            switch (naipe) {
                case 0 -> {
                    index = random.nextInt(0, baralho.getNaipeOuro().size());

                    mao.add(baralho.getNaipeOuro().get(index));

                    baralho.getNaipeOuro().remove(index);
                }

                case 1 -> {
                    index = random.nextInt(0, baralho.getNaipeCopas().size());

                    mao.add(baralho.getNaipeCopas().get(index));

                    baralho.getNaipeCopas().remove(index);
                }

                case 2 -> {
                    index = random.nextInt(0, baralho.getNaipePaus().size());

                    mao.add(baralho.getNaipePaus().get(index));

                    baralho.getNaipePaus().remove(index);
                }

                case 3 -> {
                    index = random.nextInt(0, baralho.getNaipeEspadas().size());

                    mao.add(baralho.getNaipeEspadas().get(index));

                    baralho.getNaipeEspadas().remove(index);
                }
            }
        }

        return mao;
    }
}
