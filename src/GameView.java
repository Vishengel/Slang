import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Observable;


public class GameView extends JFrame {
	private JFrame frame;
	private GamePanel gamePanel;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int gameSize = screenSize.width / 2;
	
	public GameView() {
		initFrame();
		initPanels();		
	}
	
	public void initFrame() {
		frame = new JFrame("Slang");
		frame.setBackground(Color.WHITE);
		frame.setLayout(new FlowLayout());
		frame.setSize(gameSize, gameSize);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void initPanels() {

		gamePanel = new GamePanel(gameSize-50, gameSize-50);
		//gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

		frame.add(gamePanel);
	}
	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

}
