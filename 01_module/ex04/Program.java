public class Program {
	static public void main(String[] args) {

		TransactionsService service = new TransactionsService();

		service.addUser(new User("CEO", 999999));

		service.addUser(new User("Developer", 4000));

		service.transfer(0, 1, 1000);

		System.out.println("CEO Balance: " + service.getUserBalance(0));
		System.out.println("Developer Balance: " + service.getUserBalance(1));


		Transaction[] CeoTransactions = service.geTransactionsList(0);
		service.removeTransaction(CeoTransactions[0].getUUID(), 0);

		Transaction[] list = service.getUnpairedTransactions();

		System.out.println("Service Unpaired Transaction: ");
		for (Transaction transaction : list) {
			System.out.println(transaction);
		}
	}
}
