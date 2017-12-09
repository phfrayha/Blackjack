package pedrofrayha.blackjack.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pedrofrayha.blackjack.actions.PlayerButtonClickListener;
import pedrofrayha.blackjack.gui.utils.CardToImageConverter;
import pedrofrayha.blackjack.utils.Card;
import pedrofrayha.blackjack.utils.Dealer;
import pedrofrayha.blackjack.utils.Hand;
import pedrofrayha.blackjack.utils.Player;

public class PlayerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CHIP_BASE_X_POSITION = 0;
	private static final int CHIP_BASE_Y_POSITION = 460;
	
	private static final int PLAYER_CARD_BASE_X_POSITION = 300;
	private static final int PLAYER_CARD_BASE_Y_POSITION = 400;
	
	private boolean isPreview;
	
	private static final int DEALER_CARD_BASE_X_POSITION = 300;
	private static final int DEALER_CARD_BASE_Y_POSITION = 200;
	
	private static final Font DEFAULT_FONT = new Font("TimesRoman", Font.PLAIN, 20);
	
	private int id;
	
	private JTextField currentBetAmountField;
	private JButton betButton;
	private JButton resetBetButton;
	private JButton hitButton;
	private JButton standButton;
	private JTextField currentCreditField;
	
	private JFrame parentFrame;
	
	private Hand playerCards;
	
	public PlayerPanel(int id)
	{
		super();
		
		this.isPreview = true;
		
		playerCards = null;
		
		this.id = id;
		
		this.addMouseListener(new PlayerChipClickListener());
		
		currentBetAmountField = new JTextField(10);
		currentBetAmountField.setEditable(false);
		this.resetBetAmount();
		
		JLabel currentBetLabel = new JLabel("Aposta: ");
		Box dummyBox = Box.createHorizontalBox();
		dummyBox.add(currentBetLabel);
		dummyBox.add(currentBetAmountField);
		
		betButton = new JButton("Set Bet");
		betButton.setActionCommand("SetBetCommand");
		betButton.addActionListener(new PlayerButtonClickListener());
		
		resetBetButton = new JButton("Reset Bet");
		resetBetButton.setActionCommand("ResetBetCommand");
		resetBetButton.addActionListener(new PlayerButtonClickListener());
		
		JPanel betPanel = new JPanel(new GridLayout(3,1));
		betPanel.add(dummyBox);
		betPanel.add(betButton);
		betPanel.add(resetBetButton);
		betPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		betPanel.setVisible(true);
		
		hitButton = new JButton("Hit");
		hitButton.setToolTipText("Pedir nova carta");
		hitButton.setEnabled(false);
		hitButton.setActionCommand("HitCommand");
		hitButton.addActionListener(new PlayerButtonClickListener());
		
		standButton = new JButton("Stand");
		standButton.setToolTipText("Encerrar jogada");
		standButton.setEnabled(false);
		standButton.setActionCommand("StandCommand");
		standButton.addActionListener(new PlayerButtonClickListener());
		
		JPanel playPanel = new JPanel(new GridLayout(2,1));
		playPanel.add(hitButton);
		playPanel.add(standButton);
		playPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		playPanel.setVisible(true);
		
		currentCreditField = new JTextField(10);
		currentCreditField.setEditable(false);
		currentCreditField.setText(Integer.toString(Player.INITIAL_CREDIT));
		JLabel currentCreditLabel = new JLabel("Créditos restantes: ");
		currentCreditLabel.setLabelFor(currentCreditField);
		
		JPanel creditPanel = new JPanel(new GridLayout(2,1));
		creditPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		creditPanel.add(currentCreditLabel);
		creditPanel.add(currentCreditField);
		
		JPanel dummyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		dummyPanel.add(playPanel);
		dummyPanel.add(betPanel);
		dummyPanel.add(creditPanel);
				
		this.add(dummyPanel);
		
		parentFrame = new JFrame("Jogador " + (id + 1));
		parentFrame.setVisible(true);
		parentFrame.setSize(756, 600);
		parentFrame.setResizable(false);
		parentFrame.getContentPane().add(this);
		
		repaint();
	}
	
	public void resetBetAmount()
	{
		this.currentBetAmountField.setText("0");
	}
	
	public int getBetAmount()
	{
		return Integer.parseInt(this.currentBetAmountField.getText());
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try
		{
			Image background = ImageIO.read(new File("./Imagens/blackjackBKG_Player.png"));
			g.drawImage(background, 0, 0, null);
			
			Image chip1 = ImageIO.read(new File("./Imagens/ficha 1$.png"));
			g.drawImage(chip1, CHIP_BASE_X_POSITION, CHIP_BASE_Y_POSITION, null);
			
			Image chip5 = ImageIO.read(new File("./Imagens/ficha 5$.png"));
			g.drawImage(chip5, CHIP_BASE_X_POSITION + 60, CHIP_BASE_Y_POSITION, null);

			Image chip10 = ImageIO.read(new File("./Imagens/ficha 10$.png"));
			g.drawImage(chip10, CHIP_BASE_X_POSITION + 120, CHIP_BASE_Y_POSITION, null);
			
			Image chip20 = ImageIO.read(new File("./Imagens/ficha 20$.png"));
			g.drawImage(chip20, CHIP_BASE_X_POSITION, CHIP_BASE_Y_POSITION + 60, null);
			
			Image chip50 = ImageIO.read(new File("./Imagens/ficha 50$.png"));
			g.drawImage(chip50, CHIP_BASE_X_POSITION + 60, CHIP_BASE_Y_POSITION + 60, null);
			
			Image chip100 = ImageIO.read(new File("./Imagens/ficha 100$.png"));
			g.drawImage(chip100, CHIP_BASE_X_POSITION + 120, CHIP_BASE_Y_POSITION + 60, null);
			
			g.setColor(Color.WHITE);
			g.setFont(DEFAULT_FONT);
			
			if(isPreview)
			{
				Image previewImage = ImageIO.read(new File("./Imagens/deck1.gif"));
				g.drawImage(previewImage, DEALER_CARD_BASE_X_POSITION, DEALER_CARD_BASE_Y_POSITION, null);
				
				Dealer dealer = Dealer.getInstance();
				String imagePath;
				while(dealer.getHand() == null)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
						System.exit(1);
					}
				}
				
				final Card card = Dealer.getInstance().getPreviewCard();
				imagePath = CardToImageConverter.convertToImagePath(card);
				Image cardImage = ImageIO.read(new File(imagePath));
				g.drawImage(cardImage, DEALER_CARD_BASE_X_POSITION + 30, DEALER_CARD_BASE_Y_POSITION, null);
			
				
			}
			else
			{
				int rightShift = 0;
				Dealer dealer = Dealer.getInstance();
				
				while(dealer.getHand() == null)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
						System.exit(1);
					}
				}
				
				Hand dealerCards = dealer.getHand();
				for(Card card: dealerCards.getCards())
				{
					final String imagePath = CardToImageConverter.convertToImagePath(card);
					Image cardImage = ImageIO.read(new File(imagePath));
					g.drawImage(cardImage, DEALER_CARD_BASE_X_POSITION + rightShift, DEALER_CARD_BASE_Y_POSITION, null);
					rightShift += 30;
				}
				g.drawString("Pontuação da banca: " + dealerCards.getValue(), DEALER_CARD_BASE_X_POSITION - 40, DEALER_CARD_BASE_Y_POSITION + 120);
			}
			
			if(playerCards != null)
			{
				int rightShift = 0;
				for(Card card: playerCards.getCards())
				{
					final String imagePath = CardToImageConverter.convertToImagePath(card);
					Image cardImage = ImageIO.read(new File(imagePath));
					g.drawImage(cardImage, PLAYER_CARD_BASE_X_POSITION + rightShift, PLAYER_CARD_BASE_Y_POSITION, null);
					rightShift += 30;
				}
				g.drawString("Pontuação da mão: " + playerCards.getValue(), PLAYER_CARD_BASE_X_POSITION - 40, PLAYER_CARD_BASE_Y_POSITION + 120);
			}
			
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		this.setVisible(true);
	}
	
	public void incrementBetAmount(int valueToAdd)
	{
		int currentBetAmount = Integer.parseInt(this.currentBetAmountField.getText());
		currentBetAmount += valueToAdd;
		this.currentBetAmountField.setText(Integer.toString(currentBetAmount));
	}
	
	public void toPlayingState(Hand cards)
	{
		this.betButton.setEnabled(false);
		this.resetBetButton.setEnabled(false);
		this.hitButton.setEnabled(true);
		this.standButton.setEnabled(true);
		this.playerCards = cards;
		this.repaint();
	}
	
	public void refresh()
	{
		this.repaint();
	}
	
	public void toBettingState()
	{
		this.betButton.setEnabled(true);
		this.resetBetButton.setEnabled(true);
		this.hitButton.setEnabled(false);
		this.standButton.setEnabled(false);
		this.resetBetAmount();
		this.playerCards = null;
		this.isPreview = true;
		this.repaint();
	}
	
	public void setPreview(boolean flag)
	{
		this.isPreview = flag;
	}
	
	public void toWaitingState()
	{
		this.betButton.setEnabled(false);
		this.resetBetButton.setEnabled(false);
		this.hitButton.setEnabled(false);
		this.standButton.setEnabled(false);
		this.repaint();
	}
	
	public void showDealerCards()
	{
		this.isPreview = false;
		this.repaint();
	}

	public void updateCredit(int currentCredit)
	{
		this.currentCreditField.setText(Integer.toString(currentCredit));
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void endPlayer()
	{
		JOptionPane.showMessageDialog(this, "Você faliu! A janela será fechada", "JOGADOR FALIU", JOptionPane.INFORMATION_MESSAGE);
		this.parentFrame.dispose();
	}
	
	private class PlayerChipClickListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(PlayerPanel.this.betButton.isEnabled())
			{
				int valueToAdd = 0;
				Point p = e.getPoint();
				
				int clickX = p.x;
				int clickY = p.y;
				
				if((CHIP_BASE_X_POSITION <= clickX) && (clickX < CHIP_BASE_X_POSITION + 60))
				{
					if((CHIP_BASE_Y_POSITION <= clickY) && (clickY < CHIP_BASE_Y_POSITION + 60))
					{
						valueToAdd = 1;
					}
					else if((CHIP_BASE_Y_POSITION + 60 <= clickY) && (clickY <= CHIP_BASE_Y_POSITION + 120))
					{
						valueToAdd = 20;
					}
				}
				else if((CHIP_BASE_X_POSITION + 60 <= clickX) && (clickX < CHIP_BASE_X_POSITION + 120))
				{
					if((CHIP_BASE_Y_POSITION <= clickY) && (clickY < CHIP_BASE_Y_POSITION + 60))
					{
						valueToAdd = 5;
					}
					else if((CHIP_BASE_Y_POSITION + 60 <= clickY) && (clickY <= CHIP_BASE_Y_POSITION + 120))
					{
						valueToAdd = 50;
					}
				}
				else if((CHIP_BASE_X_POSITION + 120 <= clickX) && (clickX < CHIP_BASE_X_POSITION + 180))
				{
					if((CHIP_BASE_Y_POSITION <= clickY) && (clickY < CHIP_BASE_Y_POSITION + 60))
					{
						valueToAdd = 10;
					}
					else if((CHIP_BASE_Y_POSITION + 60 <= clickY) && (clickY <= CHIP_BASE_Y_POSITION + 120))
					{
						valueToAdd = 100;
					}
				}
				
				if(valueToAdd != 0)
				{
					incrementBetAmount(valueToAdd);
				}
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {			
		}

		@Override
		public void mouseEntered(MouseEvent e) {			
		}

		@Override
		public void mouseExited(MouseEvent e) {			
		}
		
	}
}
