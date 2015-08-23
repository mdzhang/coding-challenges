import java.io.*;
import java.util.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/sherlock-and-the-beast.
 * Specifically, given a number N, find the largest value "decent" number i.e. the largest integer
 * number that has the following properties:
 *     (a) it has N digits, each of which is either a 3 or a 5
 *     (b) the # of 5s is divisible by 3
 *     (c) the # of 3s is divisible by 5
 *
 * We solve this by recognizing that this is equivalent to asking whether we can divide N into
 * contiguous blocks of length 3 or 5. If we can, we list all length 5 blocks first, followed by
 * length 3 blocks, as this will yield the largest value integer.
 *
 * We determine whether we can divide N into such blocks by first dividing by 3 (the length of five
 * blocks) and keeping track of the remainder, which could have possible values 0 to 2. If this
 * remainder has value 0, we're done. If value 1, we need to free up 3 5-blocks (so that we have 10
 * blocks) to use for 2 3-blocks. If value 2, we need to free up 1 5-block (so that we have 5
 * blocks) to use for 1 5-block.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class SherlockAndTheBeast {

    private static String getLargestDecentNumberWithNDigits(int totalDigitCount) {

        // due to repeat concatenations, we avoid using a String
        StringBuilder output = new StringBuilder();
        int fiveDigitBlockCount;
        int threeDigitBlockCount;
        int unblockedDigits;

        fiveDigitBlockCount = totalDigitCount / 3;
        unblockedDigits = totalDigitCount % 3;

        threeDigitBlockCount = unblockedDigits / 5;
        unblockedDigits = unblockedDigits % 5;

        switch (unblockedDigits) {
            case 1:
                fiveDigitBlockCount -= 3;
                threeDigitBlockCount += 2;
                break;
            case 2:
                fiveDigitBlockCount -= 1;
                threeDigitBlockCount += 1;
                break;
            default:
                break;
        }

        if (fiveDigitBlockCount >= 0 && threeDigitBlockCount >= 0 && (fiveDigitBlockCount + threeDigitBlockCount > 0)) {
            for (int i = 0; i < fiveDigitBlockCount; i++) {
                output.append("555");
            }

            for (int i = 0; i < threeDigitBlockCount; i++) {
                output.append("33333");
            }
        } else {
            output = new StringBuilder("-1");
        }

        return output.toString();
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        int decentNumberCount;
        int digitCount;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            decentNumberCount = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < decentNumberCount; i++) {
                digitCount = Integer.parseInt(bufferedReader.readLine());
                System.out.println(getLargestDecentNumberWithNDigits(digitCount));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}