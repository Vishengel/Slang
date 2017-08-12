import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private GameModel model;
	
	public GamePanel() {
		setBackground(Color.white);
		setFocusable(true);
	}
	
	public GamePanel(int width, int height) {
		setBackground(Color.white);
		setFocusable(true);
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawGrid(g);
		drawSnake(g);
	}
	
	public void drawGrid(Graphics g) {
		int gridSize = model.getGameGrid().getGridSize();
		int cellSize = getSize().width / gridSize;
		
		for (int i=0; i<gridSize; i++) {
			for (int j=0; j<gridSize; j++) {
				g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}
	}
	
	public void drawSnake(Graphics g) {
		int gridSize = model.getGameGrid().getGridSize();
		int cellSize = getSize().width / gridSize;
		
		GameGrid grid = model.getGameGrid();
		
		for (int i=0; i<gridSize; i++) {
			for (int j=0; j<gridSize; j++) {
				if (grid.getCellArray()[i][j].getHasSnake()) {
					g.setColor(Color.GREEN);
					g.fillRect(i*cellSize+1, j*cellSize+1, cellSize-1, cellSize-1);
				}
			}
		}
	}
	
	public void setGameModel(GameModel model) {
		this.model = model;
	}
}
