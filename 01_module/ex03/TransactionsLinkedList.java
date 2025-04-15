import java.util.UUID;


public class TransactionsLinkedList implements TransactionsList {

	private static class Node {
		final Transaction transaction;
		Node next;
		Node previous;
	
		Node(Transaction transaction) {
			this.transaction = transaction;
			this.next = null;
			this.previous = null;
		}
	
		Node(Transaction transaction, Node previous) {
			this.transaction = transaction;
			this.previous = previous;
			this.next = null;
		}
	}

	private Node transactionsList = null;
	private Node lastNode = null;
	private int size = 0;

	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction == null) {
			return ;
		}
		if (this.transactionsList == null) {
			this.transactionsList = new Node(transaction);
			this.lastNode = this.transactionsList;
		} else {
			this.lastNode.next = new Node(transaction, this.lastNode);
			this.lastNode = this.lastNode.next;
		}
		this.size += 1;
	}

	@Override
	public void removeTransactionById(UUID id) {
		if (size == 0) {
			throw new TransactionNotFoundException("Transaction Not Found Exception");
		}

		Node it = this.transactionsList;

		while (it != null) {
			if (it.transaction.getUUID().equals(id)) {
				if (it.previous != null && it.next != null) {
					it.previous.next = it.next;
					it.next.previous = it.previous;
				} else if (it.previous != null && it.next == null) {
					it.previous.next = null;
					this.lastNode = it.previous;
				} else if (it.previous == null && it.next != null) {
					this.transactionsList = this.transactionsList.next;
					this.transactionsList.previous = null;
				} else {
					this.transactionsList = null;
					this.lastNode = null;
				}
				size -= 1;
				return ;
			}
			it = it.next;
		}

		throw new TransactionNotFoundException("Transaction Not Found Exception");
	}

	@Override
	public Transaction[] toArray() {
		Transaction[] result = new Transaction[this.size];
		Node it = this.transactionsList;
		int i = 0;
		while (it != null) {
			result[i] = it.transaction;
			it = it.next;
			i += 1;
		}
		return result;
	}

	public int size() {
		return this.size;
	}
}
