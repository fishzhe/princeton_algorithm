package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zheyu on 10/13/16.
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        itemNull(item);
        Node node = new Node(item);
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        if (isEmpty()) {
            last = first;
        }
        size++;
    }

    public void addLast(Item item) {
        itemNull(item);
        Node node = new Node(item);
        if (last != null) {
            last.next = node;
            node.prev = last;
        }
        if (isEmpty()) {
            first = last;
        }
        last = node;
        size++;
    }

    public Item removeFirst() {
        return remove(true);
    }

    public Item removeLast() {
        return remove(false);
    }

    private Item remove(boolean isFirst) {
        checkEmptyDeque();
        Item item = last.item;
        if (first != last) {
            if (!isFirst) {
                last = last.prev;
                if (last != null) {
                    last.next = null;
                }
            } else {
                first = first.next;
                if (first != null) {
                    first.prev = null;
                }
            }
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator(Node node){
            this.current = node;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void itemNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void checkEmptyDeque() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    class Node {
        Item item;
        Node next;
        Node prev;
        public Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        Iterator<Integer> iterator = deque.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        deque.removeFirst();
        deque.removeLast();
        System.out.println();

        Iterator<Integer> iterator2 = deque.iterator();
        while(iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

        System.out.println();
        deque.addLast(3);
        deque.addLast(4);

        Iterator<Integer> iterator3 = deque.iterator();
        while(iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }
    }
}
