package pedrofrayha.blackjack.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PlayerWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PlayerPanel playerPanel;

	public PlayerWindow(int id)
	{
		super("Jogador " + (id + 1));
		this.setSize(850, 800);
		initializePlayerUI();
		this.setVisible(true);
		this.playerPanel.setVisible(true);
		
	}
	
	private void initializePlayerUI()
	{
		this.playerPanel = new PlayerPanel();
		this.getContentPane().add(this.playerPanel);
	}
	
	public void endPlayer()
	{
		JOptionPane.showMessageDialog(this, "Você faliu! A janela será fechada", "JOGADOR FALIU", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
}
