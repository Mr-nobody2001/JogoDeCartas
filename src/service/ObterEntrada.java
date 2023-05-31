package service;

import poo.trabalho5.entities.exceptions.InputDomainException;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public abstract class ObterEntrada {
    public static Integer obterOpcaoMenu() throws NumberFormatException {
        // Testado
        int opcao;

        String entrada;

        String menu = "Escolha uma opção:\n" +
                "1.Iniciar jogo com nomes aleatórios\n" +
                "2.Iniciar jogo com nomes informados pelo usuário\n" +
                "3.Mostrar resultado novamente\n" +
                "4.Consulta informações por jogador\n" +
                "5.Fim";

        JOptionPane.showMessageDialog(null, menu, "Menu",
                JOptionPane.INFORMATION_MESSAGE);

        entrada = JOptionPane.showInputDialog("Opcão: ");

        opcao = Integer.parseInt(entrada);

        if (opcao < 1 || opcao > 5) {
            throw new InputDomainException("Opção inválida: opção inexistente no menu");
        }

        return opcao;
    }

    public static Integer obterNumeroJogadores(int numeroJogadoresMin, int numeroJogadoresMax) throws NumberFormatException {
        // Testado
        int numeroJogadores;

        String entrada;

        entrada = JOptionPane.showInputDialog("Qual será a quantidade de jogadores: ");

        numeroJogadores = Integer.parseInt(entrada);

        if (numeroJogadores < numeroJogadoresMin || numeroJogadores > numeroJogadoresMax) {
            throw new InputDomainException("Quantidade inválida: o número mínimo de jogadores é " + numeroJogadoresMin +
                    " e o máximo é " + numeroJogadoresMax);
        }

        return numeroJogadores;
    }

    public static Integer obterNumeroCartas(int numeroCartasMin, int numeroCartasMax) throws NumberFormatException {
        // Testado
        int numeroCartas;

        String entrada;

        entrada = JOptionPane.showInputDialog("Qual será a quantidade de cartas distribuídas: ");

        numeroCartas = Integer.parseInt(entrada);

        if (numeroCartas < numeroCartasMin || numeroCartas > numeroCartasMax) {
            throw new InputDomainException("Quantidade inválida: o número mínimo de cartas é " + numeroCartasMin +
                    " e o máximo é " + numeroCartasMax);
        }

        return numeroCartas;
    }

    public static Integer obterNumeroCaracteres(int numeroCaracteresMax) throws NumberFormatException {
        int numeroCaracteres;

        String entrada;

        entrada = JOptionPane.showInputDialog("Qual será o número de caracteres do(s) nome(s): ");

        numeroCaracteres = Integer.parseInt(entrada);

        if (numeroCaracteres > numeroCaracteresMax) {
            throw new InputDomainException("Quantidade inválida: o número máximo de caracteres é " + numeroCaracteresMax);
        }

        return numeroCaracteres;
    }

    public static List<String> obterNomeJogador(int numeroJogadores, int numeroCaracteres) {
        List<String> nomes = new ArrayList<>(numeroJogadores);

        String entrada, nomeFormatado;
        String[] nomeBruto;

        nomeFormatado = "";

        for (int i = 0; i < numeroJogadores; i++) {
            do {
                entrada = JOptionPane.showInputDialog("Insira o nome da " + (i + 1) + " pessoa: ");

                while (entrada.length() > numeroCaracteres) {
                    JOptionPane.showMessageDialog(null, "O número máximo de caracteres é "
                            + numeroCaracteres, "Erro", JOptionPane.ERROR_MESSAGE);
                    entrada = JOptionPane.showInputDialog("Insira o nome da " + (i + 1) + " pessoa: ");
                }

                nomeBruto = entrada.split(" ");

                for (String temp : nomeBruto) {
                    nomeFormatado += temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
                }

                nomes.add(nomeFormatado);

                nomeFormatado = "";
            } while (entrada.length() == 0);
        }

        return nomes;
    }

    public static Boolean obterRespostaBooleana() {
        String resposta;

        resposta = JOptionPane.showInputDialog("Deseja ficar com a última carta ? (S, N) ");

        if (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
            JOptionPane.showMessageDialog(null, "Input inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            resposta = JOptionPane.showInputDialog("Deseja ficar com a última carta ? (S, N) ");
        }

        return resposta.equalsIgnoreCase("S");
    }

    public static String obterNomeJogador(int numeroCaracteres) {
        String nome;

        nome = JOptionPane.showInputDialog("Insira o nome do jogador: ");

        if (nome.length() > numeroCaracteres) {
            throw new InputDomainException("O número máximo de caracteres é " + numeroCaracteres);
        }

        return nome;
    }
}
