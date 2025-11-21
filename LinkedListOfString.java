public class LinkedListOfString {
    
    // classe interna para representar um nodo da lista
    private class Node {
        public String element;
        public Node next;
        
        public Node(String element) {
            this.element = element;
            this.next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int count;
    
    // construtor
    public LinkedListOfString() {
        head = null;
        tail = null;
        count = 0;
    }
    
    // adiciona no final da lista
    public boolean add(String element) {
        Node n = new Node(element);
        if (count == 0) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        count++;
        return true;
    }
    
    // retorna o elemento que ta no index especificado
    public String get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }
    
    // remove o elemento no index especificado
    public String remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
        }
        
        String removedElement;
        
        if (index == 0) {
            removedElement = head.element;
            head = head.next;
            if (count == 1) {
                tail = null;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedElement = current.next.element;
            current.next = current.next.next;
            if (index == count - 1) {
                tail = current;
            }
        }
        count--;
        return removedElement;
    }
    
    // remove a primeira ocorrencia do elemento
    public boolean remove(String element) {
        if (count == 0) {
            return false;
        }
        
        if (head.element.equals(element)) {
            head = head.next;
            if (count == 1) {
                tail = null;
            }
            count--;
            return true;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.element.equals(element)) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current;
                }
                count--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // checka se a lista contem um certo elemento
    public boolean contains(String element) {
        Node current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // retorna o tamanho da lista
    public int size() {
        return count;
    }
    
    // retorna se a lista ta vazia
    public boolean isEmpty() {
        return count == 0;
    }
    
    // limpa a lista inteira
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }
    
    // retorna o index da primeira ocorrencia do elemento
    public int indexOf(String element) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.element.equals(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }
}