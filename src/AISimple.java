
public class AISimple extends AI {
	GameModel model;

	public AISimple(GameModel model) {
		this.model = model;
		
	}
	
	public int getKeyInput(GameModel model) {
		this.model = model;
		
		return 87;
	}
	
}
