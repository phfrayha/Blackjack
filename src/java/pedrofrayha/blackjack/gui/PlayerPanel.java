package pedrofrayha.blackjack.gui;

import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pedrofrayha.blackjack.actions.PlayerButtonClickListener;
import pedrofrayha.blackjack.utils.Player;

public class PlayerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int BASE_X_POSITION = 0;
	private static final int BASE_Y_POSITION = 460;
	
	private int id;
	
	private JTextField currentBetAmountField;
	private JButton betButton;
	private JButton resetBetButton;
	private JButton hitButton;
	private JButton standButton;
	private JTextField currentCreditField;
	
	public PlayerPanel(int id)
	{
		super();
		
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
			g.drawImage(chip1, BASE_X_POSITION, BASE_Y_POSITION, null);
			
			Image chip5 = ImageIO.read(new File("./Imagens/ficha 5$.png"));
			g.drawImage(chip5, BASE_X_POSITION + 60, BASE_Y_POSITION, null);

			Image chip10 = ImageIO.read(new File("./Imagens/ficha 10$.png"));
			g.drawImage(chip10, BASE_X_POSITION + 120, BASE_Y_POSITION, null);
			
			Image chip20 = ImageIO.read(new File("./Imagens/ficha 20$.png"));
			g.drawImage(chip20, BASE_X_POSITION, BASE_Y_POSITION + 60, null);
			
			Image chip50 = ImageIO.read(new File("./Imagens/ficha 50$.png"));
			g.drawImage(chip50, BASE_X_POSITION + 60, BASE_Y_POSITION + 60, null);
			
			Image chip100 = ImageIO.read(new File("./Imagens/ficha 100$.png"));
			g.drawImage(chip100, BASE_X_POSITION + 120, BASE_Y_POSITION + 60, null);
			
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
	
	public void toPlayingState()
	{
		this.betButton.setEnabled(false);
		this.hitButton.setEnabled(true);
		this.standButton.setEnabled(true);
	}
	
	public void toBettingState()
	{
		this.betButton.setEnabled(true);
		this.hitButton.setEnabled(false);
		this.standButton.setEnabled(false);
		this.resetBetAmount();
	}

	public void updateCreditTextField(int currentCredit)
	{
		this.currentCreditField.setText(Integer.toString(currentCredit));
	}
	
	public int getId()
	{
		return this.id;
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
				
				if((BASE_X_POSITION <= clickX) && (clickX < BASE_X_POSITION + 60))
				{
					if((BASE_Y_POSITION <= clickY) && (clickY < BASE_Y_POSITION + 60))
					{
						valueToAdd = 1;
					}
					else if((BASE_Y_POSITION + 60 <= clickY) && (clickY <= BASE_Y_POSITION + 120))
					{
						valueToAdd = 20;
					}
				}
				else if((BASE_X_POSITION + 60 <= clickX) && (clickX < BASE_X_POSITION + 120))
				{
					if((BASE_Y_POSITION <= clickY) && (clickY < BASE_Y_POSITION + 60))
					{
						valueToAdd = 5;
					}
					else if((BASE_Y_POSITION + 60 <= clickY) && (clickY <= BASE_Y_POSITION + 120))
					{
						valueToAdd = 50;
					}
				}
				else if((BASE_X_POSITION + 120 <= clickX) && (clickX < BASE_X_POSITION + 180))
				{
					if((BASE_Y_POSITION <= clickY) && (clickY < BASE_Y_POSITION + 60))
					{
						valueToAdd = 10;
					}
					else if((BASE_Y_POSITION + 60 <= clickY) && (clickY <= BASE_Y_POSITION + 120))
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
