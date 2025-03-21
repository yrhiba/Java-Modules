import java.util.Scanner;

public class Program {

	private static final int maxEvals = 18;
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int current_week = 1;

		for (int eval = 0; eval < maxEvals; eval += 1)
		{
			String week = input.next();

			if (week.equals("42")) {
				break;
			}

			if (current_week != input.nextInt()) {
				System.err.println("IllegalArgument");
				System.exit(-1);
			}

			int currentMinimumGrade = 10;

			for (int i = 0; i < 5; i++) {
				currentMinimumGrade = Math.min(currentMinimumGrade, input.nextInt());
			}

			System.out.print(week + " " + current_week + " ");
			for (int i = 0; i < currentMinimumGrade; i++) {
				System.out.print("=");
			}
			System.out.println(">");

			current_week += 1;
		}

		input.close();
	}
}
