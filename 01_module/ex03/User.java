public class User
{
	private final int id;
	private String name;
	private int balance;

	public User(String name, int balance)
	{
		this.id = UserIdsGenerator.getInstance().generateId();
		this.name = name;
		this.balance = balance;
		if (this.balance < 0) {
			throw new RuntimeException("Balance cannot be negative");
		}
	}

	public int getIdentifier() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}
}
