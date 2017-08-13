import java.util.concurrent.ThreadLocalRandom;

public class GameModel {
	private GameGrid grid;
	private boolean[] keysDown;
	private Snake snake;
	private boolean wallCollision = false, selfCollision = false, snakeDied = false;
	//Keep track of the food's coordinates
	private int foodX;
	private int foodY;
	
	public GameModel() {
		grid = new GameGrid();
		snake = new Snake(grid.getGridSize());
		spawnFood();
	}
	
	public void updateModel(int keyPressed) {
		clearGrid();
		snake.setDirections();
		moveSnake(keyPressed);
		
		checkWallCollision();
		
		if (!wallCollision) {
			//checkSelfCollision();
		}
		
		if (wallCollision || selfCollision) {
			snakeDied = true;
		}
		
		if (!snakeDied) {
			
			for(SnakeBlock sb : snake.getSnakeList()) {
				if (grid.getCellArray()[sb.getXPos()][sb.getYPos()].getHasSnake()) {
					snakeDied = true;
					break;
				} else {
					grid.getCellArray()[sb.getXPos()][sb.getYPos()].setHasSnake(true);
				}
			}
		}
		
		if (snake.getHead().getXPos() == foodX && snake.getHead().getYPos() == foodY) {
			grid.getCellArray()[foodX][foodY].setHasFood(false);
			snake.addSnakeBlock();
			spawnFood();
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
		
		for (SnakeBlock sb : snake.getSnakeList()) {
			sb.move();
		}
	}
	
	public void checkSelfCollision() {
		if (grid.getCellArray()[snake.getHead().getXPos()][snake.getHead().getYPos()].getHasSnake()) {
			selfCollision = true;
		}
	}
	
	public void checkWallCollision() {
		if (snake.getHead().getXPos() < 0 || snake.getHead().getXPos() >= grid.gridSize || 
			snake.getHead().getYPos() < 0 || snake.getHead().getYPos() >= grid.gridSize ) {
			wallCollision = true;
		}
	}
	
	public void spawnFood() {
		foodX = ThreadLocalRandom.current().nextInt(0, grid.getGridSize());
		foodY = ThreadLocalRandom.current().nextInt(0, grid.getGridSize());
		
		grid.getCellArray()[foodX][foodY].setHasFood(true);
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
