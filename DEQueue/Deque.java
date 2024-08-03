import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private Node first, last;
    private int N;

    private class Node{
        Item item;
        Node next;
        Node prev;
    }
    // construct an empty deque
    public Deque(){
        N = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the deque
    public int size(){
        return N;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if(isEmpty()) last = first;
        N++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        N--;
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException();
        N--;
        Item item = last.item;
        last = last.prev;
        if(isEmpty()) first = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public  Iterator<Item> iterator(){
        return new DequeIterator();

    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        int holder1;
        int holder2;


        deque.addFirst(99);
        deque.addFirst(45);
        deque.addLast(23);
        deque.addLast(765);

//      [45,99,23,765]
        holder1 = deque.removeLast();
        holder2 = deque.removeFirst();

//        [99,23]

        System.out.println("Last delete node:- " + holder1);
        System.out.println("First delete node:- " + holder2);


        deque.addFirst(21);
        deque.addFirst(2);
        deque.addLast(4);
        deque.addLast(4235);

//      [2,21,99,23,4,4253]

        System.out.println("There are the Elements of the deque");
        System.out.println("Size of the deque:- " + deque.size());
        for(Integer x: deque){
            System.out.print(x + " | ");
        }


    }

}
