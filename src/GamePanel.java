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
		drawObjects(g);
	}
	
	public void drawGrid(Graphics g) {
		int gridSize = model.getGameGrid().getGridSize();
		int cellSize = getSize().width / gridSize;
		
		for (int i=0; i<gridSize; i++) {
			for (int j=0; j<gridSize; j++) {
				g.setColor(Color.BLACK);
				g.drawRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}
	}
	
	public void drawObjects(Graphics g) {
		int gridSize = model.getGameGrid().getGridSize();
		int cellSize = getSize().width / gridSize;
		int pX, pY;
		
		GameGrid grid = model.getGameGrid();
		
		for (int i=0; i<gridSize; i++) {
			for (int j=0; j<gridSize; j++) {
				if (grid.getCellArray()[i][j].getHasSnake()) {
					g.setColor(Color.GREEN);
					g.fillRect(i*cellSize+1, j*cellSize+1, cellSize-1, cellSize-1);
				} else if (grid.getCellArray()[i][j].getHasFood()) {
					g.setColor(Color.RED);
					g.fillRect(i*cellSize+1, j*cellSize+1, cellSize-1, cellSize-1);
				} else if (grid.getCellArray()[i][j].getPath()) {
					g.setColor(Color.BLUE);
					g.fillRect(i*cellSize+1, j*cellSize+1, cellSize-1, cellSize-1);
				}	/*else if (grid.getCellArray()[i][j].getFScore() > 0) {
					g.setColor(Color.BLUE);
					g.fillRect(i*cellSize+1, j*cellSize+1, cellSize-1, cellSize-1);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(grid.getCellArray()[i][j].getFScore()), 
							i*cellSize+1, (int) (j*cellSize+0.25*cellSize));
					
					g.drawString(Integer.toString(grid.getCellArray()[i][j].getXPos()) + ", ", 
							i*cellSize+1, (int) (j*cellSize+0.6*cellSize-1));
					
					g.drawString(Integer.toString(grid.getCellArray()[i][j].getYPos()), 
							(int) (i*cellSize+0.5*cellSize), (int) (j*cellSize+0.6*cellSize-1));
					
					pX = grid.getCellArray()[i][j].getParent().getXPos();
					pY = grid.getCellArray()[i][j].getParent().getYPos();
					
					g.drawString(Integer.toString(pX) + ", ", 
							i*cellSize+1, (int) (j*cellSize+cellSize-1));
					
					g.drawString(Integer.toString(pY), 
							(int) (i*cellSize+0.5*cellSize), (int) (j*cellSize+cellSize-1));
				} */
			}
		}
	}
	
	public void setGameModel(GameModel model) {
		this.model = model;
	}
}
