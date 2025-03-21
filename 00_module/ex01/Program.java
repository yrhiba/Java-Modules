import java.util.Scanner;

public class Program {
    static public void main(String[] args) {

        Scanner input = new Scanner(System.in);
        long number = input.nextLong();

        if (number < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        } else {
            boolean isPrime = true;
            int numberOfChecks = 0;
            for (int i = 2; i*i <= number; i++) {
                numberOfChecks += 1;
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            System.out.println(isPrime + " " + numberOfChecks);
        }

        input.close();
    }
}
