package pedrofrayha.blackjack.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DealerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton saveStateButton;

	public DealerPanel()
	{
		super();
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
