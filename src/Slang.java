import java.util.Properties;

public class Slang {

	public static void main(String[] args) {
		SlangOptions slangOptions = new SlangOptions();
		slangOptions.setOptions();
		GameLogger lo = new GameLogger();
		GameController co = new GameController(Strategy.SIMPLE);
		// GameController co = new GameController(Strategy.SIMPLE, slangOptions);
	}
}
