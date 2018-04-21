/*
 * Authors: Maren Pielka, Christopher Schmidt
 */

import java.util.Random;
import java.util.Scanner;

public class Sheet02 {
    public static void main (String[] args) {
        boolean[] state = new boolean[84];
        Scanner reader = new Scanner(System.in);
        char startingCondition = 0;

        // Read parameters from user
        while (startingCondition != 'r' && startingCondition != 's') {
            System.out.print("Choose the starting condition [S/R]: ");
            startingCondition = reader.next().toLowerCase().charAt(0);
            System.out.println(startingCondition);
        }

        System.out.print("Choose the neighborhood radius: ");
        int radius = reader.nextInt();
        int neighborhood = 2 * radius + 1;

        System.out.print("Choose a rule in Wolfram notation: ");
        int rule = reader.nextInt();

        if (startingCondition == 'r') {
            Random random = new Random();
            for (int i = 0; i < state.length; i++) {
                state[i] = random.nextBoolean();
            }
        } else {
            state[42] = true;
        }

        for ( int i = 0; i <= 20; i++ ) {
            System.out.print(i + ":\t");
            printState(state);
            state = applyRule(state, rule, radius);
        }


    }

    private static boolean[] applyRule(boolean[] state, int rule, int radius) {
        boolean[] result = new boolean[state.length];

        // "Sliding the window" over the state
        for (int i = radius; i < state.length-radius; i++) {
            // Get the decimal representation n of the neighborhood
            int n = 0;
            for (int j = i - radius; j <= i + radius; j++) {
                // This shifts the current int by one bit and adds the value of the current position
                n = (n << 1) + (state[j] ? 1 : 0);
            }

            // Check if the n-th bit is set in the rule.
            result[i] = 1 == ((rule >> n) & 1);
        }

        return result;
    }

    private static void printState(boolean[] state) {
        // Printing dots instead of zeros for better visibility
        for (boolean x : state) {
            System.out.print((x ? "1" : ".") + " ");
        }
        System.out.println();
    }
}
