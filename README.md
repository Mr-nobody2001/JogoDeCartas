# JogoDeCartas
Esse é um pequeno projeto de um jogo de cartas. Projeto de faculdade criado para a matéria de Técnicas de programação.

Regras do jogo:

01.Quantidade de participantes: 03 (no mínimo) a 06(no máximo) <br>
02.Nipes: 1=ouro, 2=copas, 3=paus, 4=espadas <br>
03.Quantidade de cartas: 104 (2 jogos ou grupos) <br>
04.Valores padrões das cartas:2,3,4,5,6,7,8,9,10,J (valor 11), Q (valor 12), K(valor 13), A(valor 14) <br>
05.Para cada jogador são geradas (aleatoriamente) 3 a 4 cartas. Se for 3 cartas, deve-se gerar 2 de um grupo 
de cartas e 1 do outro grupo de cartas. Se for 4 cartas, deve-se gerar 2 cartas de cada grupo. Essa distinção é 
importante, pois uma carta gerada de um grupo não poderá ser gerada de novo para outro jogador no 
mesmo jogo. <br>
06.A última carta não pode ser mostrada para o jogador, pois o jogador poderá descarta-la e, neste caso, a 
carta volta para o seu grupo de origem. <br>
07.Em caso de empate entre dois jogadores, efetuar a seleção de mais uma carta para cada um dos 
jogadores, de tal forma que deve-se somar o valor da nova carta como uma casa decimal no total do 
respectivo jogador. Efetuar esse passo até que não ocorra mais empate. <br>
10.No final, avaliar quem ganhou <br>
Considerar: <br>
a)Cada carta possui um peso, de acordo com o nipe (ver item 03). Assim, um 7 de paus é maior que o 7 de 
copas, pois o nipe “paus” tem peso 3 e o nipe “copas” tem peso 2 <br>
b)Cada jogador terá a soma total dos pontos , conforme os respectivos pesos das cartas e também conforme 
os complementos decimais, caso existam. O vencedor é aquele com o maior número total de pontos <br>
