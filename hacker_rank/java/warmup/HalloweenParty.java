import java.io.*;
import java.math.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/halloween-party.
 * Specifically, if one is allowed to cut a chocolate bar K times, but can only cut the bar in
 * 1 x 1 pieces, and can neither relocate nor stack pieces of the chocolate bar, what is the
 * maximum number of 1 x 1 sized pieces that can be cut from the chocolate bar?
 *
 * We solve this by recognizing that this is equivalent to asking what two numbers that sum to K
 * have the largest product. We also recognize that these numbers will be those integer numbers
 * closest to K's midpoint that sum to K.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class HalloweenParty {

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        int listedCutsAllowedCount;
        int cutsAllowed;
        double midPoint;
        long maxPiecesPossible;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the first line consist of the number of cuts to calculate the
            // maximum number of chocolate pieces we can cut out, and the second line onwards
            // be a specific cut count.
            listedCutsAllowedCount = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < listedCutsAllowedCount; i++) {
                cutsAllowed = Integer.parseInt(bufferedReader.readLine());
                midPoint = cutsAllowed / 2.0;

                // for an odd number, we'll want the product of the floor and ceil of the midpoint
                //      e.g. for 5 => 5 / 2 = 2.5 => floor(2.5) * ceil(2.5) = 2 * 3 = 6
                // for an  even number, we'll want the square of the midpoint, which whether floored
                // or ceiled should return the same number
                maxPiecesPossible = (long) (Math.floor(midPoint) * Math.ceil(midPoint));

                System.out.println(maxPiecesPossible);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}