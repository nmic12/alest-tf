import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    private static final String NOME_ARQUIVO = "casosTeste//teste00.txt";

    public static void main(String[] args) {

        GeneralTreeOfString arv = new GeneralTreeOfString();

        System.out.println("Iniciando a leitura do arquivo: " + NOME_ARQUIVO);

        // --- Leitura e Processamento ---
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {

            // Leitura n Relações
            String firstLine = br.readLine();
            int numPares = Integer.parseInt(firstLine);
            String line;

            String[] ancestrais = new String[numPares];
            String[] descendentes = new String[numPares];

            // Leitura de todas as relações
            for (int i = 0; i < numPares; i++) {
                line = br.readLine();
                String[] pessoasLinha = line.split(" ");
                ancestrais[i] = pessoasLinha[0];
                descendentes[i] = pessoasLinha[1];
            }

            // Encontrar a raiz
            String root = null;
            for (int i = 0; i < ancestrais.length; i++) {
                boolean ehDescendente = false;
                for (int j = 0; j < descendentes.length; j++) {
                    if (ancestrais[i].equals(descendentes[j])) {
                        ehDescendente = true;
                        break;
                    }
                }
                if (ehDescendente == false) {
                    root = ancestrais[i];
                    break;
                }
            }

            // Construção da Árvore
            arv.add(root, null);
            for (int i = 0; i < numPares; i++) {
                arv.add(descendentes[i], ancestrais[i]);
            }

            //
            //
            String linhaConsulta = br.readLine();
            String[] nomesConsulta = linhaConsulta.split(" ");
            String r = nomesConsulta[0];
            String s = nomesConsulta[1];

            // --- Fim da Leitura e Construção ---

            // --- Consultas ---
            System.out.println("\n--- Árvore Montada. Iniciando Consultas ---");

            System.out.println("\n--- Código DOT para GraphViz ---");
            arv.generateDOT();
            System.out.println("--------------------------------\n");

            // Pergunta 1: Ancestral Comum Mais Próximo?
            // System.out.println("\n1. Ancestral Comum de " + r + " e " + s + ":");
            String ancestral = arv.ancestralMaisProximo("Artemis", "Castor");
            System.out.println("   Resultado: " + ancestral);

            // Pergunta 2: Qual é a altura da árvore?
            int height = arv.height(root);
            System.out.println("2. Altura da árovre: " + height);

            // Pergunta 3: Quantos personagens não possuem descendentes diretos (folhas)?
            int leafs = arv.countLeaves();
            System.out.println("2. Numero de folhas: " + leafs);

            // Pergunta 4: Quantos personagens estão abaixo de um dado personagem t?
            int numNodosAbaixo = arv.countNodes("Crete");
            System.out.println("Número de personagens abaixo de t: " + numNodosAbaixo);

            // Pergunta 5: Quais personagens estão no mesmo nível que t?
            int numNodosMesmoNivel = arv.countMesmoNivel("Melantho");
            System.out.println("Número de personagens no mesmo nivel de t: " + numNodosMesmoNivel);

            // Pergunta 6: O personagem t é ancestral ou descendente de u?
            System.out.println(arv.verificaRelacao("Diomede", "Diomede"));

            // Pergunta 7: Qual o tamanho do caminho mais longo formado apenas por nomes que iniciam com vogais?
            System.out.println(arv.caminhoVogais());

            // Pergunta 8: Qual o tamanho do caminho mais longo formado apenas por nomes que iniciam com consoantes?
            System.out.println(arv.caminhoConsoantes());

        } catch (IOException e) {
            // Mensagem de erro mais clara
            System.out.println("\n Não foi possível ler o arquivo '" + NOME_ARQUIVO + "'.");
        }
    }
}