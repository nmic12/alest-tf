
/** Classe que representa uma árvore geral de inteiros/**
 * @author Isabel H. Manssour, Gabriel Fonseca Silva
 */
import java.util.LinkedList;


public class GeneralTreeOfString {

    // Classe interna Node
    private class Node {

        // Atributos da classe Node ------------------------
        public Node father;
        public String element;
        public LinkedList<Node> subtrees; // lista de nodos filhos

        // Métodos da classe Node ------------------------

        // Construtor
        public Node(String element) {
            father = null;
            this.element = element;
            subtrees = new LinkedList<>();
        }

        // Adiciona um filho a este nodo
        private void addSubtree(Node n) {
            n.father = this;
            subtrees.add(n);
        }

        // Remove o filho n deste nodo
        private boolean removeSubtree(Node n) {
            n.father = null;
            return subtrees.remove(n);
        }

        // Retorna o filho de índice i deste nodo
        public Node getSubtree(int i) {
            if ((i < 0) || (i >= subtrees.size())) {
                throw new IndexOutOfBoundsException();
            }
            return subtrees.get(i);
        }

        // Retorna o número de filhos deste nodo (Grau)
        public int getSubtreesSize() {
            return subtrees.size();
        }
    }

    // Atributos da classe GeneralTreeOfInteger
    private Node root;
    private int count;

    // Metodos da classe GeneralTreeOfInteger ------------------------

    /**
     * Metodo construtor.
     */
    public GeneralTreeOfString() {
        root = null;
        count = 0;
    }

    /**
     * Retorna o numero total de elementos da arvore.
     * 
     * @return count
     */
    public int size() {
        return count;
    }

    /**
     * Procura a referencia do nodo que contem elem.
     * Usa um caminhamento pre-fixado (depth-first) para fazer a busca.
     * 
     * @param elem elemento a ser procurado.
     * @param n    nodo a partir do qual a busca deve ser feita.
     * @return referencia do nodo que contem elem ou NULL se nao encontrado.
     */
    private Node searchNodeRef(String elem, Node n) { // O(n)
        // Caso n seja por qualquer motivo
        if (n == null)
            return null;

        // Visita o nodo atual (nodo raiz da subarvore)
        if (elem.equals(n.element))
            return n;

        // Visita os filhos
        Node aux = null;
        for (int i = 0; i < n.getSubtreesSize(); i++) {
            aux = searchNodeRef(elem, n.getSubtree(i)); // chamada recursiva para cada filho
            if (aux != null)
                return aux;
        }
        return aux;
    }

    /**
     * Adiciona elem como filho de elemFather
     * 
     * @param elem       elemento a ser adicionado na arvore.
     * @param elemFather pai do elemento a ser adicionado.
     * @return true se encontrou elemFather e adicionou elem na arvore,
     *         false caso contrario.
     */
    public boolean add(String elem, String elemFather) { // O(n)
        // Primeiro cria o nodo
        Node n = new Node(elem);

        // Se elemFather for null, insere elem como raiz da arvore
        if (elemFather == null) {
            // Se a arvore nao esta vazia, insere a raiz antiga como filho da nova raiz
            if (root != null) {
                root.father = n;
                n.addSubtree(root);
            }
            root = n;
            count++;
            return true;
        } else { // Senao, procuramos o nodo que contem elemFather
            Node aux = searchNodeRef(elemFather, root);

            if (aux != null) { // se achou elemFather, insere elem na arvore
                n.father = aux;
                aux.addSubtree(n);
                count++;
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se elem esta ou não na arvore.
     * 
     * @param elem a ser procurado.
     * @return true se achar elem, e false caso contrario.
     */
    public boolean contains(String elem) { // O (???)
        // IMPLEMENTE ESTE METODO !!
        return searchNodeRef(elem, root) != null;
    }

    /**
     * Retorna uma lista com todos os elementos da árvore numa ordem de
     * caminhamento em largura (breadth-first).
     * 
     * @return lista com os elementos da arvore na ordem do caminhamento em largura
     */
    public LinkedListOfString breathFirstTraversal() { // MUDANÇA AQUI: retorno
        LinkedListOfString lista = new LinkedListOfString(); // MUDANÇA AQUI: tipo da lista
        if (root == null)
            return lista; // arvore vazia

        Queue<Node> fila = new Queue<Node>();
        fila.enqueue(root);

        while (!fila.isEmpty()) {
            Node aux = fila.dequeue();

            lista.add(aux.element);

            for (int i = 0; i < aux.getSubtreesSize(); i++) {
                fila.enqueue(aux.getSubtree(i));
            }
        }

        return lista;
    }

    /**
     * Retorna uma lista com todos os elementos da árvore numa ordem de
     * caminhamento pre-fixado (depth-first).
     * 
     * @return lista com os elementos da arvore na ordem do caminhamento pre-fixado
     */
    public LinkedList<String> preOrderTraversal() {
        LinkedList<String> lista = new LinkedList<>();
        preOrderTraversalAux(root, lista); // chamada recursiva iniciando com a raiz
        return lista;
    }

    // Método recursivo auxiliar
    private void preOrderTraversalAux(Node n, LinkedList<String> lista) {
        if (n == null)
            return;

        lista.add(n.element);

        for (int i = 0; i < n.getSubtreesSize(); i++) {
            preOrderTraversalAux(n.getSubtree(i), lista);
        }

        return;
    }

    /**
     * Retorna uma lista com todos os elementos da árvore numa ordem de
     * caminhamento pos-fixado (depth-first).
     * 
     * @return lista com os elementos da arvore na ordem do caminhamento pos-fixado
     */
    public LinkedList<String> postOrderTraversal() {
        LinkedList<String> lista = new LinkedList<>();
        postOrderTraversalAux(root, lista); // chamada recursiva iniciando com a raiz
        return lista;
    }

    // Método recursivo auxiliar
    private void postOrderTraversalAux(Node n, LinkedList<String> lista) {
        if (n == null)
            return;

        for (int i = 0; i < n.getSubtreesSize(); i++) {
            postOrderTraversalAux(n.getSubtree(i), lista);
        }

        lista.add(n.element);
        return;
    }

    /**
     * Retorna a altura da arvore a partir de um elemento especificado.
     * 
     * @return altura da arvore, ou -1 se a arvore estiver vazia.
     */
    public int height(String element) {
        Node aux = searchNodeRef(element, root);
        return heightAux(aux, 0);
    }

    private int heightAux(Node n, int h) {

        if (n == null)
            return h - 1;

        int alturaMax = h;

        for (int i = 0; i < n.getSubtreesSize(); i++) {
            int alturaFilho = heightAux(n.getSubtree(i), h + 1);
            if (alturaFilho > alturaMax) {
                alturaMax = alturaFilho;
            }
        }
        return alturaMax;
    }

    public int countLeaves() {
        return countLeavesAux(root);
    }

    private int countLeavesAux(Node n) {
        if (n == null)
            return 0;

        if (n.getSubtreesSize() == 0) {
            return 1;
        }

        int total = 0;
        for (int i = 0; i < n.getSubtreesSize(); i++) {
            total += countLeavesAux(n.getSubtree(i));
        }

        return total;
    }

    // Metodo recursivo auxiliar

    /**
     * Retorna o nivel em que elem esta armazenado.
     * 
     * @param element a ser buscado
     * @return nivel no qual element esta, ou -1 se
     *         nao encontrou element.
     */
    public int level(String element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null)
            return -1;
        int i = -1;
        while (aux != null) {
            aux = aux.father;
            i++;
        }
        return i;
    }

    /**
     * Remove o galho da arvore que tem element na raiz. A remocao inclui o nodo que
     * contem "element".
     * IMPORTANTE: Se o elemento possui filhos, todos os nodos da subarvore serão
     * removidos (afetando o count).
     * 
     * @param element elemento que sera removido junto com sua subarvore.
     * @return true se achou element e removeu o galho, false caso contrario.
     */
    public boolean removeBranch(String element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null)
            return false;
        if (aux == root) {
            aux.subtrees.clear();
            root = null;
            count = 0;
            return true;
        }

        for (int i = 0; i < aux.getSubtreesSize(); i++) {
            removeBranch(aux.getSubtree(i).element);
        }

        Node father = aux.father;
        aux.father = null;
        father.subtrees.remove(aux);
        count--;

        return true;
    }

    /**
     * Retorna o numero de nodos da subarvore cuja raiz eh passada por parametro.
     * 
     * @param n nodo raiz da subarvore
     * @return numero de nodos da subarvore cuja raiz eh n, ou -1 se n for null ou
     *         nao existir na arvore.
     */
    public int countNodes(String element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null)
            return -1;
        return countNodesAux(aux) - 1;
    }

    // Metodo recursivo auxiliar
    private int countNodesAux(Node n) {
        int resposta = 0;
        for (int i = 0; i < n.getSubtreesSize(); i++) {
            resposta += countNodesAux(n.getSubtree(i));
        }
        resposta++;

        return resposta;
    }

    /**
     * Metodos para gerar a saida no formato DOT
     * Esta saida pode ser visualizada no GraphViz
     * Versoes online do GraphViz pode ser encontradas em:
     * http://www.webgraphviz.com/
     * http://viz-js.com/
     * https://dreampuf.github.io/GraphvizOnline
     */

    // Método principal para gerar a saída no formato DOT
    // public void generateDOT() {
    //     if (root == null) {
    //         System.out.println("Arvore vazia!");
    //         return;
    //     }

    //     System.out.println("digraph g { \n");
    //     generateNodesDOT(root);
    //     generateConnectionsDOT(root);
    //     System.out.println("}\n");

    // }

    // // Gera a parte do arquivo DOT que define os nodos
    // private void generateNodesDOT(Node n) {
    //     System.out.println("node [shape = circle];\n");
    //     // System.out.println("node [style = filled];\n");

    //     LinkedList<String> L = breathFirstTraversal();

    //     for (int i = 0; i < L.size(); i++) {
    //         // resultado: node1 [label = "1"]
    //         System.out.println("node" + L.get(i) + " [label = \"" + L.get(i) + "\"]");
    //     }
    // }

    // // Gera a parte do arquivo DOT que define as conexoes (arestas)
    // // Método recursivo
    // private void generateConnectionsDOT(Node n) {
    //     if (n == null) {
    //         return;
    //     }
    //     for (int i = 0; i < n.getSubtreesSize(); i++) {
    //         Node aux = n.getSubtree(i);
    //         System.out.println("node" + n.element + " -> node" + aux.element + ";");
    //         generateConnectionsDOT(aux); // chamada recursiva para cada filho
    //     }
    // }
    // Dado dois personagens mitológicos r e s, quem é o ancestral comum mais
    // próximo dos
    // dois?

    public String ancestralMaisProximo(String elem1, String elem2) {
        Node nodo1 = searchNodeRef(elem1, root);
        Node nodo2 = searchNodeRef(elem2, root);

        if (nodo1 == null || nodo2 == null) {
            return null;
        }

        LinkedListOfString ancestrais1 = new LinkedListOfString();
        Node aux = nodo1;

        while (aux != null) {
            ancestrais1.add(aux.element);
            aux = aux.father;
        }

        LinkedListOfString ancestrais2 = new LinkedListOfString();
        Node aux2 = nodo2;

        while (aux2 != null) {
            ancestrais2.add(aux2.element); 
            aux2 = aux2.father;
        }

        for (int i = 0; i < ancestrais1.size(); i++) {
            String elemento = ancestrais1.get(i); 

            if (ancestrais2.contains(elemento) && !elemento.equals(elem1) && !elemento.equals(elem2)) {
                return elemento;
            }
        }

        return null;
    }

    public int countMesmoNivel(String element) {
        int alturaElemento = level(element);

        if (alturaElemento == -1)
            return 0;

        int count = 0;

        // Usa LinkedListOfString ao invés de LinkedList
        LinkedListOfString todos = breathFirstTraversal();

        // For tradicional com índice ao invés de for-each
        for (int i = 0; i < todos.size(); i++) {
            String elem = todos.get(i);

            if (level(elem) == alturaElemento) {
                count++;
            }
        }

        return count - 1;
    }

    public String verificaRelacao(String element1, String element2) {

        Node nodo1 = searchNodeRef(element1, root);
        Node nodo2 = searchNodeRef(element2, root);

        if (nodo1 == null || nodo2 == null) {
            return null;
        }

        // Verifica se são o mesmo nodo
        if (nodo1 == nodo2) {
            return "Os dois elementos sao os mesmos";
        }

        // Verifica se element1 é ANCESTRAL de element2
        // (sobe de element2 até a raiz procurando element1)
        Node aux = nodo2.father;
        while (aux != null) {
            if (aux == nodo1) {
                return "Ancestral";
            }
            aux = aux.father;
        }

        // Verifica se element1 é DESCENDENTE de element2
        if (searchNodeRef(element1, nodo2) != null) {
            return "Descendente";
        }

        return "Nem ancestral nem descendente";
    }

    public int caminhoVogais() {
        if (root == null)
            return 0;
        return encontrarMaxCaminhoVogais(root);
    }

    private int encontrarMaxCaminhoVogais(Node n) {
        if (n == null) {
            return 0;
        }

        int maxCaminho = 0;

        if (ehVogal(n.element)) {
            maxCaminho = profundidadeVogais(n);
        }

        for (int i = 0; i < n.getSubtreesSize(); i++) {
            int caminhoFilho = encontrarMaxCaminhoVogais(n.getSubtree(i));
            if (caminhoFilho > maxCaminho) {
                maxCaminho = caminhoFilho;
            }
        }

        return maxCaminho;
    }

    private int profundidadeVogais(Node n) {
        if (n == null || !ehVogal(n.element)) {
            return 0;
        }

        int maxProfundidade = 0;

        for (int i = 0; i < n.getSubtreesSize(); i++) {
            Node aux = n.getSubtree(i);
            if (ehVogal(aux.element)) {
                int profundidadeAux = profundidadeVogais(aux);
                if (profundidadeAux > maxProfundidade) {
                    maxProfundidade = profundidadeAux;
                }
            }
        }

        return 1 + maxProfundidade;
    }

    private boolean ehVogal(String element) {
        char c = element.charAt(0);
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public int caminhoConsoantes() {
        if (root == null)
            return 0;
        return encontrarMaxCaminhoConsoantes(root);
    }

    private int encontrarMaxCaminhoConsoantes(Node n) {
        if (n == null) {
            return 0;
        }

        int maxCaminho = 0;

        if (ehConsoante(n.element)) {
            maxCaminho = profundidadeConsoantes(n);
        }

        for (int i = 0; i < n.getSubtreesSize(); i++) {
            int caminhoFilho = encontrarMaxCaminhoConsoantes(n.getSubtree(i));
            if (caminhoFilho > maxCaminho) {
                maxCaminho = caminhoFilho;
            }
        }

        return maxCaminho;
    }

    private int profundidadeConsoantes(Node n) {
        if (n == null || !ehConsoante(n.element)) {
            return 0;
        }

        int maxProfundidade = 0;

        // Procura o maior caminho entre os filhos que começam com consoante
        for (int i = 0; i < n.getSubtreesSize(); i++) {
            Node aux = n.getSubtree(i);
            if (ehConsoante(aux.element)) {
                int profundidadeAux = profundidadeConsoantes(aux);
                if (profundidadeAux > maxProfundidade) {
                    maxProfundidade = profundidadeAux;
                }
            }
        }

        return 1 + maxProfundidade;
    }

    private boolean ehConsoante(String element) {
        char c = element.charAt(0);
        return !ehVogal(element) && Character.isLetter(c);
    }

}