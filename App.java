import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    private static final String NOME_ARQUIVO = "casosTeste//teste05.txt";

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

            // Leitura dos nomes para consulta
            String linhaConsulta = br.readLine();
            String[] nomesConsulta = linhaConsulta.split(" ");
            String r = nomesConsulta[0];
            String s = nomesConsulta[1];

            // --- Fim da Leitura e Construção ---

            System.out.println("\n=== MEDIÇÃO DE TEMPO DE EXECUÇÃO ===\n");

            // Pergunta 1: Ancestral Comum Mais Próximo
            long inicio = System.nanoTime();
            String ancestral = arv.ancestralMaisProximo(r, s);
            long fim = System.nanoTime();
            System.out.println("1. ancestralMaisProximo(\"" + r + "\", \"" + s + "\")");
            System.out.println("   Resultado: " + ancestral);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 2: Altura da árvore
            inicio = System.nanoTime();
            int height = arv.height(root);
            fim = System.nanoTime();
            System.out.println("2. height(\"" + root + "\")");
            System.out.println("   Resultado: " + height);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 3: Número de folhas
            inicio = System.nanoTime();
            int leafs = arv.countLeaves();
            fim = System.nanoTime();
            System.out.println("3. countLeaves()");
            System.out.println("   Resultado: " + leafs);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 4: Personagens abaixo de um nodo
            inicio = System.nanoTime();
            int numNodosAbaixo = arv.countNodes("Crete");
            fim = System.nanoTime();
            System.out.println("4. countNodes(\"Crete\")");
            System.out.println("   Resultado: " + numNodosAbaixo);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 5: Personagens no mesmo nível
            inicio = System.nanoTime();
            int numNodosMesmoNivel = arv.countMesmoNivel("Melantho");
            fim = System.nanoTime();
            System.out.println("5. countMesmoNivel(\"Melantho\")");
            System.out.println("   Resultado: " + numNodosMesmoNivel);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 6: Relação entre personagens
            inicio = System.nanoTime();
            String relacao = arv.verificaRelacao("Diomede", "Artemis");
            fim = System.nanoTime();
            System.out.println("6. verificaRelacao(\"Diomede\", \"Artemis\")");
            System.out.println("   Resultado: " + relacao);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 7: Caminho mais longo com vogais
            inicio = System.nanoTime();
            int caminhoVogais = arv.caminhoVogais();
            fim = System.nanoTime();
            System.out.println("7. caminhoVogais()");
            System.out.println("   Resultado: " + caminhoVogais);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            // Pergunta 8: Caminho mais longo com consoantes
            inicio = System.nanoTime();
            int caminhoConsoantes = arv.caminhoConsoantes();
            fim = System.nanoTime();
            System.out.println("8. caminhoConsoantes()");
            System.out.println("   Resultado: " + caminhoConsoantes);
            System.out.println("   Tempo: " + (fim - inicio) / 1000000.0 + " ms\n");

            System.out.println("=== FIM DAS MEDIÇÕES ===");

        } catch (IOException e) {
            System.out.println("\nNão foi possível ler o arquivo '" + NOME_ARQUIVO + "'.");
        }
    }
}
