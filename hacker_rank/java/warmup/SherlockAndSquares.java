import java.io.*;
import java.math.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/sherlock-and-squares.
 * Specifically, given integers a and b, find the number of perfect squares between a and b,
 * inclusive. 1 ≤ a ≤ b ≤ 10^9.
 *
 * To do so, we note the following:
 *     (a) that there are m = floor( sqrt(n) ) perfect squares between 1 and n
 *     (b) that perfect squares can be found using 1 + the sum from 1 to n of 2x + 1
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class SherlockAndSquares {

    private final static double epsilon = 1E-6;

    private static int getPerfectSquaresInIntervalCount(int lowerBound, int upperBound) {
        int perfectSquaresInIntervalCount = 0;
        int summationUpperBound = isPerfectSquare(lowerBound)
                                  ? (int) Math.sqrt(lowerBound)
                                  : (int) Math.sqrt(lowerBound) + 1;

        int smallestPerfectSquareAboveLowerBound = getPerfectSquareBySummation(summationUpperBound);
        long perfectSquare = smallestPerfectSquareAboveLowerBound;
        // System.out.println("[" + lowerBound + ", " + upperBound + "]\t" + perfectSquare);

        while (perfectSquare <= upperBound) {
            perfectSquaresInIntervalCount++;

            summationUpperBound++;

            perfectSquare += 2 * (summationUpperBound - 1) + 1;
            // System.out.println("[" + lowerBound + ", " + upperBound + "]\t" + perfectSquare);
        }

        return perfectSquaresInIntervalCount;
    }

    /**
     * Determine whether or not a number is a perfect square. Here we decide a number is a perfect
     * square when the different between itself and its rounded value is less than epsilon.
     */
    private static boolean isPerfectSquare(int number) {
        double squareRoot = Math.sqrt(number);
        double roundedSquareRoot = Math.round(squareRoot);

        double differenceFromClosestWholeNumber = Math.abs(squareRoot - roundedSquareRoot);
        return differenceFromClosestWholeNumber < epsilon ? true : false;
    }

    private static int getPerfectSquareBySummation(int upperBound) {
        return upperBound > 1
               ? getPerfectSquareBySummationRecursive(upperBound - 1, 1, 1)
               : (upperBound == 1 ? 1 : 0);
    }

    private static int getPerfectSquareBySummationRecursive(int upperBound, int x, int sumSoFar) {
        sumSoFar += 2 * x + 1;

        return x == upperBound ? sumSoFar : getPerfectSquareBySummationRecursive(upperBound, x + 1, sumSoFar);
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        int intervalCount;
        String[] intervalBounds;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the first line consist of the number of intervals, and each
            // interval's lower and upper bound separated by a space from the second line onwards
            intervalCount = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < intervalCount; i++) {
                intervalBounds = bufferedReader.readLine().split(" ");
                System.out.println(getPerfectSquaresInIntervalCount(
                        Integer.parseInt(intervalBounds[0]), Integer.parseInt(intervalBounds[1])));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}