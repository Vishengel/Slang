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
	
	public void addSnakeBlock() {
		int newX = 0, newY = 0;
		SnakeBlock tail = snakeList.get(snakeList.size() - 1);
		
		if (tail.getDirection().equals("up")) {
			newX = tail.getXPos();
			newY = tail.getYPos() + 1;
		} else if (tail.getDirection().equals("down")) {
			newX = tail.getXPos();
			newY = tail.getYPos() - 1;
		} else if (tail.getDirection().equals("left")) {
			newX = tail.getXPos() + 1;
			newY = tail.getYPos();
		} else if (tail.getDirection().equals("right")) {
			newX = tail.getXPos() - 1;
			newY = tail.getYPos();
		}
		snakeList.add(new SnakeBlock(newX, newY));
	}
	
	public void setDirections() {
		for (int i=snakeList.size()-1; i>0; i--) {
			snakeList.get(i).setDirection(snakeList.get(i-1).getDirection());
		}
	}
}
