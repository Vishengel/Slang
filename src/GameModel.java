import java.util.concurrent.ThreadLocalRandom;

public class GameModel {
	private GameGrid grid;
	private boolean[] keysDown;
	private Snake snake;
	private boolean wallCollision = false, selfCollision = false, snakeDied = false;
	//Keep track of the food's coordinates
	private int foodX, foodY;
	
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
				if (!snake.getHead().getDirection().equals("down")) {
					snake.getHead().setDirection("up");
				}
				break;
			case 83:
				if (!snake.getHead().getDirection().equals("up")) {
					snake.getHead().setDirection("down");
				}
				break;
			case 65:
				if (!snake.getHead().getDirection().equals("right")) {
					snake.getHead().setDirection("left");
				}
				break;
			case 68:
				if (!snake.getHead().getDirection().equals("left")) {
					snake.getHead().setDirection("right");
				}
				break;
		}
		
		for (SnakeBlock sb : snake.getSnakeList()) {
			sb.move();
		}
	}
	
	public void checkWallCollision() {
		if (snake.getHead().getXPos() < 0 || snake.getHead().getXPos() >= grid.gridSize || 
			snake.getHead().getYPos() < 0 || snake.getHead().getYPos() >= grid.gridSize ) {
			snakeDied = true;
		}
	}
	
	public boolean checkFoodCollision(int foodX, int foodY) {
		for (SnakeBlock block : this.snake.getSnakeList()) {
			if (block.getXPos() == foodX && block.getYPos() == foodY) {
				return true;
			}
		}
		System.out.println("No food collision");
		return false;
	}
	
	public void spawnFood() {
		do {
			foodX = ThreadLocalRandom.current().nextInt(0, grid.getGridSize());
			foodY = ThreadLocalRandom.current().nextInt(0, grid.getGridSize());
		} while (checkFoodCollision(foodX, foodY));
		
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
