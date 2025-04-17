import java.util.Scanner;
import java.util.UUID;

public class Menu {

	private Scanner input;
	private TransactionsService service;
	private boolean devMode;
	private boolean finished;

	public Menu() {
		input = new Scanner(System.in);
		service = new TransactionsService();
		devMode = false;
		finished = false;
	}

	private boolean isFinished() {
		return this.finished;
	}

	public void show() {
		System.out.println("1. Add a user");
		System.out.println("2. view user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		System.out.println("5. DEV - remove a transfer by ID");
		System.out.println("6. DEV - check transfer validity");
		System.out.println("7. Finish execution");
	}

	private void addUser() {
		System.out.println("Enter a user name and a balance:");
		String name;
		int balance;
		name = this.input.next();
		balance = this.input.nextInt();
		try {
			User newUser = new User(name, balance);
			service.addUser(newUser);
			System.out.println("User with id = " + newUser.getIdentifier() + " is added");
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void viewUserBalance() {
		System.out.println("Enter user id: ");
		int userId = input.nextInt();
		try {
			int balance = service.getUserBalance(userId);
			System.out.println("user with id = " + userId + ", have a balance = " + balance + ".");
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	private void performTransfer() {
		System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
		int senderId, recipientId, amount;
		senderId = input.nextInt();
		recipientId = input.nextInt();
		amount = input.nextInt();
		try {
			service.transfer(senderId, recipientId, amount);
			System.out.println("The transfer is completed");
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void viewUserTransactions() {
		int userId = input.nextInt();
		try {
			Transaction[] list = service.geTransactionsList(userId);
			for (Transaction transaction : list) {
				System.out.println(transaction);
			}
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void removeTransfer() {
		System.out.println("Enter a user ID and a transfer ID");
		int userId = this.input.nextInt();
		UUID transferId = UUID.fromString(this.input.next());
		try {
			service.removeTransaction(transferId, userId);
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void checkTransferValidity() {

		Transaction[] list = service.getUnpairedTransactions();

		System.out.println("Check results: ");

		if (list.length == 0) {
			System.out.println("all clean.");
			return ;
		}

		System.out.println("unacknowledged transfers list:");
		for (Transaction transaction : list) {
			System.out.println(transaction);
		}
	}

	public void getAndApplyOption() {
		int option = this.input.nextInt();
		if (option < 1 || option > 7) {
			System.out.println("invalid option. (1 -> 7 allowed)");
			return ;
		}
		if (!this.devMode && (option == 5 || option == 6)) {
			System.out.println("not allowed option. only for dev profiles.");
			return ;
		}
		switch (option) {
			case 1:
				this.addUser();
				break;
			case 2:
				this.viewUserBalance();
				break;
			case 3:
				this.performTransfer();
				break;
			case 4:
				this.viewUserTransactions();
				break;
			case 5:
				this.removeTransfer();
				break;
			case 6:
				this.checkTransferValidity();
				break;
			case 7:
				this.finished = true;
				break;
			default:
				break;
		}
	}

	public void activateDevMode() {
		this.devMode = true;
	}

	public void start() {
		while (!this.isFinished()) {
			System.out.println("Please select an option");
			this.show();
			System.out.println("-".repeat(15));
			this.getAndApplyOption();
			System.out.println("-".repeat(15));
		}
	}
}
