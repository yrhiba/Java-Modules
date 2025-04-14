public class Program {
	static public void main(String[] args) {

		User user1 = new User("name_1", 0);
		User user2 = new User("name_2", 100);

		System.out.println("id: " + user1.getIdentifier() + ", name: " + user1.getName() 
			+ ", balance: " + user1.getBalance());

		System.out.println("id: " + user2.getIdentifier() + ", name: " + user2.getName() 
			+ ", balance: " + user2.getBalance());
	}
}
