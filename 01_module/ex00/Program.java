public class Program {
	static public void main(String[] args) {
		User user1 = new User(0, "user1", 0);
		System.out.println("Identifier: " + user1.getIdentifier() 
		+ ", name: " + user1.getName() + ", balance: " + user1.getBalance());
		Transaction trans1 = new Transaction(0, 0,
			Transaction.TransferCategory.INCOME, 0);
		System.out.println(trans1.getTransactionSummary());
	}
}
