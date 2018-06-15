import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        int n = Integer.parseInt(args[0]);
        
        
        String s;
        
        while (!StdIn.isEmpty()) {
            s =  StdIn.readString();
            r.enqueue(s);
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println(r.dequeue());
        }
    }
}
