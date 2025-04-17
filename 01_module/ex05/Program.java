public class Program {
	static public void main(String[] args) {

		Menu menu = new Menu();

		if (args.length > 1) {
			throw new RuntimeException("invalid number of arguments");
		}
		
		if (args.length == 1) {
			if (args[0].equals("--profile=dev")) {
				menu.activateDevMode();
			} else {
				throw new RuntimeException("invalid argument");
			}
		}

		menu.start();
	}
}
