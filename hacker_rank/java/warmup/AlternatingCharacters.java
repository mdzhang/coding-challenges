import java.io.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/alternating-characters.
 * Specifically, for any given string comprised of 2 unique characters, what is the minimum number
 * of character deletions necessary to convert the string to one that has those two characters
 * alternating every other character. The resulting string may be of any length.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class AlternatingCharacters {

    public static int findMinimumNumberOfDeletionsForAlternatingCharacterString(String string) {
        int minimumDeletionCount = 0;

        char previousChar;
        char currentChar;

        if (string.length() > 1) {
            previousChar = string.charAt(0);

            for (int i = 1; i < string.length(); i++) {
                currentChar = string.charAt(i);

                if (currentChar == previousChar) {
                    minimumDeletionCount += 1;
                } else {
                    previousChar = currentChar;
                }
            }
        }

        return minimumDeletionCount;
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        int stringCount;
        String line;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the number of strings to be listed on the first line,
            // and the strings to count the minimum number of deletions for from the second line
            // onwards. Here, we go ahead and pull out the former
            stringCount = Integer.parseInt(bufferedReader.readLine());

            // For each string we should expect to see listed, read it in and
            // pass it off to findMinimumNumberOfDeletionsForAlternatingCharacterString().
            // Write the resulting height to its own line to STDOUT
            for (int i = 0; i < stringCount; i++) {
                line = bufferedReader.readLine();
                System.out.println(findMinimumNumberOfDeletionsForAlternatingCharacterString(line));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}