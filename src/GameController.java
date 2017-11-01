import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController {
	private GameModel model;
	private GameView view;
	private boolean isRunning = false;
	int keyPressed;
	private final int gameSpeed = 140;
	AI ai;
	private Strategy strategy;

	public GameController() {
		this.strategy = Strategy.MANUAL;
		initGame();
	}
	
	public GameController(Strategy strategy) {
		this.strategy = strategy;
		initGame();
	}
	
	public void initGame() {
		ai = new InputGenerator();
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
		long lastLoopTime = System.nanoTime(), now;
		
		while(isRunning) {
			now = System.nanoTime();
			lastLoopTime = now;

			this.model.updateModel(getKeyInput());
			if (this.model.getSnakeDied()) {
				//isRunning = false;
				System.out.println("SLAAANG?");
				model = new GameModel();
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
	}
	
	public int getKeyInput() {
		if (this.strategy.equals(Strategy.MANUAL)) {
			return this.keyPressed;
		} else {
			return ai.getKeyInput();
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
