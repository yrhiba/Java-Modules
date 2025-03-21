import java.util.Scanner;

public class Program {

    static int numberDigitsSum(long number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    static boolean isPrime(long number) {
        for (long i = 2; i*i <= number; i++) {
            if (number % i == 0) {
                return (false);
            }
        }
        return (true);
    }

    static public void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int answer = 0;

        while (true) 
        {
            long number = input.nextLong();
            if (number == 42) {
                break;
            }
            if (isPrime(numberDigitsSum(number))) {
                answer += 1;
            }
        }

        System.out.println("Count of coffee-request : " + answer);

        input.close();
    }     
}
