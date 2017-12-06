package pedrofrayha.blackjack.gui.utils;

import java.util.HashMap;
import java.util.Map;

import pedrofrayha.blackjack.utils.Card;
import pedrofrayha.blackjack.utils.Card.Suit;
import pedrofrayha.blackjack.utils.Card.Value;

public class CardToImageConverter 
{
	private CardToImageConverter()
	{
	}
	
	private static Map<Card, String> cardToImagePathMap = makeCardToImageMap();
	
	private static Map<Card, String> makeCardToImageMap() 
	{
		Map<Card, String> tempMap = new HashMap<Card, String>();
		for(Value value: Value.values())
		{
			for(Suit suit: Suit.values())
			{
				Card card = new Card(suit, value);
				String pathName = makeImagePath(card);
				tempMap.put(card, pathName);
			}
		}
		
		return tempMap;	
	}
	
	private static String getBaseString(Value value)
	{
		if(value.equals(Value.AS))
		{
			return "a";
		}
		else if(value.equals(Value.DOIS))
		{
			return "2";
		}
		else if(value.equals(Value.TRES))
		{
			return "3";
		}
		else if(value.equals(Value.QUATRO))
		{
			return "4";
		}
		else if(value.equals(Value.CINCO))
		{
			return "5";
		}
		else if(value.equals(Value.SEIS))
		{
			return "6";
		}
		else if(value.equals(Value.SETE))
		{
			return "7";
		}
		else if(value.equals(Value.OITO))
		{
			return "8";
		}
		else if(value.equals(Value.NOVE))
		{
			return "9";
		}
		else if(value.equals(Value.DEZ))
		{
			return "t";
		}
		else if(value.equals(Value.VALETE))
		{
			return "j";
		}
		else if(value.equals(Value.DAMA))
		{
			return "q";
		}
		
		return "k";
	}
	
	private static String getSuitString(Suit suit)
	{
		if(suit.equals(Suit.COPAS))
		{
			return "h";
		}
		else if(suit.equals(Suit.ESPADAS))
		{
			return "s";
		}
		else if(suit.equals(Suit.OUROS))
		{
			return "d";
		}
		
		return "c";
	}
	
	private static String makeImagePath(Card card) 
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("./Imagens/");
		builder.append(getBaseString(card.getValue()));
		builder.append(getSuitString(card.getSuit()));
		builder.append(".gif");
		return builder.toString();
	}
	
	public static String convertToImagePath(Card c)
	{
		return cardToImagePathMap.get(c);
	}

	
}
