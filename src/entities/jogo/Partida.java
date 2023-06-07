package entities.jogo;

import entities.baralho.Baralho;
import entities.baralho.Carta;
import entities.exceptions.InternalException;
import service.GerarMao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
    private final List<Jogador> jogadores = new ArrayList<>();
    private final List<List<Carta>> maoJogador = new ArrayList<>();
    private final List<List<Carta>> complementosJogador = new ArrayList<>();

    public Partida() {
    }

    public List<Jogador> obterVencedor() {
        List<Jogador> vencedores = new ArrayList<>();

        Double maiorPontuacao = -1.0;

        for (Jogador temp : this.jogadores) {
            if (maiorPontuacao.compareTo(gerarPontuacao(temp.getNome())) < 0) {
                maiorPontuacao = gerarPontuacao(temp.getNome());
            }
        }

        for (Jogador temp : this.jogadores) {
            if (maiorPontuacao.equals(gerarPontuacao(temp.getNome()))) {
                vencedores.add(temp);
            }
        }

        return vencedores;
    }

    public Jogador desempatar(List<Baralho> baralhos, List<Jogador> vencedores) {
        Random random = new Random();

        int numeroBaralho = random.nextInt(0, baralhos.size());

        do {
            for (Jogador vencedor : vencedores) {
                Carta novaCarta = GerarMao.gerar(baralhos.get(numeroBaralho),
                        1, false).get(0);

                for (int j = 0; j < this.getJogadores().size(); j++) {
                    if (this.getJogadores().get(j).getNome().equals(vencedor.getNome())) {
                        this.getComplementosJogador().get(j).add(novaCarta);
                    }
                }
            }

            vencedores = this.obterVencedor();
        } while (vencedores.size() != 1);

        return vencedores.get(0);
    }

    public Double gerarPontuacao(int i) {
        double soma = 0;

        for (Carta temp : this.getMaoJogador().get(i)) {
            soma += temp.getNaipe().getValue() * temp.getValor();
        }

        for (Carta temp : this.getComplementosJogador().get(i)) {
            soma += (temp.getNaipe().getValue() * temp.getValor()) / 10.0;
        }

        return soma;
    }

    public Double gerarPontuacao(String nome) {
        int i;

        for (i = 0; i < this.getJogadores().size(); i++) {
            if (this.getJogadores().get(i).getNome().equals(nome)) {
                break;
            }
        }

        return gerarPontuacao(i);
    }

    public String resumirPartida(int numeroCartas) {
        StringBuilder mensagem = new StringBuilder();

        mensagem.append(String.format("%-30s", "Jogador"));

        for (int i = 0; i < numeroCartas; i++) {
            mensagem.append(" Carta").append(i + 1).append(" ");
        }

        mensagem.append("Complementos Pontos\n");

        for (int i = 0; i < this.getJogadores().size(); i++) {
            mensagem.append(String.format("%-30s", this.getJogadores().get(i).getNome()));

            for (Carta temp2 : this.getMaoJogador().get(i)) {
                mensagem.append(String.format("%-8s", temp2.getNome()));
            }

            if (this.getComplementosJogador().get(i).size() != 0) {
                for (Carta temp2 : this.getComplementosJogador().get(i)) {
                    mensagem.append(String.format("%-14s", temp2.getNome())).append(" ,");
                }
            } else {
                mensagem.append(String.format("%-14s", "............"));
            }

            mensagem.append(String.format("%.2f", gerarPontuacao(i))).append("\n");
        }

        return mensagem.toString();
    }

    public String resumirPartidaJogador(String nomeJogador) {
        StringBuilder mensagem = new StringBuilder();
        int i;

        for (i = 0; i < this.getJogadores().size(); i++) {
            if (this.getJogadores().get(i).getNome().equalsIgnoreCase(nomeJogador)) {
                break;
            }
        }

        if (i == this.getJogadores().size()) {
            throw new InternalException("Erro: Jogador não encontrado");
        }

        mensagem.append(String.format("%-30s", "Jogador"));

        for (int j = 0; j < this.getMaoJogador().get(i).size(); j++) {
            mensagem.append(" Carta").append(j + 1).append(" ");
        }

        mensagem.append("Complementos Pontos\n");

        mensagem.append(String.format("%-30s", this.getJogadores().get(i).getNome()));

        for (Carta temp : this.getMaoJogador().get(i)) {
            mensagem.append(String.format("%-8s", temp.getNome()));
        }

        if (this.getComplementosJogador().get(i).size() != 0) {
            for (Carta temp2 : this.getComplementosJogador().get(i)) {
                mensagem.append(String.format("%-14s", temp2.getNome())).append(" ,");
            }
        } else {
            mensagem.append(String.format("%-14s", "............"));
        }

        mensagem.append(String.format("%.2f", gerarPontuacao(i))).append("\n");

        return mensagem.toString();
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<List<Carta>> getMaoJogador() {
        return maoJogador;
    }

    public List<List<Carta>> getComplementosJogador() {
        return complementosJogador;
    }
}
