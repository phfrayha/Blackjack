package pedrofrayha.blackjack.utils;

public class Person {
	protected Hand hand;
	
	public boolean hasBlackJack()
	{
		return this.hand.isBlackJack();
	}
	
	public boolean hasBust()
	{
		return this.hand.getValue() > 21;
	}
}
