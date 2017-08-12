import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
	private ArrayList<SnakeBlock> snakeList;
	
	public Snake(int gridSize) {		
		snakeList = new ArrayList<SnakeBlock>();
		
		int randX = ThreadLocalRandom.current().nextInt(0, gridSize);
		int randY = ThreadLocalRandom.current().nextInt(0, gridSize);
		
		snakeList.add(new SnakeBlock(randX, randY));
	}

	public ArrayList<SnakeBlock> getSnakeList() {
		return snakeList;
	}
	
	public SnakeBlock getHead() {
		return snakeList.get(0);
	}
}
