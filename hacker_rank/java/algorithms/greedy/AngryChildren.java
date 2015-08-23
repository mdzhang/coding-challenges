import java.io.*;
import java.util.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/angry-children.
 * Specifically, for N packets of candy, each of which has a variable number of candy inside of it,
 * find the minimum possible value of unfairness. Unfairness is the difference between the max
 * and min number of candies from K selected packets of candy to distribute to K children.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class AngryChildren {

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        int totalCandyPacketCount;
        int selectedCandyPacketCount;
        int[] candiesPerPacket;

        int maxValue;
        int minValue;
        int currentUnfairness;
        int minUnfairness;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the total number of packets of candy on the first line,
            // the number of candy packets we must select on the second line,
            // and the number of individual candies in each packet of candy listed from the
            // third line onwards.
            totalCandyPacketCount = Integer.parseInt(bufferedReader.readLine());
            selectedCandyPacketCount = Integer.parseInt(bufferedReader.readLine());

            candiesPerPacket = new int[totalCandyPacketCount];

            // For each individual candy piece count per packet, read it in and add it to an array.
            for (int i = 0; i < totalCandyPacketCount; i++) {
                candiesPerPacket[i] = Integer.parseInt(bufferedReader.readLine());
            }

            // Ultimately, we want to find the minimum unfairness, which is the difference between
            // the max and min individual candy piece count of selectedCandyPacketCount number of
            // packets.
            //
            // We recognize that this is the same as asking "find k packets with a really
            // close max and min" which is equivalent to asking "find k ordered packets with a
            // really close upper and lower bound".
            //
            // Thus, we approach the problem by sorting the array of candy counts, and for each
            // k contiguous entries in the array, subtract the max (which will be at the highest
            // index), from the min (which will be at the lowest index). Then increment the indexes
            // and try again, until we hit the end of the array.
            Arrays.sort(candiesPerPacket);
            minUnfairness = candiesPerPacket[selectedCandyPacketCount - 1] - candiesPerPacket[0];

            for (int i = selectedCandyPacketCount; i < totalCandyPacketCount; i++) {
                maxValue = candiesPerPacket[i];
                minValue = candiesPerPacket[i - selectedCandyPacketCount + 1];

                currentUnfairness = maxValue - minValue;

                if (currentUnfairness < minUnfairness) {
                    minUnfairness = currentUnfairness;
                }
            }

            // Write the resulting minimum unfairness to its own line to STDOUT
            System.out.println(minUnfairness);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}