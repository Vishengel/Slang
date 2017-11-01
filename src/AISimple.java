
public class AISimple extends AI {
	GameModel model;

	public AISimple(GameModel model) {
		this.model = model;
		
	}
	
	public int getKeyInput(GameModel model, boolean logSteps) {
		this.model = model;
		
		return 87;
	}
	
}
