import java.util.concurrent.ThreadLocalRandom;

public class SnakeBlock {
	private int xPos;
	private int yPos;
	private String direction = "";
	
	public SnakeBlock(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void move() {
		if (direction.equals("up")) {
			yPos--;
		} else if (direction.equals("down")) {
			yPos++;
		} else if (direction.equals("left")) {
			xPos--;
		} else if (direction.equals("right")) {
			xPos++;
		}
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public void setXPos(int newXPos) {
		this.xPos = newXPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}
	
	public void setYPos(int newYPos) {
		this.yPos = newYPos;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
}
