import java.util.ArrayList;

public class GameGrid {
	GridCell[][] cellArray;
	int gridSize = 20;
	
	public GameGrid() {
		cellArray = new GridCell[gridSize][gridSize];
		for(int i=0; i<gridSize; i++) {
			for(int j=0; j<gridSize; j++) {
				cellArray[i][j] = new GridCell();
			}
		}
	}

	public GridCell[][] getCellArray() {
		return this.cellArray;
	}
	
	public int getGridSize() {
		return this.gridSize;
	}
}
