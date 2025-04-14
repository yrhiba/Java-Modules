public class UserIdsGenerator {

	private static UserIdsGenerator instance;
	private int currentId;

	private UserIdsGenerator() {
		this.currentId = 0;
	}

	static {
		try {
			instance = new UserIdsGenerator();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred in creating singleton instance");
		}
	}

	public static UserIdsGenerator getInstance() {
		return instance;
	}

	public int generateId() {
		return this.currentId++;
	}
}
