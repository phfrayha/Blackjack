package pedrofrayha.blackjack.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pedrofrayha.blackjack.utils.Card.Suit;
import pedrofrayha.blackjack.utils.Card.Value;

public class Deck
{	
	private static int NUMBER_OF_CARDS = 52;
	
	private List<Card> cards;
	
	private static Deck deck = null;
	
	private Deck()
	{
		this.cards = new ArrayList<Card>(NUMBER_OF_CARDS);
		this.shuffleCards();
	}
	
	public static Deck getInstance()
	{
		if(deck == null)
		{
			deck = new Deck();
		}
		
		return deck;
	}
	
	public void shuffleCards()
	{
		for(int i=0; i<NUMBER_OF_CARDS;i++)
		{
			this.cards.add(null);
		}
		
		for(Value value: Value.values())
		{
			for(Suit suit : Suit.values())
			{
				Random rand = new Random();
				int randomPos = Math.abs(rand.nextInt()%NUMBER_OF_CARDS);
				while(this.cards.get(randomPos)!=null)
				{
					randomPos = Math.abs(rand.nextInt()%NUMBER_OF_CARDS);
				}
				Card card = new Card(suit, value);
				this.cards.set(randomPos, card);
			}
		}
	}
	
	public Card drawCard()
	{
		if(this.cards.size()==0)
		{
			System.out.println("Baralho vazio!");
		}
		return this.cards.remove(0);
	}
}
