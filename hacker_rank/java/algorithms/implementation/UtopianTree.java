import java.io.*;
import java.math.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/utopian-tree.
 * Specifically, given a Utopian Tree which is initially 1 meter tall when planted in the Spring,
 * find the height of the tree after N cycles of growth. The Utopian tree goes through 2 cycles of
 * growth every year - one in the Spring when it doubles in height, and one in the Summer when it
 * grows by one meter.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class UtopianTree {

    /**
     * Calculate out how much a Utopian Tree grows after a given number of cycles. The Utopian tree has
     * 2 cycles of growth every year - one in the Spring when it doubles in height, and one in the
     * Summer when it grows by one meter.
     */
    private static int calculateUtopianTreeHeightAfterNCycles(int numberCycles) {
        int height = 1;
        int cyclesSoFar = 1;

        while (cyclesSoFar <= numberCycles) {
            // since the Utopian Tree was planted in the Spring, during which it doubles in height,
            // if the cycle number is odd, we double the tree's height
            if (cyclesSoFar % 2 == 1) {
                height *= 2;
            }
            // else, we're in an even season (Summer) when the tree only grows a single meter
            else {
                height += 1;
            }
            cyclesSoFar++;
        }

        return height;
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        int listedNumberCount;
        int numberCycles;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the number of cycles to be listed on the first line,
            // and the number cycles we should find the height for listed on the second line onwards.
            // Here, we go ahead and pull out the former
            listedNumberCount = Integer.parseInt(bufferedReader.readLine());

            // For each number we should expect to see listed, read it in, parse it, and
            // pass it off to calculateUtopianTreeHeightAfterNCycles().
            // Write the resulting height to its own line to STDOUT
            for (int i = 0; i < listedNumberCount; i++) {
                numberCycles = Integer.parseInt(bufferedReader.readLine());
                System.out.println(calculateUtopianTreeHeightAfterNCycles(numberCycles));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}