import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;
    private class Node {
        Item item;
        Node next;
    }
    public RandomizedQueue() {
        first = null;
        last = null;
        n = 0;
    }                 // construct an empty randomized queue
    
    public boolean isEmpty() {
        return n == 0;
    }                 // is the randomized queue empty?
    public int size() {
        return n;
    }                        // return the number of items on the randomized queue
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument must no be null!");
        }
        
        Node x = new Node();
        x.item = item;
        x.next = null;
        if (last == null) {
            first = x;
            last = x;
        }
        else {
            last.next = x;
            last = last.next;
        }
        n += 1;
    }           // add the item
    
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException("RandomizedQueue is empty!");
        }
        // Random rand = new Random();
        final int r = StdRandom.uniform(0, n);
        Node old;
        
        if (r == 0) {
            old = first;
            first = first.next;
            old.next = null;
            if (first == null) {
                last = null;
            }
        }
        else {
            Node prev = first;
            Node current = first.next;
            int currentIndex = 1;
            while (currentIndex != r) {
                prev = current;
                current = current.next;
                currentIndex += 1;
            }
            old = current;
            prev.next = current.next;
            if (old == last) {
                last = prev;
            }
            old.next = null;
        }
        n -= 1;
        return old.item;
    }                    // remove and return a random item
    public Item sample() {
        // Random rand = new Random();
        if(n == 0) {
            throw new NoSuchElementException("No more elements to return!"); 
        }
        final int r = StdRandom.uniform(0, n);
        return this.getItemAtIndex(r);
    }                    // return a random item (but do not remove it)
    
    private Item getItemAtIndex(int r) {
        Node current = first;
        int i = 0;
        while (i != r) {
            current = current.next;
            i += 1;
        }
        return current.item;
    }
    
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }        // return an independent iterator over items in random order
    
    
    private class RandomIterator implements Iterator<Item> {
        private boolean[] visited;
        private int has;
        
        public RandomIterator() {
            visited = new boolean[n];
            has = n;
            for (int i = 0; i < n; i++) {
                visited[i] = false;
            }
        }
        
        
        public boolean hasNext() {
            return has > 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Remove operation not supported!");
        }
        public Item next() {
            if (has == 0) {
                throw new NoSuchElementException("No more elements to return!");
            }
            
            int r = StdRandom.uniform(0, n);
            
            while (visited[r]) {
                r = StdRandom.uniform(0, n);
            }
            
            visited[r] = true;
            has -= 1;
            return getItemAtIndex(r);
        }
    }
    
    private void printRandom() {
        Iterator<Item> i = this.iterator();
        while (i.hasNext()) {
            System.out.println("Element : " + i.next());
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        System.out.println("IsEmpty : " + q.isEmpty());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println("IsEmpty : " + q.isEmpty());
        q.printRandom();
        System.out.println("sample : " + q.sample());
        System.out.println("dequeue : " + q.dequeue());
        q.printRandom();
        System.out.println("dequeue : " + q.dequeue());
        System.out.println("dequeue : " + q.dequeue());
        q.printRandom();
        System.out.println("IsEmpty : " + q.isEmpty());
    }  // unit testing (optional)
}
