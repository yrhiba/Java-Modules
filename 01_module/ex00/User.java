public class User
{
	private int identifier;
	private String name;
	private int balance;

	public User(int identifier, String name, int balance)
	{
		this.identifier = identifier;
		this.name = name;
		this.balance = balance;
	}

	public int getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}
}
