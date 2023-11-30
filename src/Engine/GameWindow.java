package Engine;

import Engine.OptionsMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GameWindow {
	private JFrame gameWindow;
	private GamePanel gamePanel;
	private OptionsMenu optionsMenu;

	public GameWindow() {
		gameWindow = new JFrame("Quinnipiac RPG");
		gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gameWindow.setContentPane(gamePanel);
		gameWindow.setResizable(true);
		gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Instantiate OptionsMenu
		optionsMenu = new OptionsMenu();

		// Set the menu bar for the JFrame
		gameWindow.setJMenuBar(optionsMenu);

		gamePanel.setupGame();
		setupKeyListener();
	}

	private void setupKeyListener() {
		// Add key listener for handling Escape key press to toggle options menu
		// visibility
		gamePanel.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					optionsMenu.toggleOptionsMenuVisibility();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	public void startGame() {
		gamePanel.startGame();
	}

	public ScreenManager getScreenManager() {
		return gamePanel.getScreenManager();
	}
}
