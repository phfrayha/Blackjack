package pedrofrayha.blackjack;

import pedrofrayha.blackjack.utils.Card;
import pedrofrayha.blackjack.utils.Deck;

public class BlackjackLauncher {

	public static void main(String[] args)
	{
		for(int i=0;i<52;i++)
		{
			Card card = Deck.getInstance().drawCard();
			System.out.println(card.toString());
		}
	}

}
