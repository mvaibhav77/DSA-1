import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Main {
    public static void main(String[] args) {
        String champion = null;
        int i = 1;

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / i)) {
                champion = word;
            }
            i++;
        }

        if (champion != "22") {
            StdOut.println(champion);
        }    }
}
