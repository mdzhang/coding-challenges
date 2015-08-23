import java.io.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/the-love-letter-mystery.
 * Specifically, for any given string, what is the minimum number of reductions necessary
 * to convert the string to a palindrome. A reduction is the conversion of a letter in the alphabet
 * to another letter in the alphabet that precedes the one to be reduced. We can reduce only one
 * letter at a time.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class LoveLetterMystery {

    public static int findMinimumNumberReductionsToConvertToPalindrome(String string) {
        int minimumReductionCount = 0;

        // Since the goal is to have a string that is a palindrome, we only need to iterate over
        // half its length. We determine what character we should start from by (roughly) finding
        // the midpoint of the string and then adding one.
        //
        // So, for example, given a string of even length 'abbc' we start from index 2, and
        // given a string of odd length 'abbab', we won't change the middle character, so we start
        // from index 3
        int startingCharIndex = (string.length() % 2 == 0)
                                ? string.length() / 2
                                : string.length() / 2 + 1;

        int distanceFromClosestEndOfString;     // this corresponds to the index of oppositeChar
        char currentChar;                       // index of a character on the string's right side
        char oppositeChar;                      // char opposite currentChar on string's left side

        for (int i = startingCharIndex; i < string.length(); i++) {
            // find out how many chars away from the end of the string currentChar is
            distanceFromClosestEndOfString = string.length() - 1 - i;

            currentChar = string.charAt(i);
            // opposite char is as many chars away from the beginning of the string as
            // currentChar is from the end of the string
            oppositeChar = string.charAt(distanceFromClosestEndOfString);

            // to make a palindrome, each char must match the char opposite it in the string
            // we can only reduce characters, so the reduction is equivalent to the absolute
            // value of the difference between the two chars
            minimumReductionCount += Math.abs(currentChar - oppositeChar);
        }

        return minimumReductionCount;
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        int stringCount;
        String line;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the number of strings to be listed on the first line,
            // and the strings to convert to palindromes from the second line onwards.
            // Here, we go ahead and pull out the former
            stringCount = Integer.parseInt(bufferedReader.readLine());

            // For each string we should expect to see listed, read it in and
            // pass it off to findMinimumNumberReductionsToConvertToPalindrome().
            // Write the resulting reduction count to its own line to STDOUT
            for (int i = 0; i < stringCount; i++) {
                line = bufferedReader.readLine();
                System.out.println(findMinimumNumberReductionsToConvertToPalindrome(line));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}