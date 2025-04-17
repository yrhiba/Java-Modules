import java.util.UUID;

public class Transaction {

	public enum TransferCategory {
		OUTCOME,
		INCOME
	}

	private final UUID identifier;
	private final int recipientId;
	private final int senderId;
	private final TransferCategory transferCategory;
	private final int transferAmount;

	public Transaction(UUID id, int recipientId, int senderId, TransferCategory transferCategory, int transferAmount)
	{
		this.identifier = id;
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.transferCategory = transferCategory;
		this.transferAmount = transferAmount;
	}

	public UUID getUUID() {
		return this.identifier;
	}

	public TransferCategory getTransferCategory() {
		return this.transferCategory;
	}

	public int getTransferAmount() {
		return this.transferAmount;
	}
	
	public int getrecipientId() {
		return this.recipientId;
	}

	public int getsenderId() {
		return this.senderId;
	}

	@Override
	public String toString() {
		return "Transaction[" +
			"identifier=" + this.identifier +
			", recipientId=" + this.recipientId +
			", senderId=" + this.senderId +
			", transferCategory=" + this.transferCategory +
			", transferAmount=" + this.transferAmount +
			"]";
	}
}
