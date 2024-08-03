/* *****************************************************************************
 *  Name:              Vaibhav Shukla
 *  Coursera User ID:  cf2d493838fbf83017d21569a7082619
 *  Last modified:     July 25, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
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

        if (champion != null) {
            StdOut.println(champion);
        }
    }
}

