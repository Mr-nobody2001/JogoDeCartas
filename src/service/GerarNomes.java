package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GerarNomes {
    private static final Random random = new Random();

    public static List<String> gerar(int numeroJogadores, int numeroCaracteres) {
        List<String> nomesAleatorios = new ArrayList<>(numeroJogadores);

        String nome, nomeFormatado;
        String[] nomeBruto;

        for (int i = 0; i < numeroJogadores; i++) {
            nome = "";
            nomeFormatado = "";

            for (int j = 0; j < numeroCaracteres; j++) {
                nome += (char) (random.nextInt(25) + 65);
            }

            nomeBruto = nome.split(" ");

            for (String temp : nomeBruto) {
                nomeFormatado += temp.charAt(0) + temp.substring(1).toLowerCase();
            }

            nomesAleatorios.add(nomeFormatado);
        }

        return nomesAleatorios;
    }
}
