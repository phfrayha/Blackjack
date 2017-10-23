package pedrofrayha.blackjack.utils;

public class Card 
{
	public enum Suit
	{
		COPAS,
		PAUS,
		ESPADAS,
		OUROS,
	}
	
	public enum Value
	{
		AS,
		DOIS,
		TRES,
		QUATRO,
		CINCO,
		SEIS,
		SETE,
		OITO,
		NOVE,
		DEZ,
		VALETE,
		DAMA,
		REI
	}
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	public Suit getSuit()
	{
		return this.suit;
	}
	
	public Value getValue()
	{
		return this.value;
	}
}
