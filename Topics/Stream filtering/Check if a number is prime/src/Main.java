import java.util.Scanner;
import java.util.stream.*;

class PrimeNumbers {

    /**
     * Checking if a number is prime
     *
     * @param number to test >= 2
     * @return true if number is prime else false
     */
    private static boolean isPrime(long number) {
        long m;
        m = number / 2;
        if (number == 0 || number == 1) {
            return false;
        } else {
            IntStream intStream = IntStream.rangeClosed(2, (int) m);
            return intStream.noneMatch(i -> number % i == 0);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine().trim();

        int n = Integer.parseInt(line);

        System.out.println(isPrime(n) ? "True" : "False");
    }
}