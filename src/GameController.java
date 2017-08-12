import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController {
	private GameModel model;
	private GameView view;
	private boolean isRunning = false;
	int keyPressed;
	private final int fps = 60;
	private final int gameSpeed = 140;
	
	public GameController() {
		initGame();
	}
	
	public void initGame() {
		model = new GameModel();
		view = new GameView();

		view.getGamePanel().addKeyListener(new InputController());
		view.getGamePanel().requestFocusInWindow();
		this.view.getGamePanel().setGameModel(model);	
		
		isRunning = true;
		keyPressed = 0;
		
		gameLoop();
	}
	
	public void gameLoop() {
		long lastLoopTime = System.nanoTime(), optimalTime = 1000000000 / fps, now, updateLength;
		
		while(isRunning) {
			now = System.nanoTime();
			updateLength = now - lastLoopTime;
			lastLoopTime = now;

			this.model.updateModel(keyPressed);
			if (this.model.getSnakeDied()) {
				isRunning = false;
			} else {			
				this.view.getGamePanel().setGameModel(model);
				this.view.getGamePanel().repaint();
			}

			try{
				Thread.sleep( (lastLoopTime - System.nanoTime())/1000000 + gameSpeed);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
		
		System.out.println("SLAAANG?");
		initGame();
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
