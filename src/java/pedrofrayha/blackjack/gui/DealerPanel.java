package pedrofrayha.blackjack.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pedrofrayha.blackjack.actions.DealerButtonClickListener;
import pedrofrayha.blackjack.gui.utils.CardToImageConverter;
import pedrofrayha.blackjack.utils.Card;
import pedrofrayha.blackjack.utils.Hand;

public class DealerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Font DEFAULT_FONT = new Font("TimesRoman", Font.PLAIN, 20);
	
	private static final int DEFAULT_CARD_X_POSITION = 400;
	private static final int DEFAULT_CARD_Y_POSITION = 300;
	
	private JButton saveStateButton;
	private JButton endGameButton;
	private JButton startNewRoundButton;
	
	private Hand dealerHand;
	
	private static final int DEFAULT_BUTTON_WIDTH = 200;
	private static final int DEFAULT_BUTTON_HEIGHT = 40;
	
	private int currentPlayerID = -1;
	
	public void toPlayingState(Hand cards)
	{
		this.dealerHand = cards;
		this.repaint();
	}

	public DealerPanel()
	{
		super(new BorderLayout());
				
		this.saveStateButton = new JButton("Salvar jogo");
		this.saveStateButton.setActionCommand("SaveStateCommand");
		this.saveStateButton.addActionListener(new DealerButtonClickListener());
		this.saveStateButton.setSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);
		
		this.endGameButton = new JButton("Encerrar jogo");
		this.endGameButton.setActionCommand("EndGameCommand");
		this.endGameButton.addActionListener(new DealerButtonClickListener());
		this.endGameButton.setSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);;
		
		this.startNewRoundButton = new JButton("Começar nova rodada");
		this.startNewRoundButton.setActionCommand("NewRoundCommand");
		this.startNewRoundButton.addActionListener(new DealerButtonClickListener());
		this.startNewRoundButton.setSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);
		this.disableStartNewRound();
		
		Box dummyBox = Box.createVerticalBox();
		dummyBox.add(startNewRoundButton);
		dummyBox.add(saveStateButton);
		dummyBox.add(endGameButton);
		
		this.add(dummyBox, BorderLayout.SOUTH);
		
		JFrame parentFrame = new JFrame("Dealer");
		parentFrame.setSize(850, 800);
		parentFrame.getContentPane().add(this);
		parentFrame.setVisible(true);
		parentFrame.setResizable(false);
		this.setVisible(true);
		
		repaint();
	}
	
	public void enableStartNewRound()
	{
		this.startNewRoundButton.setEnabled(true);
	}
	
	public void disableStartNewRound()
	{
		this.startNewRoundButton.setEnabled(false);
	}
	
	public void showCurrentPlayer(int id)
	{
		this.currentPlayerID = id;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try
		{
			Image background = ImageIO.read(new File("./Imagens/blackjackBKG.png"));
			g.drawImage(background, 0, 0, null);
			
			g.setColor(Color.WHITE);
			g.setFont(DEFAULT_FONT);
			
			if(this.currentPlayerID != -1)
			{
				int positionX = (this.getWidth() / 2) - 80;
				int positionY = this.getHeight() - 100;
				g.drawString("Vez do jogador " + (this.currentPlayerID + 1) + " jogar", positionX, positionY);
			}
			
			if(this.dealerHand != null)
			{
				int rightShift = 0;
				for(Card card: dealerHand.getCards())
				{
					String path = CardToImageConverter.convertToImagePath(card);
					Image cardImage = ImageIO.read(new File(path));
					g.drawImage(cardImage, DEFAULT_CARD_X_POSITION + rightShift, DEFAULT_CARD_Y_POSITION, null);
					rightShift+=30;
				}
				g.drawString("Pontuação da banca: " + this.dealerHand.getValue(), DEFAULT_CARD_X_POSITION - 30, DEFAULT_CARD_Y_POSITION + 120);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		this.setVisible(true);
	}

}
