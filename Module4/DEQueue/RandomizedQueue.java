import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] list;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue(){
        list = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // resizing the array of items
    private void resize(int n){
        Item[] copy = (Item[]) new Object[n];
        if (N >= 0) System.arraycopy(list, 0, copy, 0, N);
        list = copy;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null) throw new IllegalArgumentException();
        if(N == list.length) resize(2 * N);
        list[N++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        int idx = (int) (Math.random() * N);
        Item temp = list[idx];
        list[idx] = list[N-1];
        list[N-1] = temp;
        Item item = list[--N];
        list[N] = null;
        if(N>0 && N == list.length/4) resize(list.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();
        int idx = (int) (Math.random() * N);
        return list[idx];
    }

    // iterator class
    private class RandomizedIterator implements Iterator<Item>{
        private Item[] randomList = (Item[]) new Object[N];
        private int current = 0;

        RandomizedIterator(){
            System.arraycopy(list, 0, randomList, 0, N);
            for(int i=0; i<N; i++){
                int idx = (int) (Math.random() * N);
                Item temp = randomList[idx];
                randomList[idx] = randomList[i];
                randomList[i] = temp;
            }
        }

        public boolean hasNext(){
            return current < N;
        }

        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            return randomList[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedIterator();
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);

        System.out.println("Sample: " + rq.sample());
        System.out.println("Dequeue: " + rq.dequeue());
        System.out.println("Size: " + rq.size());

        for (int i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
