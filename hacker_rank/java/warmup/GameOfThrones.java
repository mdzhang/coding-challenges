import java.io.*;
import java.util.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/game-of-thrones.
 * Specifically, given a string, find out whether or not an anagram can be made of it.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class GameOfThrones {

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        char[] inputChars;
        int oddNumberDuplicatesCharCount = 0;
        int duplicateCharCount;
        char duplicateChar;
        char currentChar;
        String output;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format consists of a single string, for which we must determine whether or not
            // a palindrome can be made from an anagram of the string
            inputChars = bufferedReader.readLine().toCharArray();

            Arrays.sort(inputChars);

            // To create a palindrome from the available characters, we need an even number of each
            // available character so they can be placed at mirror positions in the string. The
            // obvious exception to this would be that for odd length strings, we can have an odd
            // number of at least one character to place in the middle.
            //
            // So, we know that we cannot build a palindrome from this string if either
            //      (a) the string is of even length but has an odd number of at least one char
            //      (b) the string is of odd length but has an odd number of at least two chars
            //
            // So we iterate over a sorted array of the inputChars, counting how many of a given
            // char we have. Each time we have an odd number of a char, we increment
            // oddNumberDuplicatesCharCount.

            duplicateChar = inputChars[0];
            duplicateCharCount = 1;

            for (int i = 1; i < inputChars.length; i++) {
                currentChar = inputChars[i];

                if (currentChar == duplicateChar) {
                    duplicateCharCount++;
                } else {
                    if (duplicateCharCount % 2 == 1) {
                        oddNumberDuplicatesCharCount++;
                    }

                    if ( ((inputChars.length % 2 == 1) && (oddNumberDuplicatesCharCount > 1)) ||
                         ((inputChars.length % 2 == 0) && (oddNumberDuplicatesCharCount > 0)) ) {
                        break;
                    }

                    duplicateChar = currentChar;
                    duplicateCharCount = 1;
                }
            }

            output = ((inputChars.length % 2 == 1) && (oddNumberDuplicatesCharCount > 1)) ||
                     ((inputChars.length % 2 == 0) && (oddNumberDuplicatesCharCount > 0))
                     ? "NO"
                     : "YES";

            System.out.println(output);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}