import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController {
	private GameModel model;
	private GameView view;
	private boolean isRunning = false;
	private enableViewer = false;
		private final int gameSpeed = 40;
	int keyPressed;

	InputGenerator randomInput;


	public GameController() {
		initGame();
	}
	
	public void initGame() {
		randomInput = new InputGenerator();
		model = new GameModel();
		// view = new GameView();

		// view.getGamePanel().addKeyListener(new InputController());
		// view.getGamePanel().requestFocusInWindow();
		// this.view.getGamePanel().setGameModel(model);	
		
		isRunning = true;
		keyPressed = 0;
		
		gameLoop();
	}
	
	public void gameLoop() {
		long lastLoopTime = System.nanoTime(), now;
		
		while(isRunning) {
			now = System.nanoTime();
			lastLoopTime = now;

			this.model.updateModel(randomInput.generateInput());
			if (this.model.getSnakeDied()) {
				//isRunning = false;
				//System.out.println("SLAAANG?");
				System.out.print("Score: ");
				System.out.println(model.getScore());
				model = new GameModel();
			} else {			
				// this.view.getGamePanel().setGameModel(model);
				// this.view.getGamePanel().repaint();
			}

			try{
				Thread.sleep( (lastLoopTime - System.nanoTime())/1000000 + gameSpeed);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}	
	}
	
	class InputController implements KeyListener {
		
		@Override
		public void keyPressed(KeyEvent e) {
			GameController.this.keyPressed = e.getKeyCode();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
