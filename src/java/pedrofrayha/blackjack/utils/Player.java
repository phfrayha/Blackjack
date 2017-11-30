package pedrofrayha.blackjack.utils;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JOptionPane;

import pedrofrayha.blackjack.gui.PlayerWindow;

public class Player extends Person
{
	private int playerID;
	private static final int INITIAL_CREDIT = 1000;
	private PlayerWindow playerWindow;
	private int credit;
	private int betAmount;
	private boolean hasBet;
	private boolean hasStood;
	private int numBuyouts;

	public Player(int id)
	{
		this.credit = INITIAL_CREDIT;
		this.playerWindow = new PlayerWindow(id);
		this.playerID = id;
		this.numBuyouts = 0;
	}
	
	public int getPlayerID()
	{
		return this.playerID;
	}
	
	public int getBetAmount()
	{
		return this.betAmount;
	}
	
	public PlayerWindow getPlayerFrame()
	{
		return this.playerWindow;
	}
	
	public void addCredit(int credit)
	{
		this.credit += credit;
	}
	
	public void executeBuyout()
	{
		this.numBuyouts++;
		this.credit = INITIAL_CREDIT / 2;
	}
	
	public boolean hasSetBet()
	{
		return this.hasBet;
	}
	
	public int play()
	{
		this.hand = new Hand();
		this.hasStood = false;
		while(!this.hasStood && this.hand.getValue() < 22)
		{
			//TODO: Implementar interface de hit/stand
		}
		return this.hand.getValue();
	}
	
	public boolean hasStood()
	{
		return this.hasStood;
	}
	
	public boolean isBroke()
	{
		boolean isBroke = this.credit == 0;
		if(isBroke)
		{
			if(this.numBuyouts < 2)
			{
				int ret = JOptionPane.showConfirmDialog(playerWindow, "Deseja executar a compra de creditos?", "VOLTAR AO JOGO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(ret == JOptionPane.YES_OPTION)
				{
					this.executeBuyout();
					isBroke = false;
				}
				else
				{
					this.playerWindow.endPlayer();
				}
			}
			else
			{
				this.playerWindow.endPlayer();
			}
		}
		return isBroke;
	}
	
	public void bet(int bet)
	throws IllegalArgumentException
	{
		if(bet > this.credit)
		{
			throw new IllegalArgumentException();
		}
		this.credit -= bet;
		hasBet = true;
	}
	
	public void saveState(Writer writer)
	throws IOException
	{
		writer.write(this.playerID + ";" + this.credit + "\n");
	}
	
}
