package pedrofrayha.blackjack.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import pedrofrayha.blackjack.actions.DealerButtonClickListener;

public class DealerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton saveStateButton;
	private JButton endGameButton;

	public DealerPanel()
	{
		super(new BorderLayout());
		
		this.saveStateButton = new JButton("Salvar jogo");
		this.saveStateButton.setActionCommand("SaveStateCommand");
		this.saveStateButton.addActionListener(new DealerButtonClickListener());
		
		this.endGameButton = new JButton("Encerrar jogo");
		this.endGameButton.setActionCommand("EndGameCommand");
		this.endGameButton.addActionListener(new DealerButtonClickListener());
		
		Box dummyBox = Box.createHorizontalBox();
		dummyBox.add(saveStateButton);
		dummyBox.add(endGameButton);
		
		this.add(dummyBox, BorderLayout.SOUTH);
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try
		{
			Image background = ImageIO.read(new File("./Imagens/blackjackBKG.png"));
			g.drawImage(background, 0, 0, null);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		this.setVisible(true);
	}

}
