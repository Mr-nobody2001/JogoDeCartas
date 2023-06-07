package application;

import entities.jogo.Jogador;
import entities.jogo.Partida;
import entities.baralho.Baralho;
import entities.baralho.Carta;
import service.GerarMao;
import service.GerarNomes;
import service.ObterEntrada;
import entities.exceptions.InputDomainException;
import entities.exceptions.InternalException;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Properties;
import java.io.FileInputStream;

public class Jogo {
    public static void main(String[] args) {
        Properties properties = new Properties();

        String path = "C:\\Users\\gabri\\OneDrive\\Documentos\\Conteúdo Faculdade\\Segundo Semestre\\Técnicas de programação" +
                "\\trabalhos\\trabalho5\\src\\files\\configuracao.txt";

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
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

                        numeroJogadores = ObterEntrada.obterNumeroJogadores(Integer.parseInt(properties.getProperty("numeroMinJogadores")),
                                Integer.parseInt(properties.getProperty("numeroMaxJogadores")));
                        numeroCartas = ObterEntrada.obterNumeroCartas(Integer.parseInt(properties.getProperty("numeroMinCartas")),
                                Integer.parseInt(properties.getProperty("numeroMaxCartas")));

                        List<String> nomes;

                        int numeroCaracteres;

                        numeroCaracteres = ObterEntrada.obterNumeroCaracteres(Integer.parseInt(properties.getProperty("numeroCaracteres")));

                        if (opcao == 1) {
                            nomes = GerarNomes.gerar(numeroJogadores, numeroCaracteres);
                        } else {
                            nomes = ObterEntrada.obterNomeJogador(numeroJogadores, numeroCaracteres);
                        }

                        Partida partida = new Partida();

                        int numeroBaralho;

                        for (int i = 0; i < numeroJogadores; i++) {
                            numeroBaralho = random.nextInt(0, baralhos.size());

                            partida.getJogadores().add(new Jogador(nomes.get(i)));

                            partida.getMaoJogador().add(GerarMao.gerar(baralhos.get(numeroBaralho),
                                    numeroCartas, true));

                            partida.getComplementosJogador().add(new ArrayList<>());

                            String mensagem = "Nome: " + partida.getJogadores().get(i).getNome() + " \nCartas: ";

                            int j;

                            for (j = 0; j < numeroCartas - 1; j++) {
                                mensagem += partida.getMaoJogador().get(i).get(j).getNome() + " ";
                            }

                            Carta cartaOpcional = partida.getMaoJogador().get(i).get(j);

                            JOptionPane.showMessageDialog(null, mensagem,
                                    "Jogador " + (i + 1) + " essa é a sua mão", JOptionPane.INFORMATION_MESSAGE);

                            if (ObterEntrada.obterRespostaBooleana()) {
                                switch (cartaOpcional.getNaipe().getValue()) {
                                    case 1 -> baralhos.get(numeroBaralho).getNaipeOuro().remove(cartaOpcional);
                                    case 2 -> baralhos.get(numeroBaralho).getNaipeCopas().remove(cartaOpcional);
                                    case 3 -> baralhos.get(numeroBaralho).getNaipePaus().remove(cartaOpcional);
                                    case 4 -> baralhos.get(numeroBaralho).getNaipeEspadas().remove(cartaOpcional);
                                }
                            } else {
                                partida.getMaoJogador().get(i).remove(cartaOpcional);

                                cartaOpcional = GerarMao.gerarNaipeEspecifico(baralhos.get(numeroBaralho),
                                        1, cartaOpcional.getNaipe().getValue()).get(0);

                                partida.getMaoJogador().get(i).add(cartaOpcional);
                            }

                            JOptionPane.showMessageDialog(null, cartaOpcional.getNome(),
                                    "Jogador " + (i + 1) + " essa é a sua última carta", JOptionPane.INFORMATION_MESSAGE);

                        }

                        String mensagem = "";

                        for (Jogador temp : partida.getJogadores()) {
                            mensagem += "Nome: " + temp.getNome() +
                                    " \n" + "Pontuação: " + partida.gerarPontuacao(temp.getNome()) + "\n";
                        }

                        JOptionPane.showMessageDialog(null, mensagem,
                                "Pontuações: ", JOptionPane.INFORMATION_MESSAGE);

                        List<Jogador> vencedores = partida.obterVencedor();

                        if (vencedores.size() != 1) {
                            vencedores.add(partida.desempatar(baralhos, vencedores));
                        }

                        JOptionPane.showMessageDialog(null, "Nome: " + vencedores.get(0).getNome()
                                        + "\nPontuação: " + partida.gerarPontuacao(vencedores.get(0).getNome()),
                                "Vencedor", JOptionPane.INFORMATION_MESSAGE);

                        ultimaPartida = partida;

                        opcao = -1;
                    }

                    case 3 -> {
                        opcao = -1;

                        if (ultimaPartida == null) {
                            throw new InternalException("Erro: Não há partidas registradas no histórico");
                        }

                        JOptionPane.showMessageDialog(null, ultimaPartida.resumirPartida(Integer.parseInt(properties.getProperty("numeroMaxCartas"))),
                                "Resumo da última partida", JOptionPane.INFORMATION_MESSAGE);
                    }

                    case 4 -> {
                        opcao = -1;

                        if (ultimaPartida == null) {
                            throw new InternalException("Erro: Não há partidas registradas no histórico");
                        }

                        String nomeJogador = ObterEntrada.obterNomeJogador(Integer.parseInt(properties.getProperty("numeroCaracteres")));

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
