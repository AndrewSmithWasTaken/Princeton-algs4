import java.util.Iterator;
/**
 *
 * @author andrew
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    private Node head, tail;
    private int size;
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
        
        private Node(Item item) {
            this.item = item;
            this.next = null;
            this.previous = null;
        }
    }
    
    // construct an empty deque
    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }      
    
    // is the deque empty?       
    public boolean isEmpty() {
        return size == 0;
    }   
    
    // return the number of items on the deque 
    public int size() {
        return size;
    }     
    
    // add the item to the front       
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Can't add null items.");
        }   
        if (!isEmpty()) {
            Node newNode = new Node(item);
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
            
        }
        else {
            head = new Node(item);
            tail = head;
        }
        size++;
    }  
    
    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Can't add null items.");
        }
        if (!isEmpty()) {
            Node newNode = new Node(item);
            tail.next = newNode;
            newNode.previous = tail;
            newNode.next = null;
            tail = newNode;
        }  
        else {
            Node newNode = new Node(item);
            head = newNode;
            tail = newNode;
        } 
        size++;
    }
    
    // remove and return the item from the front  
    public Item removeFirst() {              
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No!"); 
        } 
        Item tmp = head.item;
        head = head.next;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head.previous = null;
        }
        size--;
        return tmp;
    }
    
    // remove and return the item from the end
    public Item removeLast() {                 
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No!"); 
        }
        Item tmp = tail.item;
        tail = tail.previous;
        if (size == 1) {
            tail = null;
            head = null;
        }
        else {
            tail.next = null;
        }
        size--;
        return tmp;
    }
    
    // return an iterator over items in order from front to end  
    public Iterator<Item> iterator() { 
        return new DequeIterator(); 
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = head;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Can't do that");
        }   
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            if (current.next == null) {
                throw new java.util.NoSuchElementException("No!!!!!!!!");
            }
            else {
                return item;
            }
        }
    }
    
    // unit testing        
    public static void main(String[] args) {
//        Deque<Integer> deque = new Deque<>();
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.removeLast();
//        deque.removeLast();
//        deque.removeLast();
//        deque.removeLast();
//        deque.removeLast();
        Deque<Integer> deque = new Deque<>();
        System.out.println("Empty?: " + deque.isEmpty());
        deque.addLast(4);
        deque.addLast(4);
        deque.addLast(4);
        System.out.println(deque.removeLast()); //     ==> 1
        System.out.println("-----------------");
        System.out.println("Items in deque: ");
        for (int i : deque) {
            System.out.println(i);
        }
        System.out.println("Empty?: " + deque.isEmpty());
        System.out.println("Size: " + deque.size());
    }
}