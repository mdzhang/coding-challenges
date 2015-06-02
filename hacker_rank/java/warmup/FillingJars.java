import java.io.*;
import java.math.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/filling-jars.
 * Specifically, given N jars over which k candies will be distributed over jars a to b, m times,
 * what is the average number of candies in each jar after the mth distribution. Note that
 * 1 <= a <= b <= N.
 *
 * Also note that 3 <= N <= 10^7
 *                1 <= M <= 10^5
 *                0 <= k <= 10^6
 *
 * So, while any one of these might fit in an int, e.g. multiplication should be done with longs.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class FillingJars {

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        String[] jarAndDistributionCounts;
        int totalNumberJars;
        int candyDistributionCount;
        long totalCandyCount;

        String[] distributionIndicesAndCandyCount;
        int lowerIndex;
        int upperIndex;
        long candiesToDistribute;
        long range;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the first line consist of two numbers, separated by a space, the
            // former of which is the jar count and the later of which is the distribution count.
            jarAndDistributionCounts = bufferedReader.readLine().split(" ");
            totalNumberJars = Integer.parseInt(jarAndDistributionCounts[0]);
            candyDistributionCount = Integer.parseInt(jarAndDistributionCounts[1]);

            totalCandyCount = 0;

            for (int i = 0; i < candyDistributionCount; i++) {
                distributionIndicesAndCandyCount = bufferedReader.readLine().split(" ");

                lowerIndex = Integer.parseInt(distributionIndicesAndCandyCount[0]);
                upperIndex = Integer.parseInt(distributionIndicesAndCandyCount[1]);

                candiesToDistribute = Long.parseLong(distributionIndicesAndCandyCount[2]);
                range = upperIndex - lowerIndex + 1;

                totalCandyCount += candiesToDistribute * range;
            }

            System.out.println(totalCandyCount / totalNumberJars);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}