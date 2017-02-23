package queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zheyu on 10/14/16.
 *
 * Description:
 *  RandomizedQueue following instructions.
 *  Score: 99/100; Lost one point because randomNode() is not constant time.
 *  Could use array to implement it, so that randomNode() will use constant time.
 *  But the complicated part of using array is default array size. And also don't
 *  forget to deal with inserting more elements than array size.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Node first;

    public RandomizedQueue() {
        size = 0;
        first = null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        size++;
    }

    public Item dequeue() {
        Node node = randomNode();
        Item item = node.item;
        Node prev = node.prev;
        Node next = node.next;
        if (prev == null) {
            first = next;
        }

        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        size--;
        return item;
    }
    public Item sample() {
        return randomNode().item;
    }

    private Node randomNode() {
        removeEmptyDeque();
        int pos = StdRandom.uniform(size);
        Node node = first;
        while (pos > 0) {
            node = node.next;
            pos--;
        }
        return node;
    }

    private void removeEmptyDeque() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(first, size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] array;
        private int iSize;

        public RandomizedQueueIterator(Node node, int size) {
            array = (Item[]) new Object[size];
            iSize = 0;
            while (node != null) {
                array[iSize] = node.item;
                iSize++;
                node = node.next;
            }
        }

        @Override
        public boolean hasNext() {
            return iSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int pos = StdRandom.uniform(iSize);

            Item item = array[pos];
            array[pos] = null;
            if (pos != iSize - 1) {
                array[pos] = array[iSize - 1];
                array[iSize - 1] = null;
            }
            iSize--;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    private class Node {
        private Item item;
        private Node next;
        private Node prev;
        public Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }
    public static void main(String[] args) {
        RandomizedQueue<Integer> dq = new RandomizedQueue<>();
        dq.enqueue(1);
        dq.enqueue(2);
        dq.enqueue(3);
        dq.enqueue(4);
        dq.enqueue(5);

        System.out.println("-----------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(dq.sample());
        }

        System.out.println("-----------------------------");
        while (!dq.isEmpty()) {
            System.out.println(dq.dequeue());
        }

        Iterator<Integer> iterator = dq.iterator();
        Iterator<Integer> iterator2 = dq.iterator();

        System.out.println("-----------------------------");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("-----------------------------");
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }


    }

}
