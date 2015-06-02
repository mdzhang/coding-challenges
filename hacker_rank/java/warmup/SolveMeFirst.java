import java.io.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/solve-me-first.
 * Specifically, it scans 2 integers from STDIN, sums them, and prints the sum to STDOUT (without
 * a newline).
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class SolveMeFirst {

    public static void main (String args[]) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            int a = Integer.parseInt(bufferedReader.readLine());
            int b = Integer.parseInt(bufferedReader.readLine());

            System.out.print(a + b);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}