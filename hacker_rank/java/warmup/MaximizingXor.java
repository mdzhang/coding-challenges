import java.io.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/maximizing-xor.
 * Specifically, given L and R where L <= A <= B <= R, find the combination of A and B that, when
 * XOR'd, yields the maximum value.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class MaximizingXor {

    /**
     * Given both lower and upper integer value bounds, find the combination of integers a and b,
     * where a and b are both contained in [lowerBound, upperBound], such that a and b maximize
     * the value of a XOR b.
     */
    public static int calculateMaxXor(int lowerBound, int upperBound) {
        int maxXor = Integer.MIN_VALUE;
        int currentXor;

        for (int a = lowerBound; a <= upperBound; a++) {
            for (int b = a; b <= upperBound; b++) {
                currentXor = a ^ b;

                if (currentXor > maxXor) {
                    maxXor = currentXor;
                }
            }
        }

        return maxXor;
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        int lowerBound;
        int upperBound;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            lowerBound = Integer.parseInt(bufferedReader.readLine());
            upperBound = Integer.parseInt(bufferedReader.readLine());

            System.out.println(calculateMaxXor(lowerBound, upperBound));
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}