public class GameModel {
	private GameGrid grid;
	private boolean[] keysDown;
	private Snake snake;
	private boolean snakeDied = false;
	private boolean foodEaten = false;
	
	public GameModel() {
		grid = new GameGrid();
		snake = new Snake(grid.getGridSize());
	}
	
	public void updateModel(int keyPressed) {
		clearGrid();
		moveSnake(keyPressed);
		
		checkWallCollission();
		
		if (!snakeDied) {
			for(SnakeBlock sb : snake.getSnakeList()) {
				grid.getCellArray()[sb.getXPos()][sb.getYPos()].setHasSnake(true);
			}
		}
	}
	
	public void clearGrid() {
		for(int i=0; i<grid.getGridSize(); i++) {
			for(int j=0; j<grid.getGridSize(); j++) {
				grid.getCellArray()[i][j].setHasSnake(false);
			}
		}
	}
	
	public void moveSnake(int keyPressed) {
		switch(keyPressed) {
			case 87: 
				snake.getHead().setDirection("up");
				break;
			case 83:
				snake.getHead().setDirection("down");
				break;
			case 65:
				snake.getHead().setDirection("left");
				break;
			case 68:
				snake.getHead().setDirection("right");
				break;
		}
		
		snake.getHead().move();
	}
	
	public void checkWallCollission() {
		if (snake.getHead().getXPos() < 0 || snake.getHead().getXPos() >= grid.gridSize || 
			snake.getHead().getYPos() < 0 || snake.getHead().getYPos() >= grid.gridSize ) {
			snakeDied = true;
		}
	}
	
	public void setKeysDown(boolean[] keysDown) {
		this.keysDown = keysDown;
	}
	
	public GameGrid getGameGrid() {
		return this.grid;
	}
	
	public Snake getSnake() {
		return this.snake;
	}
	
	public boolean getSnakeDied() {
		return snakeDied;
	}
}
