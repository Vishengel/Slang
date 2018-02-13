import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController {
	private GameModel model;
	private GameView view;
	private boolean isRunning = false;
	private boolean enableViewer = true;
	int keyPressed;
	private final int gameSpeed = 140;
	private AI ai;
	private Strategy strategy;
	private boolean logSteps = true;
	private boolean stepWise = false;



	public GameController() {
		this.strategy = Strategy.MANUAL;
		initGame();
	}
	
	public GameController(Strategy strategy) {
		this.strategy = strategy;
		initGame();
	}
	
	public void initGame() {
		if (!strategy.equals(Strategy.MANUAL)) {
			setAI();
		}
	
		model = new GameModel();
		if (enableViewer) {
			view = new GameView();
			view.getGamePanel().addKeyListener(new InputController());
			view.getGamePanel().requestFocusInWindow();
			view.getGamePanel().setGameModel(model);	
		}
		
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
				//System.out.println("SLAAANG?");
				//System.out.print("Score: ");
				//System.out.println(model.getScore());
				model = new GameModel();
			} else if (enableViewer) {			
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
			return this.ai.getKeyInput(model, logSteps);
		}
	}

	public void setAI() {
		if (this.strategy.equals(Strategy.RANDOM)) {
			this.ai = new AIRandom();
		} else if (this.strategy.equals(Strategy.SIMPLE)) {
			this.ai = new AISimple(model);
		} else if (this.strategy.equals(Strategy.ASTAR)) {
			this.ai = new AIAStar(model);
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
