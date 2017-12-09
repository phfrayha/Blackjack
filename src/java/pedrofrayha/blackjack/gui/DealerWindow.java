package pedrofrayha.blackjack.gui;

import javax.swing.JFrame;

import pedrofrayha.blackjack.utils.Hand;

public class DealerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DealerPanel dealerPanel;
	
	public DealerWindow()
	{
		super("Dealer");
		this.setSize(850, 800);
		initializeDealerUI();
		this.setVisible(true);
		this.setResizable(false);
		this.dealerPanel.setVisible(true);
	}
	
	public void toPlayingState(Hand cards)
	{
		this.dealerPanel.toPlayingState(cards);
	}
	
	public void showCurrentPlayer(int id)
	{
		this.dealerPanel.showCurrentPlayer(id);
	}
	
	public void enableStartNewRound()
	{
		this.dealerPanel.enableStartNewRound();
	}
	
	public void disableStartNewRound()
	{
		this.dealerPanel.disableStartNewRound();
	}
	
	private void initializeDealerUI()
	{
		this.dealerPanel = new DealerPanel();
		this.getContentPane().add(this.dealerPanel);
	}

}
