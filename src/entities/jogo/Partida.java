package entities.jogo;

import entities.baralho.Baralho;
import entities.baralho.Carta;
import entities.exceptions.InternalException;
import service.GerarMao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {
    private final List<Jogador> participantes;
    private Jogador vencedor;

    public Partida(List<Jogador> participantes) {
        this.participantes = participantes;
    }

    public List<Jogador> obterVencedor() {
        List<Jogador> vencedores = new ArrayList<>();

        Double maiorPontuacao = -1.0;

        for (Jogador temp : this.participantes) {
            if (maiorPontuacao.compareTo(temp.getPontos()) < 0) {
                maiorPontuacao = temp.getPontos();
            }
        }

        for (Jogador temp : this.participantes) {
            if (maiorPontuacao.equals(temp.getPontos())) {
                vencedores.add(temp);
            }
        }

        return vencedores;
    }

    public Jogador desempatar(List<Baralho> baralhos, List<Jogador> vencedores) {
        Random random = new Random();

        int numeroBaralho = random.nextInt(0, baralhos.size());

        do {
            for (Jogador temp : vencedores) {
                Carta novaCarta = GerarMao.gerar(baralhos.get(numeroBaralho),
                        1, false).get(0);

                temp.getComplementos().add(novaCarta);
                temp.setPontos(temp.gerarPontuacao());
            }

            vencedores = this.obterVencedor();
        } while (vencedores.size() != 1);

        return vencedores.get(0);
    }

    public List<Jogador> getParticipantes() {
        return participantes;
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
    }

    public String resumirPartida(int numeroCartas) {
        StringBuilder mensagem = new StringBuilder();

        mensagem.append(String.format("%-30s", "Jogador"));

        for (int i = 0; i < numeroCartas; i++) {
            mensagem.append(" Carta").append(i + 1).append(" ");
        }

        mensagem.append("Complementos Pontos\n");

        for (Jogador temp : this.participantes) {
            mensagem.append(String.format("%-30s", temp.getNome()));

            for (Carta temp2 : temp.getCartas()) {
                mensagem.append(String.format("%-8s", temp2.getNome()));
            }

            if (temp.getComplementos().size() != 0) {
                for (Carta temp2 : temp.getComplementos()) {
                    mensagem.append(String.format("%-14s", temp2.getNome())).append(" ,");
                }
            } else {
                mensagem.append(String.format("%-14s", "............"));
            }

            mensagem.append(String.format("%.2f", temp.getPontos())).append("\n");
        }

        return mensagem.toString();
    }

    public String resumirPartidaJogador(String nomeJogador) {
        StringBuilder mensagem = new StringBuilder();
        Jogador jogador = null;

        for (Jogador temp : this.participantes) {
            if (temp.getNome().equalsIgnoreCase(nomeJogador)) {
                jogador = temp;
                break;
            }
        }

        if (jogador == null) {
            throw new InternalException("Erro: Jogador não encontrado");
        }

        mensagem.append(String.format("%-30s", "Jogador"));

        for (int j = 0; j < jogador.getCartas().size(); j++) {
            mensagem.append(" Carta").append(j + 1).append(" ");
        }

        mensagem.append("Complementos Pontos\n");

        mensagem.append(String.format("%-30s", jogador.getNome()));

        for (Carta temp : jogador.getCartas()) {
            mensagem.append(String.format("%-8s", temp.getNome()));
        }

        if (jogador.getComplementos().size() != 0) {
            for (Carta temp2 : jogador.getComplementos()) {
                mensagem.append(String.format("%-14s", temp2.getNome())).append(" ,");
            }
        } else {
            mensagem.append(String.format("%-14s", "............"));
        }

        mensagem.append(String.format("%.2f", jogador.getPontos())).append("\n");

        return mensagem.toString();
    }
}
