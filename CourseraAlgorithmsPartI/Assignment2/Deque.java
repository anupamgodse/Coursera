import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  
    private Node first;
    private Node last;
    private int n;
  
    
    private class Node {
        Item item;
        Node next;
    }
  
  
  
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }                          // construct an empty deque
    
    public boolean isEmpty() {
        return n == 0;
    }                 // is the deque empty?
    
    public int size() {
        return n;
    }                        // return the number of items on the deque
    
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        if (last == null) {
            last = first;
        }
        n += 1;
    }         // add the item to the front
    
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        Node x = new Node();
        x.item = item;
        x.next = null;
        if (last != null) {
            last.next = x;
            last = x;
        }
        else {
            last = x;
            first = x;
        }
        n += 1;
    }          // add the item to the end
    
    public Item removeFirst() {
        if (n == 0) {
            throw new NoSuchElementException("dequeue is empty");
        }
        Node oldfirst = first;
        first = first.next;
        oldfirst.next = null;
        if (first == null) {
            last = null;
        }
        n -= 1;
        return oldfirst.item;
    }                // remove and return the item from the front
    
    public Item removeLast() {
        if (n == 0) {
            throw new NoSuchElementException("dequeue is empty");
        }
        Node prev = first;
        Node temp = first.next;
        if (temp != null) {
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            last = prev;
            last.next = null;
        }
        else {
            first = null;
            last = null;
            temp = prev;
        }
        n -= 1;
        return temp.item;
    }                 // remove and return the item from the end
    
    public Iterator<Item> iterator() {
        return new ListIterator();  
    }        // return an iterator over items in order from front to end
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("dequeue is empty"); 
            }
            Node ret = current;
            current = current.next;
            return ret.item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported.");
        }
    }
    
    private void printDeque() {
        Iterator<Item> i = this.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
    
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.println(d.isEmpty());
        d.addLast(5);
        System.out.println(d.isEmpty());
        // d.printDeque();
        d.addFirst(2);
        // d.printDeque();
        d.addLast(6);
        d.addFirst(1);
        d.printDeque();
        // d.printDeque();
        System.out.println("removeLast = " + d.removeLast());
        d.addLast(8);
        System.out.println("removeFirst = " + d.removeFirst());
        d.addFirst(0);
        d.addLast(8);
        System.out.println("removeLast = " + d.removeLast());
        System.out.println("removeFirst = " + d.removeFirst());
        d.addLast(12);
        // System.out.println("removeFirst = " + d.removeLast());
        
        System.out.println("removeLast = " + d.removeLast());
        System.out.println("removeLast = " + d.removeLast());
        System.out.println("removeFirst = " + d.removeFirst());
        System.out.println("removeFirst = " + d.removeFirst());
        System.out.println(d.isEmpty());
        d.printDeque();
        
        
    }   // unit testing (optional)
}
