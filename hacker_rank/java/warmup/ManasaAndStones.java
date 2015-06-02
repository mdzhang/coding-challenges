import java.io.*;
import java.util.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/manasa-and-stones.
 * Specifically, a hiker is following a trail of N stones, each of which has a number on it.
 * The first stone has the number 0, and every two consecutive stones have a difference of either
 * a or b. Determine all the possibilities for the number value on the last stone.
 *
 * Note: 1 <= n, a, b <= 10^3, and the number on the stones are non-decreasing
 *
 * Suppose we have x occurrences of a and y occurrences of b over the 2nd to Nth stone. This is
 * equivalent to asking how many ways can we sum two numbers (not necessarily unique) a and b
 * s.t that each occurs no less than 0 and no more than n - 1 times.
 *
 * Imagine that |'s are values and ___'s are the differences between them.
 *
 *          [|___|___|___|]
 *
 * Since every slot must be full, there are only 2 values possible per slot, and order doesn't
 * matter, we can simplify this problem to how many ways we can position a bar (#) that has all a's
 * to its left and all b's to its right.
 *
 * For four stones we have these possibilities:
 *         [# b | b | b |]
 *         [| a # b | b |]
 *         [| a | b # b |]
 *         [| a | a | a #]
 *
 * Thus we can see that there are N possible values for the final stone, which we can find by calling
 * the index of the # separatingIndex, and multiplying, for each value of separatingIndex,
 *         a * separatingIndex + b * (N - 1 - separatingIndex)
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class ManasaAndStones {

    public static int[] removeDuplicates(int[] sortedArray) {

        int uniqueValueCount = 0;
        int currentIndex = 1;

        while (currentIndex < sortedArray.length) {

            if (sortedArray[currentIndex] != sortedArray[uniqueValueCount]) {
                uniqueValueCount++;
                sortedArray[uniqueValueCount] = sortedArray[currentIndex];
            }

            currentIndex++;
        }

        return Arrays.copyOf(sortedArray, uniqueValueCount + 1);
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        int trailCount;
        int a;
        int b;
        int totalStoneCount;
        int[] possibleValues;
        String outputLine;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            trailCount = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < trailCount; i++) {
                totalStoneCount = Integer.parseInt(bufferedReader.readLine());
                a = Integer.parseInt(bufferedReader.readLine());
                b = Integer.parseInt(bufferedReader.readLine());

                possibleValues = new int[totalStoneCount];
                outputLine = "";

                for (int separatingIndex = 0; separatingIndex < totalStoneCount; separatingIndex++) {
                    possibleValues[separatingIndex] = b * separatingIndex + a * (totalStoneCount - 1 - separatingIndex);
                }

                Arrays.sort(possibleValues);

                // there's always a possibility that the sum of the products placed in possibleValues contain duplicates
                // so we remove them here
                possibleValues = removeDuplicates(possibleValues);

                for (int value: possibleValues) {
                    outputLine += value + " ";
                }

                System.out.println(outputLine.trim());
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}