
/**
 * Implementação de uma fila (queue) usando lista encadeada.
 * O <T> é o tipo genérico do elemento armazenado na fila.
 */
public class Queue<T> {
    
    // Classe interna para representar um nodo da fila
    private class Node {
        public T element;
        public Node next;
        public Node(T e) {
            element = e;
            next = null;
        }
    }
    
    // Atributos da classe Fila (Queue)
    private int count;
    private Node head;
    private Node tail;
    
    public Queue() {
        count = 0;
        head = null;
        tail = null;
    }
    
    public void enqueue(T element) {
        Node n = new Node(element);
        if (count==0) {
            head = n;
        }
        else {
            tail.next = n;      
        }
        tail = n;
        count++;
    }
    
    public T dequeue() {
        if (count == 0)
            throw new EmptyQueueException("Fila vazia!"); // Erro
        T aux = head.element;
        head = head.next;
        count--;
        return aux;
    }
    
    public T head() {
        if (count == 0)
            throw new EmptyQueueException("Fila vazia!"); // Erro
        return head.element;
    }
    
    public boolean isEmpty() {
        return (count==0);
    }
    
    public int size() {
        return count;
    }
    
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }
}
