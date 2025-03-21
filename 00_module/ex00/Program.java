public class Program {
    public static void main(String[] args) {
        // the value wich we are going to calculate their digits sum
        int value = 479598;

        // 
        int answer = 0;
        while (value != 0) {
            answer += value % 10;
            value /= 10;
        }

        //
        System.out.println(answer);
    }
}
