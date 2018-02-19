import java.util.ArrayList;

public class AIAStar extends AI {
	GameModel model;
	private int keyInput, headX, headY, foodX, foodY;
	private boolean firstRun = true;

	public AIAStar(GameModel model) {
		this.model = model;
	}
	
	@Override
	public int getKeyInput(GameModel model, boolean logSteps) {
		this.model = model;
		this.headX = model.getSnake().getHead().getXPos();
		this.headY = model.getSnake().getHead().getYPos();
		this.foodX = model.getFoodX();
		this.foodY = model.getFoodY();
		GridCell nextCell;
		
		String direction = model.getSnake().getHead().getDirection();
		
		clearGrid(model.getGameGrid());
		
		nextCell = AStar(headX, headY, foodX, foodY);
		
		if(nextCell.getXPos() > headX) {
			keyInput = 68;
		} else if (nextCell.getXPos() < headX) {
			keyInput = 65;
		} else if (nextCell.getYPos() < headY) {
			keyInput = 87;
		} else if (nextCell.getYPos() > headY) {
			keyInput = 83;
		}
		
		return keyInput;
	}
	
	public GridCell AStar(int headX, int headY, int foodX, int foodY) {
		GameGrid grid = model.getGameGrid();
		GridCell start = grid.getCellArray()[headX][headY];
		GridCell goal = grid.getCellArray()[foodX][foodY];
		GridCell current;

		int iter = 0;
		int tempGScore = 0;
		
		ArrayList<GridCell> closedSet = new ArrayList<GridCell>();
		ArrayList<GridCell> openSet = new ArrayList<GridCell>();
		ArrayList<GridCell> path = new ArrayList<GridCell>();
		
		openSet.add(start);
		
		start.setGScore(0);
		start.setFScore(start.getGScore() + getManhattanDistance(start.getXPos(), start.getYPos(), goal.getXPos(), goal.getYPos()));
		
		while(!openSet.isEmpty()) {
			System.out.println("Iteration " + iter++);
			System.out.println("======================");
			
			current = openSet.get(getLowestFScore(openSet));
			System.out.println("Current: " + current.getXPos() + ", " + current.getYPos() + " - F: " + current.getFScore());
			
			if(current.getXPos() == goal.getXPos() && current.getYPos() == goal.getYPos()) {
				path = constructPath(goal);
				//System.out.println("Path found: " + path.get(path.size()-1).getXPos() + ", " + path.get(path.size()-1).getYPos());
				if (path.size() > 2) {
					return path.get(path.size()-2);
				} else {
					return path.get(0);
				}
			}
			
			openSet.remove(openSet.indexOf(current));
			closedSet.add(current);
			
			for (GridCell n : getNeighbors(current)) {
				// Do nothing if the neighbor is already in the closed set
				if(inSet(n, closedSet) < 0) {
					// Add the node to the open set if it is not already a part of it
					if(inSet(n, openSet) < 0) {
						openSet.add(n);
					}
					tempGScore = current.getGScore() + 1;
					
					if (tempGScore < n.getGScore()) {
						n.setParent(current);
						n.setGScore(tempGScore);
						n.setFScore(n.getGScore() + getManhattanDistance(n.getXPos(), n.getYPos(), goal.getXPos(), goal.getYPos()));
					}
				}
			}
			
			
		}
		
		return closedSet.get(getLowestFScore(closedSet));
	}

	public void clearGrid(GameGrid gameGrid) {
		for(int i=0; i<gameGrid.getGridSize(); i++) {
			for(int j=0; j<gameGrid.getGridSize(); j++) {
				gameGrid.getCellArray()[i][j].setParent(null);
				gameGrid.getCellArray()[i][j].setFScore(0);
				gameGrid.getCellArray()[i][j].setGScore(1000);
				gameGrid.getCellArray()[i][j].setPath(false);
			}
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
				lowestFScore = gc.getFScore();
			}
		}
		
		return indexOfLowest;
	}
	
	public ArrayList<GridCell> constructPath(GridCell goal) {
		System.out.println("Contructing path");
		ArrayList<GridCell> path = new ArrayList<GridCell>();
		path.add(goal);
		while(goal.getParent() != null) {
			System.out.println("Parent node: " + goal.getParent().getXPos() + ", " + goal.getParent().getYPos());
			model.getGameGrid().getCellArray()[goal.getXPos()][goal.getYPos()].setPath(true);
			goal = goal.getParent();
			path.add(goal);
		}
		
		return path;
	}
	
	public ArrayList<GridCell> getNeighbors(GridCell gc) {
		ArrayList<GridCell> neighbors = new ArrayList<GridCell>();
		GridCell[][] cellArray = this.model.getGameGrid().getCellArray();
		
		if (gc.getYPos()-1 >= 0 && !cellArray[gc.getXPos()][gc.getYPos()-1].hasSnake) {
			neighbors.add(cellArray[gc.getXPos()][gc.getYPos()-1]);
		}
		if (gc.getXPos()+1 < this.model.getGameGrid().getGridSize() && !cellArray[gc.getXPos()+1][gc.getYPos()].hasSnake) {
			neighbors.add(cellArray[gc.getXPos()+1][gc.getYPos()]);
		}
		if (gc.getYPos()+1 < this.model.getGameGrid().getGridSize() && !cellArray[gc.getXPos()][gc.getYPos()+1].hasSnake) {
			neighbors.add(cellArray[gc.getXPos()][gc.getYPos()+1]);
		}
		if (gc.getXPos()-1 >= 0 && !cellArray[gc.getXPos()-1][gc.getYPos()].hasSnake) {
			neighbors.add(cellArray[gc.getXPos()-1][gc.getYPos()]);
		}
		
		return neighbors;
	}
	
	public int inSet(GridCell gc, ArrayList<GridCell> set) {
		for (GridCell i : set) {
			if(gc.getXPos() == i.getXPos() && gc.getYPos() == i.getYPos()) {
				return set.indexOf(i);
			}
		}
		
		return -1;
	}
	
}
