import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/find-digits.
 * Specifically, for a given integer, this program counts how many of the digits in the integer
 * divide the integer itself. If the same digit occurs twice, it is counted twice.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class FindDigits {

    /**
     * Get the number of digits in the given integer that divide into the integer.
     * Includes duplicates.
     *
     * @param  givenInteger
     * @return  dividingDigitsOfIntegerCount
     */
    public static int getDividingDigitsOfIntegerCount(int givenInteger)
    {
        int digit;
        int dividingDigitsOfIntegerCount = 0;

        // integer is a copy of the given integer that, every time we observe its last digit,
        // we will divide by 10 to switch its former second-to-last digit to its new last digit
        // (see more in docs below)
        int integer = givenInteger;

        do {
            // pull out the last digit of the integer
            digit = integer % 10;

            // only a non-zero digit that mods with the given integer to 0 will divide into it
            if ((digit != 0) && (givenInteger % digit == 0)) {
                dividingDigitsOfIntegerCount += 1;
            }

            // in the next iteration, we don't want to pull out the same last digit, so here
            // we divide by 10 (knowing that this will be floored by the integer conversion),
            // so that the last digit of the resulting integer is the second-to-last digit
            // of the current value of integer
            integer /= 10;
        }
        // integer will be floored to 0 once we divide a single digit number by 10;
        // aka once we've run out of unchecked digits
        while (integer > 0);

        return dividingDigitsOfIntegerCount;
    }

    /**
     * Read in each integer from STDIN, count the number of digits of a given integer that divide
     * into that integer, and write the resulting count to its own line to STDOUT.
     */
    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        int listedIntegerCount;
        int givenInteger;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // input format has the number of integers to be listed on the first line,
            // and the integers whose digits we should count listed on the second line onwards
            // here, we go ahead and pull out the former
            listedIntegerCount = Integer.parseInt(bufferedReader.readLine());

            // for each integer we should expect to see listed, read it in, parse it, and
            // pass it off to getDividingDigitsOfIntegerCount()
            // write the resulting count to its own line to STDOUT
            for (int i = 0; i < listedIntegerCount; i++) {
                givenInteger = Integer.parseInt(bufferedReader.readLine());
                System.out.println(getDividingDigitsOfIntegerCount(givenInteger));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}