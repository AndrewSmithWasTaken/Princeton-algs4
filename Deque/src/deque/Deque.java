package deque;
import java.util.Iterator;
/**
 *
 * @author andrew
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
    
    // construct an empty deque
    public Deque() {
        size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
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
        Node oldHead = head;
        head = new Node();
        oldHead.previous = head;
        head.item = item;
        head.next = oldHead;
        head.previous = null;
        if (isEmpty()) {
            tail = head;
        }
        size ++;
    }  
    
    // add the item to the end
    public void addLast(Item item){
        if (item == null) {
            throw new java.lang.NullPointerException("Can't add null items.");
        }
        Node oldTail = tail;
        tail = new Node();
        oldTail.next = tail;
        tail.item = item;
        tail.next = null;
        tail.previous = oldTail;
        if (isEmpty()) {
            head = tail;
        } 
        size ++;
    }
    
    // remove and return the item from the front  
    public Item removeFirst() {              
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No!"); 
        } 
        Node tmp = head;
        if (size == 1) {
            head = tail = null;
        }
        else {
            head = head.next;
            head.previous = null;
        }
        size --;
        return tmp.item;
    }
    
    // remove and return the item from the end
    public Item removeLast() {                 
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No!"); 
        }
        Node tmp = tail;
        if (size == 1) {
            tail = head = null;
        }
        else {
            tail = tail.previous;
            tail.next = null;
        }
        size --;
        System.out.println(tmp.item);
        return tmp.item;
    }
    
    // return an iterator over items in order from front to end  
    @Override
    public Iterator<Item> iterator() { 
        return new DequeIterator(); 
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Can't do that");
        }   
        @Override
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
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
    }
}