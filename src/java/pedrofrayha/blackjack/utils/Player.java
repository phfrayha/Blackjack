package pedrofrayha.blackjack.utils;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JOptionPane;

import pedrofrayha.blackjack.gui.PlayerWindow;

public class Player extends Person
{
	private int playerID;
	public static final int INITIAL_CREDIT = 1000;
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
	
	public Player(String[] params)
	{
		this.playerID = Integer.valueOf(params[0]);
		this.playerWindow = new PlayerWindow(this.playerID);
		this.credit = Integer.valueOf(params[1]);
		this.numBuyouts = Integer.valueOf(params[2]);
		this.updateCredit();
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
	
	public void executeBuyout(int value)
	{
		this.numBuyouts++;
		this.credit = value;
		this.updateCredit();
	}
	
	public boolean hasSetBet()
	{
		return this.hasBet;
	}
	
	public void stand()
	{
		this.hasStood = true;
	}
	
	public void requestCard()
	{
		Deck deck = Deck.getInstance();
		this.hand.addCard(deck.drawCard());
	}
	
	public int play()
	{
		this.hand = new Hand();
		this.hasStood = false;
		this.playerWindow.toPlayingState();
		while(!this.hasStood && !this.hasBust())
		{
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		this.playerWindow.toWaitingState();
		return this.hand.getValue();
	}
	
	public boolean hasStood()
	{
		return this.hasStood;
	}
	
	public boolean isBroke()
	{
		boolean isPlayerBroke = this.credit == 0;
		if(isPlayerBroke)
		{
			if(this.numBuyouts < 2)
			{
				isPlayerBroke = offerBuyout();
			}
			else
			{
				this.playerWindow.endPlayer();
			}
		}
		return isPlayerBroke;
	}
	
	public void updateCredit()
	{
		this.playerWindow.updateCredit(this.credit);
	}

	private boolean offerBuyout() {
		int ret = JOptionPane.showConfirmDialog(playerWindow, "Deseja executar a compra de creditos?", "VOLTAR AO JOGO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(ret == JOptionPane.YES_OPTION)
		{
			boolean isValidBuyout = false;
			int value = Player.INITIAL_CREDIT / 2;
			while(!isValidBuyout)
			{
				String rawInput = JOptionPane.showInputDialog(playerWindow, "Quantos créditos deseja comprar?", "COMPRA DE CRÉDITOS", JOptionPane.QUESTION_MESSAGE);
				try
				{
					value = Integer.parseInt(rawInput);
					isValidBuyout = value <= (Player.INITIAL_CREDIT / 2);
					if(!isValidBuyout)
					{
						JOptionPane.showMessageDialog(playerWindow, "Valor de recompra acima do limite de " + (Player.INITIAL_CREDIT / 2), "VALOR INVÁLIDO", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(playerWindow, "Formato inválido para créditos", "FORMATO INVÁLIDO", JOptionPane.ERROR_MESSAGE);
				}
			}
			this.executeBuyout(value);
			return false;
		}
		else
		{
			this.playerWindow.endPlayer();
		}
		return true;
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
		this.betAmount = bet;
		this.playerWindow.toWaitingState();
	}
	
	public void resetStatus()
	{
		this.hasBet = false;
		this.hasStood = false;
		this.playerWindow.toBettingState();
	}
	
	public void saveState(Writer writer)
	throws IOException
	{
		writer.write(this.playerID + ";" + this.credit + ";" + this.numBuyouts + "\n");
	}
	
}
