package application;

import entities.jogo.Jogador;
import entities.jogo.Partida;
import entities.baralho.Baralho;
import entities.baralho.Carta;
import service.GerarMao;
import service.GerarNomes;
import service.ObterEntrada;
import poo.trabalho5.entities.exceptions.InputDomainException;
import poo.trabalho5.entities.exceptions.InternalException;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

public class Jogo {
    public static void main(String[] args) {
        int numeroJogadoresMin, numeroJogadoresMax, numeroCartasMin, numeroCartasMax, numeroCaracteresMax;

        numeroJogadoresMin = numeroJogadoresMax = numeroCartasMin = numeroCartasMax = numeroCaracteresMax = 0;

        String path = "C:\\Users\\gabri\\OneDrive\\Documentos\\Conteúdo Faculdade\\Segundo Semestre\\Técnicas de programação" +
                "\\trabalhos\\trabalho5\\src\\files\\configuracao.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();

            String[] dadosBruto;

            while (line != null) {
                dadosBruto = line.split(",");

                if (dadosBruto[0].equalsIgnoreCase("NumeroJogadores")) {
                    numeroJogadoresMin = Integer.parseInt(dadosBruto[1]);
                    numeroJogadoresMax = Integer.parseInt(dadosBruto[2]);
                } else {
                    if (dadosBruto[0].equalsIgnoreCase("NumeroCartas")) {
                        numeroCartasMin = Integer.parseInt(dadosBruto[1]);
                        numeroCartasMax = Integer.parseInt(dadosBruto[2]);
                    } else {
                        if (dadosBruto[0].equalsIgnoreCase("NumeroCaracteres")) {
                            numeroCaracteresMax = Integer.parseInt(dadosBruto[1]);
                        }
                    }
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input inválido: " +
                    "Caracteres não numéricos não são permitidos", "Erro de formato de input", JOptionPane.ERROR_MESSAGE);
        }

        Partida ultimaPartida = null;

        int opcao = -1;

        do {
            try {
                if (opcao == -1) {
                    opcao = ObterEntrada.obterOpcaoMenu();
                }

                switch (opcao) {
                    case 1, 2 -> {
                        List<Baralho> baralhos = new ArrayList<>(2);

                        Random random = new Random();

                        for (int i = 0; i < 2; i++) {
                            baralhos.add(new Baralho());
                        }

                        int numeroJogadores, numeroCartas;

                        numeroJogadores = ObterEntrada.obterNumeroJogadores(numeroJogadoresMin, numeroJogadoresMax);
                        numeroCartas = ObterEntrada.obterNumeroCartas(numeroCartasMin, numeroCartasMax);

                        List<String> nomes;

                        int numeroCaracteres;

                        numeroCaracteres = ObterEntrada.obterNumeroCaracteres(numeroCaracteresMax);

                        if (opcao == 1) {
                            nomes = GerarNomes.gerar(numeroJogadores, numeroCaracteres);
                        } else {
                            nomes = ObterEntrada.obterNomeJogador(numeroJogadores, numeroCaracteres);
                        }

                        List<Jogador> jogadores = new ArrayList<>(numeroJogadores);

                        int numeroBaralho;

                        for (int i = 0; i < numeroJogadores; i++) {
                            numeroBaralho = random.nextInt(0, baralhos.size());

                            jogadores.add(new Jogador(nomes.get(i), GerarMao.gerar(baralhos.get(numeroBaralho),
                                    numeroCartas, true)));

                            String mensagem = "Nome: " + jogadores.get(i).getNome() + " \nCartas: ";

                            int j;

                            for (j = 0; j < numeroCartas - 1; j++) {
                                mensagem += jogadores.get(i).getCartas().get(j).getNome() + " ";
                            }

                            Carta cartaOpcional = jogadores.get(i).getCartas().get(j);

                            System.out.println(cartaOpcional.getNome());

                            JOptionPane.showMessageDialog(null, mensagem,
                                    "Jogador " + (i + 1) + " essa é a sua mão", JOptionPane.INFORMATION_MESSAGE);

                            if (ObterEntrada.obterRespostaBooleana()) {
                                switch (cartaOpcional.getPeso()) {
                                    case 1 -> baralhos.get(numeroBaralho).getNaipeOuro().remove(cartaOpcional);
                                    case 2 -> baralhos.get(numeroBaralho).getNaipeCopas().remove(cartaOpcional);
                                    case 3 -> baralhos.get(numeroBaralho).getNaipePaus().remove(cartaOpcional);
                                    case 4 -> baralhos.get(numeroBaralho).getNaipeEspadas().remove(cartaOpcional);
                                }
                            } else {
                                jogadores.get(i).getCartas().remove(cartaOpcional);

                                cartaOpcional = GerarMao.gerarNaipeEspecifico(baralhos.get(numeroBaralho),
                                        1, cartaOpcional.getPeso()).get(0);

                                jogadores.get(i).getCartas().add(cartaOpcional);
                            }

                            JOptionPane.showMessageDialog(null, cartaOpcional.getNome(),
                                    "Jogador " + (i + 1) + " essa é a sua última carta", JOptionPane.INFORMATION_MESSAGE);

                            jogadores.get(i).setPontos(jogadores.get(i).gerarPontuacao());
                        }

                        Partida partida = new Partida(jogadores);

                        String mensagem = "";

                        for (Jogador temp : jogadores) {
                            mensagem += "Nome: " + temp.getNome() + " \n" + "Pontuação: " + temp.getPontos() + "\n";
                        }

                        JOptionPane.showMessageDialog(null, mensagem,
                                "Pontuações: ", JOptionPane.INFORMATION_MESSAGE);

                        List<Jogador> vencedores = partida.obterVencedor();

                        if (vencedores.size() != 1) {
                            vencedores.add(partida.desempatar(baralhos, vencedores));
                        }

                        JOptionPane.showMessageDialog(null, "Nome: " + vencedores.get(0).getNome()
                                        + "\nPontuação: " + vencedores.get(0).getPontos(),
                                "Vencedor", JOptionPane.INFORMATION_MESSAGE);

                        partida.setVencedor(vencedores.get(0));

                        ultimaPartida = partida;

                        opcao = -1;
                    }

                    case 3 -> {
                        opcao = -1;

                        if (ultimaPartida == null) {
                            throw new InternalException("Erro: Não há partidas registradas no histórico");
                        }

                        JOptionPane.showMessageDialog(null, ultimaPartida.resumirPartida(numeroCartasMax),
                                "Resumo da última partida", JOptionPane.INFORMATION_MESSAGE);
                    }

                    case 4 -> {
                        opcao = -1;

                        if (ultimaPartida == null) {
                            throw new InternalException("Erro: Não há partidas registradas no histórico");
                        }

                        String nomeJogador = ObterEntrada.obterNomeJogador(numeroCaracteresMax);

                        JOptionPane.showMessageDialog(null, ultimaPartida.resumirPartidaJogador(nomeJogador),
                                "Resumo da última partida", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input inválido: " +
                        "Caracteres não numéricos não são permitidos", "Erro de formato de input", JOptionPane.ERROR_MESSAGE);
            } catch (InternalException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro interno",
                        JOptionPane.ERROR_MESSAGE);
            } catch (InputDomainException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Input inválido",
                        JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro inesperado",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } while (opcao != 5);

        JOptionPane.showMessageDialog(null, "Fechando programa",
                "", JOptionPane.INFORMATION_MESSAGE);
    }
}
