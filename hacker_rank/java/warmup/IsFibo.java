import java.io.*;
import java.math.*;

/**
 * Solution to the problem presented at https://www.hackerrank.com/challenges/is-fibo.
 * Specifically, for a list of given integers, this program determines whether or not
 * that number is an element of the Fibonacci Sequence.
 *
 * @version  1.0
 * @author  Michelle D. Zhang
 */
public class IsFibo {
    private final static double epsilon = 1E-6;
    private static final BigDecimal SQRT_DIG = new BigDecimal(100);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

    /**
     * Private utility method used to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    /**
     * Uses Newton Raphson to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    public static BigDecimal bigSqrt(BigDecimal c){
        return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
    }

    /**
     * Determine whether or not a number is a perfect square. Here we decide a number is a perfect
     * square when the different between itself and its rounded value is less than epsilon.
     */
    private static boolean isPerfectSquare(BigDecimal number) {
        BigDecimal squareRoot = bigSqrt(number);
        BigDecimal roundedSquareRoot = squareRoot.setScale(0, BigDecimal.ROUND_HALF_UP);

        BigDecimal differenceFromClosestWholeNumber = squareRoot.subtract(roundedSquareRoot).abs();
        return differenceFromClosestWholeNumber.compareTo(new BigDecimal(epsilon)) == -1
            ? true : false;
    }

    /**
     * Determine whether or not a number is Fibonacci. Note that a number x is Fibonacci
     * if and only if one or both of (5*x^2 + 4) or (5*x^2 – 4) is a perfect square.
     */
    private static boolean isFibonacci(BigInteger number) {
        BigDecimal bigNumber = new BigDecimal(number);
        BigDecimal highestDegreeTerm = (new BigDecimal(5)).multiply(bigNumber.pow(2));

        return (isPerfectSquare(highestDegreeTerm.add(new BigDecimal(4)) ) ||
               isPerfectSquare(highestDegreeTerm.subtract(new BigDecimal(4))) )
           ? true : false;
    }

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        int listedNumberCount;
        BigInteger number;
        String output;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            // Input format has the number of integers to be listed on the first line,
            // and the integers whose digits we should count listed on the second line onwards.
            // Here, we go ahead and pull out the former
            listedNumberCount = Integer.parseInt(bufferedReader.readLine());

            // For each integer we should expect to see listed, read it in, parse it, and
            // pass it off to isFibonacci().
            // Depending on the result, write "IsFibo" or "IsNotFibo" to STDOUT
            for (int i = 0; i < listedNumberCount; i++) {
                number = new BigInteger(bufferedReader.readLine());
                output = isFibonacci(number) ? "IsFibo" : "IsNotFibo";
                System.out.println(output);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
}