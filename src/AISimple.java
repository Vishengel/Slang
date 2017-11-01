
public class AISimple extends AI {
	GameModel model;
	private int keyInput;

	public AISimple(GameModel model) {
		this.model = model;
	}
	
	public int getKeyInput(GameModel model) {
		this.model = model;
		int headX = model.getSnake().getHead().getXPos(), headY = model.getSnake().getHead().getYPos();
		int foodX = model.getFoodX(), foodY = model.getFoodY();
		String direction = model.getSnake().getHead().getDirection();
		
		if (headX > foodX && !(direction.equals("right") || direction.equals("left"))) {
			keyInput = 65;
		} else if (headX < foodX && !(direction.equals("right") || direction.equals("left"))) {
			keyInput = 68;
		} else if (headY > foodY && !(direction.equals("up") || direction.equals("down"))) {
			keyInput = 87;
		} else if (headY < foodY && !(direction.equals("up") || direction.equals("down"))) {
			keyInput = 83;
		}
		
		return keyInput;
	}
	
}
