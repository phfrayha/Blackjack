package pedrofrayha.blackjack.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pedrofrayha.blackjack.utils.Hand;

public class PlayerWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	PlayerPanel playerPanel;

	public PlayerWindow(int id)
	{
		super("Jogador " + (id + 1));
		this.id = id;
		this.setSize(756, 600);
		initializePlayerUI();
		this.setVisible(true);
		this.setResizable(false);
		this.playerPanel.setVisible(true);
		
	}
	
	private void initializePlayerUI()
	{
		this.playerPanel = new PlayerPanel(this.id);
		this.getContentPane().add(this.playerPanel);
	}
	
	public void toPlayingState(Hand cards) 
	{
		this.playerPanel.toPlayingState(cards);
	}
	
	public void toBettingState()
	{
		this.playerPanel.toBettingState();
	}
	
	public void refresh()
	{
		this.playerPanel.refresh();
	}
	
	public void setPreview(boolean flag)
	{
		this.playerPanel.setPreview(flag);
	}
	
	public void toWaitingState()
	{
		this.playerPanel.toWaitingState();
	}
	
	public void updateCredit(int credit)
	{
		this.playerPanel.updateCreditTextField(credit);
	}
	
	public void showDealerCards()
	{
		this.playerPanel.showDealerCards();
	}
	
	public void endPlayer()
	{
		JOptionPane.showMessageDialog(this, "Você faliu! A janela será fechada", "JOGADOR FALIU", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
}
