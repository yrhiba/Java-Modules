import java.util.Scanner;

public class Program {

	static final int limit = 10;
	static final String[] weekDaysKey = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

	static Scanner input;

	static String[] students;
	static int studentsSize;

	static boolean[][] classes;
	static int classSize;

	static int[][][] attendanceTable;

	static int getStudentIndex(String studentName) {
		for (int i = 0; i < studentsSize; i++) {
			if (students[i].equals(studentName)) {
				return i;
			}
		}
		return (-1);
	}

	static void err(String msg) {
		System.err.println(msg);
		System.exit(-1);
	}

	static void readStudents()
	{
		String name;
		int idx;

		idx = 0;
		while (true) {
			name = input.nextLine();
			if (name.equals(".")) {
				break;
			}
			if (idx >= limit) {
				System.err.println("err: students length hit the maximum allowed.");
				System.exit(-1);
			}
			if ((name.length() > limit) || name.contains(" ")) {
				System.err.println("err: student name not allowed.");
				System.exit(-1);
			}
			students[idx] = name;
			idx += 1;
		}

		studentsSize = idx;
	}

	static void readClasses() {

		String line;

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				classes[i][j] = false;
			}
		}

		classSize = 0;

		while (true) {
			line = input.nextLine();
			if (line.equals(".")) {
				break;
			}
			if (classSize > limit) {
				err("err: classes length hit the maximum allowed.");
			}
			String[] t = line.split(" ");
			if (t.length != 2) {
				err("input error");
			}
			int hour = 0;
			for (int i = 0; i < t[0].length(); i++) {
				hour = hour * 10 + (t[0].charAt(i) - '0');
			}
			if (hour < 1 || hour > 6) {
				err("hour not allowed");
			}
			boolean exist = false;
			for (int i = 0; i < weekDaysKey.length; i++) {
				if (weekDaysKey[i].equals(t[1])) {
					exist = true;
					classes[i][hour] = true;
					break;
				}
			}
			if (exist == false) {
				err("incorrect input");
			}
			classSize += 1;
		}
	}

	static void readAttendanceRecoding() {

		String line;

		for (int i = 0; i < 31; i++) {
			for (int j = 0; j <= 6; j++) {
				for (int k = 0; k < 10; k++) {
					attendanceTable[i][j][k] = 0;
				}
			}
		}

		while (true) {

			line = input.nextLine();

			if (line.equals(".")) {
				break;
			}

			String[] t = line.split(" ");
			
			if (t.length != 4) {
				err("err: incorrect input");
			}

			String studentdName = t[0];
			int studentIdx = getStudentIndex(studentdName);
			int classDay = Integer.parseInt(t[2]);
			int classHour = Integer.parseInt(t[1]);
			String attend = t[3];

			if (studentIdx < 0 || classDay < 0 || classDay > 30
				|| classHour < 1 || classHour > 6) {
				err("err incorrect input");
			}

			if (attend.equals("HERE")) {
				attendanceTable[classDay][classHour][studentIdx] = 1;
			} else if (attend.equalsIgnoreCase("NOT_HERE")) {
				attendanceTable[classDay][classHour][studentIdx] = -1;
			} else {
				err("err: incorrect input");
			}
		}
	}
	
	static void renderTable() {

		System.out.printf("%12s", "");

		boolean thereIsColBefore = false;
		for (int day = 1, weekDay = 1; day <= 30; day++
		, weekDay = (weekDay + 1) % 7) {
			for (int hour = 1; hour <= 6; hour++) {
				if (!classes[weekDay][hour]) continue;
				if (thereIsColBefore) {
					System.out.printf("|");
				} else {
					thereIsColBefore = true;
				}
				String colContent = hour + ":00 " + weekDaysKey[weekDay] + " " + day;
				System.out.printf("%10s", colContent);
			}
		}
		System.out.printf("|\n");
		
		for (int studentIdx = 0; studentIdx < studentsSize; studentIdx++) {
			System.out.printf("%-12s", students[studentIdx]);
			boolean tmp = false;
			for (int day = 1, weekDay = 1; day <= 30; day++
			, weekDay = (weekDay + 1) % 7) {
				for (int hour = 1; hour <= 6; hour++) {
					if (!classes[weekDay][hour]) continue;
					if (tmp) {
						System.out.printf("|");
					} else {
						tmp = true;
					}
					int value = attendanceTable[day][hour][studentIdx];
					if (value != 0) {
						System.out.printf("%10d", value);
					}
					else {
						System.out.printf("%10s", "");
					}
				}
			}
			System.out.printf("|\n");
		}
	}
	public static void main(String[] args) {

		students = new String[limit];
		classes = new boolean[7][7];
		attendanceTable = new int[31][7][10];
		input = new Scanner(System.in);

		readStudents();
		readClasses();
		readAttendanceRecoding();
		renderTable();

		input.close();
	}
}
