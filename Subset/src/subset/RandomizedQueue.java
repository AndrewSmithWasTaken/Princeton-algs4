package subset;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
/**
 *
 * @author andrew
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rq;     // queue elements
    private int size;      // number of elements in queue
    private int head;      // index of first element of queue
    private int tail;      // index of next available slot
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        rq = (Item[]) new Object[2];
        size = 0;
        head = 0;
        tail = 0;
    }               
   
    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }
   
    // return the number of items in the deque 
    public int size() {
        return size;
    }  
    
    // resize the underlying array
    private void resize(int max) {
        assert max >= size;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = rq[(head + i) % rq.length];
        }
        rq = temp;
        head = 0;
        tail  = size;
    }
   
    // add the item        
    public void enqueue(Item item) {
        if (size == rq.length) {
            resize(2*rq.length);
        }  
        rq[tail++] = item;                        
        if (tail == rq.length) {
            tail = 0;
        }          
        size++;
    }
   
    // remove and return a random item       
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        int pos = StdRandom.uniform(size);
        Item item = rq[pos];
        rq[pos] = rq[tail-1];
        rq[tail-1] = null;
        tail = tail-1;
        size--;
        if (size > 0 && size == rq.length/4) {
            resize(rq.length/2);
        } 
        return item;
    }

    // return (but do not remove) a random item        
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return rq[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order        
    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class RandomIterator implements Iterator<Item> {
        private int i = 0;
        @Override
        public boolean hasNext() { 
            return i < size;                               
        }
        @Override
        public void remove() { 
            throw new UnsupportedOperationException();  
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int pos = StdRandom.uniform(size);
            Item item = rq[pos];
            System.out.println(item);
            return item;
        }
    }
}
