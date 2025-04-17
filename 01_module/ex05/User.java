public class User
{
	private final int id;
	private String name;
	private int balance;
	private final TransactionsLinkedList transactionsList;

	public User(String name, int balance)
	{
		if (name == null) {
			throw new RuntimeException("Name cannot be null");
		}
		this.id = UserIdsGenerator.getInstance().generateId();
		this.transactionsList = new TransactionsLinkedList();
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

	public TransactionsList getTransactionsList() {
		return this.transactionsList;
	}

	public void addTransaction(Transaction transaction) {
		this.transactionsList.addTransaction(transaction);
		this.balance += transaction.getTransferAmount();
		if (this.balance < 0) {
			throw new IllegalTransactionException("IllegalTransactionException");
		}
	}

	@Override
	public String toString() {
		return "User[id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}
}
