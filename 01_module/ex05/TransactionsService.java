import java.util.UUID;

public class TransactionsService {

	private UsersList db;

	TransactionsService() {
		db = new UsersArrayList();
	}

	public void addUser(User user) {
		this.db.addUser(user);
	}

	public int getUserBalance(int userId) {
		User userData = db.getUserById(userId);
		return userData.getBalance();
	}

	public void transfer(int senderUserId, int recipentUserId, int amout) {
		/*
			Performing a transfer transaction (user IDs and transfer amount are specified). In
			this case, two transactions of DEBIT/CREDIT types are created and added to
			recipient and sender. IDs of both transactions must be equal
		 */

		UUID id = UUID.randomUUID();

		db.getUserById(senderUserId).addTransaction(
			new Transaction(id,
				recipentUserId, 
				senderUserId, 
				Transaction.TransferCategory.OUTCOME, 
				-1 * amout
			));
		db.getUserById(recipentUserId).addTransaction(
			new Transaction(
				id,
				recipentUserId,
				senderUserId, 
				Transaction.TransferCategory.INCOME, 
				amout
			));
	}

	public Transaction[] geTransactionsList(int userId) {
		return db.getUserById(userId).getTransactionsList().toArray();
	}

	public void removeTransaction(UUID transactionId, int userId) {
		db.getUserById(userId).getTransactionsList().removeTransactionById(transactionId);
	}

	public Transaction[] getUnpairedTransactions() {

		int incomTransactionsSize = 0;
		int outcomTransactionsSize = 0;

		for (int i = 0; i < db.getUsersSize(); i++) {
			User user = db.getUserByIndex(i);
			Transaction[] transactions = user.getTransactionsList().toArray();
			for (Transaction transaction : transactions) {
				if (transaction.getTransferCategory().equals(Transaction.TransferCategory.INCOME)) {
					incomTransactionsSize += 1;
				} else {
					outcomTransactionsSize += 1;
				}
			}
		}
		
		Transaction[] incomTransactions = new Transaction[incomTransactionsSize];
		boolean[] incomTransactionsPaired = new boolean[incomTransactionsSize];

		Transaction[] outcomTransactions = new Transaction[outcomTransactionsSize];
		boolean[] outcomTransactionsPaired = new boolean[outcomTransactionsSize];
		
		int incomeIdx = 0, outcomeIdx = 0;

		for (int i = 0; i < db.getUsersSize(); i++) {
			User user = db.getUserByIndex(i);
			Transaction[] transactions = user.getTransactionsList().toArray();
			for (Transaction transaction : transactions) {
				if (transaction.getTransferCategory().equals(Transaction.TransferCategory.INCOME)) {
					incomTransactions[incomeIdx] = transaction;
					incomTransactionsPaired[incomeIdx] = false;
					incomeIdx += 1;
				} else {
					outcomTransactions[outcomeIdx] = transaction;
					outcomTransactionsPaired[outcomeIdx] = false;
					outcomeIdx += 1;
				}
			}
		}

		int numberOfUnpairedTransactions = incomTransactionsSize + outcomTransactionsSize;

		for (int i = 0; i < incomTransactions.length; i++) {
			for (int j = 0; j < outcomTransactions.length; j++) {
				if (incomTransactions[i].getUUID().equals(outcomTransactions[j].getUUID())) {
					numberOfUnpairedTransactions -= (incomTransactionsPaired[i] ? 0 : 1) + (outcomTransactionsPaired[j] ? 0 : 1); 
					incomTransactionsPaired[i] = true;
					outcomTransactionsPaired[j] = true;
				}
			}
		}

		Transaction[] result = new Transaction[numberOfUnpairedTransactions];
		int idx = 0;

		for (int i = 0; i < incomTransactions.length; i++) {
			if (incomTransactionsPaired[i] == false) {
				result[idx++] = incomTransactions[i];
			}
		}

		for (int i = 0; i < outcomTransactions.length; i++) {
			if (outcomTransactionsPaired[i] == false) {
				result[idx++] = outcomTransactions[i];
			}
		}

		return result;
	}
}
