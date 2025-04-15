public class Program {

	static String getUserInfo(User user) {
		return "name: " + user.getName() + ", balance: " + user.getBalance();
	}

	static public void main(String[] args) {

		UsersArrayList users = new UsersArrayList();

		users.addUser(new User("rich king", 9999));
		users.addUser(new User("poor man", 0));

		System.out.println("Users size: " + users.getUsersSize() + ", List-of-Users: ");
		for (int i = 0; i < users.getUsersSize(); i++) {
			System.out.println("user getted by index -> " + getUserInfo(users.getUserByIndex(i)));
		}

		// example getting user by index
		User u1 = users.getUserByIndex(0);

		// example getting transaction list of a user
		TransactionsList list = u1.getTransactionsList();

		// example of adding transactions
		list.addTransaction(new Transaction(1, 0, Transaction.TransferCategory.INCOME, 100));
	}
}
