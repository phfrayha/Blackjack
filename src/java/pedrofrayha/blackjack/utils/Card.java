package pedrofrayha.blackjack.utils;

public class Card 
{
	public enum CardValue
	{
		AS_DE_COPAS,
		DOIS_DE_COPAS,
		TRES_DE_COPAS,
		QUATRO_DE_COPAS,
		CINCO_DE_COPAS,
		SEIS_DE_COPAS,
		SETE_DE_COPAS,
		OITO_DE_COPAS,
		NOVE_DE_COPAS,
		DEZ_DE_COPAS,
		VALETE_DE_COPAS,
		DAMA_DE_COPAS,
		REI_DE_COPAS,
		AS_DE_PAUS,
		DOIS_DE_PAUS,
		TRES_DE_PAUS,
		QUATRO_DE_PAUS,
		CINCO_DE_PAUS,
		SEIS_DE_PAUS,
		SETE_DE_PAUS,
		OITO_DE_PAUS,
		NOVE_DE_PAUS,
		DEZ_DE_PAUS,
		VALETE_DE_PAUS,
		DAMA_DE_PAUS,
		REI_DE_PAUS,
		AS_DE_ESPADAS,
		DOIS_DE_ESPADAS,
		TRES_DE_ESPADAS,
		QUATRO_DE_ESPADAS,
		CINCO_DE_ESPADAS,
		SEIS_DE_ESPADAS,
		SETE_DE_ESPADAS,
		OITO_DE_ESPADAS,
		NOVE_DE_ESPADAS,
		DEZ_DE_ESPADAS,
		VALETE_DE_ESPADAS,
		DAMA_DE_ESPADAS,
		REI_DE_ESPADAS,
		AS_DE_OUROS,
		DOIS_DE_OUROS,
		TRES_DE_OUROS,
		QUATRO_DE_OUROS,
		CINCO_DE_OUROS,
		SEIS_DE_OUROS,
		SETE_DE_OUROS,
		OITO_DE_OUROS,
		NOVE_DE_OUROS,
		DEZ_DE_OUROS,
		VALETE_DE_OUROS,
		DAMA_DE_OUROS,
		REI_DE_OUROS,
	}
	
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
		REI,
	}
	
	private Suit suit;
	private Value value;
	
	@Override
	public String toString()
	{
		return this.value + " DE " + this.suit;
	}
	
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
	
	public int getNumberValue()
	{
		int number;
		if(this.value == Value.AS)
		{
			number = 11;
		}
		else if(this.value == Value.REI || this.value == Value.DAMA || this.value == Value.VALETE || this.value == Value.DEZ)
		{
			number = 10;
		}
		else if(this.value == Value.NOVE)
		{
			number = 9;
		}
		else if(this.value == Value.OITO)
		{
			number = 8;
		}
		else if(this.value == Value.SETE)
		{
			number = 7;
		}
		else if(this.value == Value.SEIS)
		{
			number = 6;
		}
		else if(this.value == Value.CINCO)
		{
			number = 5;
		}
		else if(this.value == Value.QUATRO)
		{
			number = 4;
		}
		else if(this.value == Value.TRES)
		{
			number = 3;
		}
		else
		{
			number = 2;
		}
		
		return number;
	}
	
}
