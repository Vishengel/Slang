import java.util.Properties;

public class Slang {

	public static void main(String[] args) {
		SlangOptions slangOptions = new SlangOptions();
		slangOptions.setOptions();
		if(slangOptions.defaultOptions) {
			System.out.println("Using default options");
		} else {
			System.out.println("Using config file options");
		}
		// GameLogger lo = new GameLogger();
		GameController co = new GameController(Strategy.SIMPLE);
		// GameController co = new GameController(Strategy.SIMPLE, slangOptions);
	}
}
