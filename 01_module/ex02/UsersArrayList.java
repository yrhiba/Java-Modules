public class UsersArrayList implements UsersList {

	private User[] users = new User[10];
	private int size = 0;

	@Override
	public void addUser(User user) {
		if (size == users.length) {
			User[] tmp = new User[size * 2];
			for (int i = 0; i < size; i++) {
				tmp[i] = users[i];
			}
			users = tmp;
		}
		users[size] = user;
		size += 1;
	}

	@Override
	public User getUserById(int id) {
		for (int i = 0; i < size; i++) {
			if (users[i].getIdentifier() == id) {
				return users[i];
			}
		}
		throw new UserNotFoundException("Attempt to retrieve a user with a non-existent ID.");
	}
	
	@Override
	public User getUserByIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new UserNotFoundException("Attempt to retrieve a user with a non-valid index.");
		}
		return users[idx];
	}

	@Override
	public int getUsersSize() {
		return (size);
	}
}
