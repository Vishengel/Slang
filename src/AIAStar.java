import java.util.ArrayList;

public class AIAStar extends AI {
	GameModel model;
	private int keyInput;

	public AIAStar(GameModel model) {
		this.model = model;
	}
	
	@Override
	public int getKeyInput(GameModel model, boolean logSteps) {
		this.model = model;
		int headX = model.getSnake().getHead().getXPos(), headY = model.getSnake().getHead().getYPos();
		int foodX = model.getFoodX(), foodY = model.getFoodY();
		String direction = model.getSnake().getHead().getDirection();
		
		AStar(headX, headY, foodX, foodY);
		
		return keyInput;
	}
	
	public void AStar(int headX, int headY, int foodX, int foodY) {
		GameGrid grid = model.getGameGrid();
		GridCell start = grid.getCellArray()[headX][headY];
		GridCell goal = grid.getCellArray()[foodX][foodY];
		GridCell current;
		
		ArrayList<GridCell> closedSet = new ArrayList<GridCell>();
		ArrayList<GridCell> openSet = new ArrayList<GridCell>();
		openSet.add(start);

		start.setGScore(0);
		start.setFScore(start.getGScore() + getManhattanDistance(start.getXPos(), start.getYPos(), goal.getXPos(), goal.getYPos()));
		
		while(!openSet.isEmpty()) {
			current = openSet.get(getLowestFScore(openSet));
			if(current.getXPos() == goal.getXPos() && current.getYPos() == goal.getYPos()) {
				constructPath();
			}
			openSet.remove(openSet.indexOf(current));
			closedSet.add(current);
		}

	}
	
	public int getManhattanDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	public int getLowestFScore(ArrayList<GridCell> openSet) {
		int indexOfLowest = 0;
		int lowestFScore = 1000;
		for (GridCell gc : openSet) {
			if (gc.getFScore() < lowestFScore) {
				indexOfLowest = openSet.indexOf(gc);
			}
		}
		
		return indexOfLowest;
	}
	
	public void constructPath() {
		
	}
	
}
