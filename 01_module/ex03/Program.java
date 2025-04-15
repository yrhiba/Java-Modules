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

		/* 
			TOADD: 
			- test Transactions List
		*/
	}
}
