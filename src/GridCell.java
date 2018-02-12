
public class GridCell {
	boolean hasSnake = false;
	boolean hasFood = false;
	//Values used for the A*-algorithm
	int xPos, yPos, fScore, gScore;
	
	public GridCell(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public boolean getHasSnake() {
		return hasSnake;
	}
	
	public void setHasSnake(boolean hasSnake) {
		this.hasSnake = hasSnake;
	}
	
	public boolean getHasFood() {
		return hasFood;
	}
	
	public void setHasFood(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getFScore() {
		return this.fScore;
	}
	
	public void setFScore(int fScore) {
		this.fScore = fScore;
	}
	
	public int getGScore() {
		return this.gScore;
	}
	
	public void setGScore(int gScore) {
		this.gScore = gScore;
	}
}
