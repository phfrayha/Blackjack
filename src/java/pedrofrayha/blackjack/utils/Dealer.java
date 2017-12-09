package pedrofrayha.blackjack.utils;

import javax.swing.JOptionPane;

import pedrofrayha.blackjack.BlackjackLauncher;
import pedrofrayha.blackjack.gui.DealerPanel;

public class Dealer extends Person{
	private static Dealer dealerSingleton = null;
	
	private DealerPanel dealerWindow;
	
	private Dealer()
	{
		this.dealerWindow = new DealerPanel();
	}
	
	public void enableStartNewRound()
	{
		this.dealerWindow.enableStartNewRound();
	}
	
	public void disableStartNewRound()
	{
		this.dealerWindow.disableStartNewRound();
	}
	
	public void setCurrentPlayer(int id)
	{
		this.dealerWindow.showCurrentPlayer(id);
	}
	
	public void setUpDealer()
	{
		this.hand = new Hand();
		this.dealerWindow.toPlayingState(this.hand);
	}
	
	public Card getPreviewCard()
	{
		return this.hand.getFirstCard();
	}
	
	public int play()
	{
		setCurrentPlayer(-1);
		Deck deck = Deck.getInstance();
		while(hand.getValue() < 17)
		{
			hand.addCard(deck.drawCard());
			BlackjackLauncher.refreshUI();
		}
		return hand.getValue();
	}
	
	public static Dealer getInstance()
	{
		if(dealerSingleton == null)
		{
			dealerSingleton = new Dealer();
		}
		return dealerSingleton;
	}
	
	public DealerPanel getDealerFrame() 
	{
		return this.dealerWindow;
	}
	
	public void showEndGameMessage()
	{
		JOptionPane.showMessageDialog(dealerWindow, "Todos os jogadores estão sem dinheiro!", "FIM DE JOGO", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}
