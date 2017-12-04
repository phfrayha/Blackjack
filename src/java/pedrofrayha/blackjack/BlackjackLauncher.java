package pedrofrayha.blackjack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import pedrofrayha.blackjack.utils.Dealer;
import pedrofrayha.blackjack.utils.Deck;
import pedrofrayha.blackjack.utils.Player;

public class BlackjackLauncher {
	
	private static JFrame initialFrame;
	private static JComboBox<Integer> numberOfPlayersBox;
	
	private static Dealer dealer;
	private static final List<Player> players = new ArrayList<Player>(4);
	
	private static boolean haveAllPlayersBet()
	{
		for(Player player:players)
		{
			if(!player.hasSetBet())
			{
				return false;
			}
		}
		return true;
	}
	
	private static boolean areAllPlayersBroke()
	{
		boolean areAllPlayersBroke = true;
		List<Player> playersToEliminate = new ArrayList<Player>();
		for(Player player:players)
		{
			if(!player.isBroke())
			{
				areAllPlayersBroke = false;
			}
			else
			{
				playersToEliminate.add(player);
			}
		}
		players.removeAll(playersToEliminate);
		return areAllPlayersBroke;
	}
	
	private static void showInitialWindow()
	{
		initialFrame = new JFrame("Escolha o número de jogadores");
		initialFrame.setSize(600, 80);
		initialFrame.setLayout(new GridLayout(2, 1));

		numberOfPlayersBox = new JComboBox<Integer>();
		numberOfPlayersBox.addItem(1);
		numberOfPlayersBox.addItem(2);
		numberOfPlayersBox.addItem(3);
		numberOfPlayersBox.addItem(4);

		numberOfPlayersBox.setSelectedIndex(0);

		JLabel numberOfPlayersLabel = new JLabel("Quantos jogadores irão jogar?");

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("PlayerNumbersOK");
		okButton.addActionListener(new ButtonClickListener());
		
		Box dummyBox = Box.createHorizontalBox();
		dummyBox.add(numberOfPlayersLabel);
		dummyBox.add(numberOfPlayersBox);
		dummyBox.add(okButton);
		
		JButton loadSavedStateButton = new JButton("Carregar jogo anterior");
		loadSavedStateButton.setActionCommand("LoadSavedStateCommand");
		loadSavedStateButton.addActionListener(new ButtonClickListener());

//		initialFrame.add(numberOfPlayersLabel);
//		initialFrame.add(numberOfPlayersBox);
//		initialFrame.add(okButton);
		initialFrame.add(dummyBox);
		initialFrame.add(loadSavedStateButton);

		initialFrame.setVisible(true);

		initialFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}
	
	private static boolean havePlayersBeenCreated = false;

	public static void main(String[] args)
	{		
		showInitialWindow();
				
		while(!havePlayersBeenCreated)
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
				
		while(!areAllPlayersBroke())
		{
			while(!haveAllPlayersBet())
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					System.out.println(e.getMessage());
					System.exit(1);
				}
			}
			final int[] playerHandValues = new int[4];
			int counter = 0;
			for(Player player: players)
			{
				playerHandValues[counter] = player.play();
				counter++;
			}
			final int dealerValue = dealer.play();
			final boolean dealerHasBlackJack = dealer.hasBlackJack();
			final boolean hasDealerBust = dealer.hasBust();
			
			for(Player player: players)
			{
				final boolean hasPlayerBust = player.hasBust();
				if(player.hasBlackJack())
				{
					if(dealerHasBlackJack)
					{
						player.addCredit(player.getBetAmount());
					}
					else
					{
						int payout = player.getBetAmount() / 2;
						payout*=5;
						player.addCredit(payout);
					}
				}
				else if(hasDealerBust)
				{
					player.addCredit(2 * player.getBetAmount());
				}
				else if(!dealerHasBlackJack)
				{
						if(!hasPlayerBust)
						{
							if(playerHandValues[player.getPlayerID()] > dealerValue)
							{
								player.addCredit(2 * player.getBetAmount());
							}
							else if (playerHandValues[player.getPlayerID()] == dealerValue)
							{
								player.addCredit(player.getBetAmount());
							}
						}
				}
				player.resetStatus();
				player.updateCredit();
			}
			Deck.getInstance().shuffleCards();
		}
		dealer.showEndGameMessage();
	}
	
	public static void saveState() 
			throws IOException
	{
		JFileChooser fc = new JFileChooser();
		int result = fc.showSaveDialog(dealer.getDealerFrame());
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			int tries = 0;
			boolean hasWritten = false;
			while(tries < 3)
			{
				FileWriter writer = null;
				try
				{
					writer = new FileWriter(file);
					writer.write(players.size() + "\n");
					for(Player player: players)
					{
						player.saveState(writer);
					}
					hasWritten = true;
					writer.close();
					break;
				}
				catch(IOException e)
				{
					tries++;
				}
			}
			if(!hasWritten)
			{
				throw new IOException("Tentou salvar estado três vezes e não conseguiu");
			}
		}
		JOptionPane.showMessageDialog(dealer.getDealerFrame(), "Jogo salvo com sucesso!", "JOGO SALVO", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static void initializePlayersAndDealer(int numberOfPlayers)
	{
		dealer = Dealer.getInstance();
		for(int i=0; i < numberOfPlayers; i++)
		{
			Player player = new Player(i);
			players.add(player);
		}
	}
	
	private static void initializePlayersAndDealer(List<Player> playersHolder)
	{
		dealer = Dealer.getInstance();
		players.addAll(playersHolder);
	}
	
	private static void loadSavedState()
	{
		final JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(initialFrame);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			BufferedReader reader;
			try
			{
				reader = new BufferedReader(new FileReader(file));
				String strNumPlayers = reader.readLine();
				int numPlayers = Integer.valueOf(strNumPlayers);
				List<Player> playersHolder = new ArrayList<Player>(numPlayers);
				for(int i=0;i<numPlayers; i++)
				{
					String[] param = reader.readLine().split(";");
					if(!"0".equalsIgnoreCase(param[1]))
					{
						playersHolder.add(new Player(param));
					}
				}
				initializePlayersAndDealer(playersHolder);
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
	}
	
	public static Player getPlayerById(int id)
	{
		for(Player player: players)
		{
			if(player.getPlayerID() == id)
			{
				return player;
			}
		}
		
		return null;
	}
	
	private static class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equalsIgnoreCase("PlayerNumbersOK")) {
				Integer numberOfPlayers = (Integer) numberOfPlayersBox.getSelectedItem();
				initializePlayersAndDealer(numberOfPlayers);
				initialFrame.dispose();
				havePlayersBeenCreated = true;
			}
			else if(command.equalsIgnoreCase("LoadSavedStateCommand"))
			{
				loadSavedState();
				initialFrame.dispose();
				havePlayersBeenCreated = true;
			}
		}
	}

}
