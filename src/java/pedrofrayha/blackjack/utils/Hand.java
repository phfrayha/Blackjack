package pedrofrayha.blackjack.utils;

import java.util.LinkedList;
import java.util.List;

public class Hand {
	private List<Card> hand;
	private int value;
	
	public Hand()
	{
		this.hand = new LinkedList<Card>();
		Deck deck = Deck.getInstance();
		addCard(deck.drawCard());
		addCard(deck.drawCard());
	}
	
	public List<Card> getCards()
	{
		return this.hand;
	}
	
	public Card getFirstCard()
	{
		return this.hand.get(0);
	}
	
	public void addCard(Card card)
	{
		this.hand.add(card);
	}
	
	public boolean isBlackJack()
	{
		if(this.getValue() == 21 && this.hand.size() == 2)
		{
			return true;
		}
		
		return false;
	}
	
	public int getValue()
	{
		this.value = 0;
		for(Card card:hand)
		{
			this.value+=card.getNumberValue();
		}
		return value;
	}
}
