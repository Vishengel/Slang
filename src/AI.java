import java.util.Random;

public abstract class AI {

	private boolean logSteps = true;
	
	public abstract int getKeyInput(GameModel model, boolean logSteps);
}