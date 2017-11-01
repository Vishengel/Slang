
public class AIRetarded extends AI {
	GameModel model;

	public AIRetarded(GameModel model) {
		this.model = model;
		
	}
	
	public int getKeyInput(GameModel model) {
		this.model = model;
		
		return 87;
	}
	
}
