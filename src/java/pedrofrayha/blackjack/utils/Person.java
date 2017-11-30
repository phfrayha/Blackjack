package pedrofrayha.blackjack.utils;

public class Person {
	protected Hand hand;
	
	public boolean hasBlackJack()
	{
		return this.hand.isBlackJack();
	}
}
