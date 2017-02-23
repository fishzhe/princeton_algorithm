package queues;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by zheyu on 2/18/17.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k && !rq.isEmpty(); ++i) {
            System.out.println(rq.dequeue());
        }
    }
}
