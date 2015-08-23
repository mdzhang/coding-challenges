import java.io.*;
import java.math.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/chocolate-feast.
 * Specifically, a customer has $N dollars to spend on chocolate, each bar of which cost $C.
 * Additionally, a customer can get a free chocolate for every M wrappers they hand in. Given
 * M, N, and C, how many chocolates can the customer buy and eat at most?
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class ChocolateFeast {

    private static int getMaxChocolateBarReceivableCount(int customerDollars,
            int chocolateBarCostDollars, int chocolateBarCostWrappers, int customerWrappers,
            int chocolateBarConsumedCount) {

        // first the customer purchases all the chocolate bars they possibly can
        int purchasableChocolateBarCount = customerDollars / chocolateBarCostDollars;

        // then, using the wrappers of the chocolate bars they just purchased,
        // they trade in for more chocolate bars
        int tradeinChocolateBarCount = (purchasableChocolateBarCount + customerWrappers) / chocolateBarCostWrappers;

        // count how many wrappers we still have after tradein, including the wrappers of the
        // chocolate bars we just received from trade
        int wrappersAfterTradein = tradeinChocolateBarCount +
            ((purchasableChocolateBarCount + customerWrappers) % chocolateBarCostWrappers);

        chocolateBarConsumedCount += purchasableChocolateBarCount + tradeinChocolateBarCount;

        // if we have enough wrappers to keep trading in for chocolate bars, make the recursive
        // call to this function, having updated customerDollars, customerWrappers, and chocolateBarConsumedCount
        // else just return chocolateBarConsumedCount
        return wrappersAfterTradein >= chocolateBarCostWrappers
               ? getMaxChocolateBarReceivableCount(0, chocolateBarCostDollars, chocolateBarCostWrappers,
                                                   wrappersAfterTradein, chocolateBarConsumedCount)
               : chocolateBarConsumedCount;
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;

        int customerCount;
        String[] customerStats;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the first line consist of the number of customers, and the
            // customer's total amount in cash, the cost of a chocolate bar, and the number of
            // wrappers a customer must turn in to get a free chocolate bar.
            customerCount = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < customerCount; i++) {
                customerStats = bufferedReader.readLine().split(" ");
                System.out.println(getMaxChocolateBarReceivableCount(Integer.parseInt(customerStats[0]),
                        Integer.parseInt(customerStats[1]), Integer.parseInt(customerStats[2]), 0, 0));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}