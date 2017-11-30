package pedrofrayha.blackjack.utils;

import javax.swing.JOptionPane;

import pedrofrayha.blackjack.gui.DealerWindow;

public class Dealer extends Person{
	private static Dealer dealerSingleton = null;
	
	private DealerWindow dealerWindow;
	private Hand dealerHand;
	
	private Dealer()
	{
		this.dealerWindow = new DealerWindow();
	}
	
	public int play()
	{
		dealerHand = new Hand();
		Deck deck = Deck.getInstance();
		if(dealerHand.getValue() < 17)
		{
			dealerHand.addCard(deck.drawCard());
		}
		return dealerHand.getValue();
	}
	
	public static Dealer getInstance()
	{
		if(dealerSingleton == null)
		{
			dealerSingleton = new Dealer();
		}
		return dealerSingleton;
	}
	
	public DealerWindow getDealerFrame() 
	{
		return this.dealerWindow;
	}
	
	public void showEndGameMessage()
	{
		JOptionPane.showMessageDialog(dealerWindow, "Todos os jogadores estão sem dinheiro!", "FIM DE JOGO", JOptionPane.INFORMATION_MESSAGE);
	}
}
