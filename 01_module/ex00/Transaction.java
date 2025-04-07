import java.util.UUID;

public class Transaction {

	public enum TransferCategory {
		OUTCOME,
		INCOME
	}

	private final UUID identifier;
	private final int recipientId;
	private final int senderId;
	private TransferCategory transferCategory;
	private final int transferAmount;

	public Transaction(int recipientId, int senderId, TransferCategory transferCategory, int transferAmount)
	{
		this.identifier = UUID.randomUUID();
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.transferCategory = transferCategory;
		this.transferAmount = transferAmount;
	}

	public String getTransactionSummary() {
		String res = "Identifier: " + this.identifier;
		res += ", recipientId: " + this.recipientId;
		res += ", senderId: " + this.senderId;
		res += ", transferCategory: " + this.transferCategory;
		res += ", transferAmount: " + this.transferAmount;
		return res;
	}
}
