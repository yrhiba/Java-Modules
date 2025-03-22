import java.util.Scanner;

public class Program {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		String s = input.nextLine();

		char[] sCharArray = s.toCharArray();

		int[] charCount = new int[65536];

		for (int i = 0; i < 65536; i++) {
			charCount[i] = -1;
		}

		for (char c : sCharArray) {
			if (charCount[c] == -1) {
				charCount[c] = 0;
			}
			charCount[c] += 1;
		}

		char[] mostCharFrequent = new char[10];
		int[] mostCharFrequentCount = new int[10];
		int size = 0;

		for (int i = 0; i < 10; i++) {
			int maxCode = -1;
			for (int c = 0; c < 65536; c++) {
				if (charCount[c] == -1) continue;
				if ((maxCode == -1)
					|| (charCount[c] > charCount[maxCode]))
				{
					maxCode = c;
				}
			}

			if (maxCode == -1) {
				break;
			}

			mostCharFrequent[i] = (char)maxCode;
			mostCharFrequentCount[i] = charCount[maxCode];
			size += 1;

			charCount[maxCode] = -1;
		}

		String[][] grid = new String[12][10];

		for (int i = 0; i < size; i++) {
			grid[11][i] = "" + mostCharFrequent[i];
			int tallSize = (mostCharFrequentCount[i] * 10) / mostCharFrequentCount[0];
			int j = 0, k = 10;
			while (j < tallSize && j < 10)
			{
				grid[k][i] = "#";
				k -= 1;
				j += 1;
			}
			grid[k][i] = Integer.toString(mostCharFrequentCount[i]);
		}

		for (int i = 0; i < 12; i++) {
			boolean renderNewLine = false;
			for (int j = 0; j < 10; j++) {
				if (grid[i][j] == null || grid[i][j].isEmpty()) {
					break;
				}
				if (j > 0) {
					System.out.printf(" ");
				}
				System.out.printf("%3s", grid[i][j]);
				renderNewLine = true;
			}
			if (renderNewLine) {
				System.out.printf("\n");
			}
		}

		input.close();
	}
}
