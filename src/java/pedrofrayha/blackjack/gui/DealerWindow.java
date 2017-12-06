package pedrofrayha.blackjack.gui;

import javax.swing.JFrame;

public class DealerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DealerPanel dealerPanel;
	
	public DealerWindow()
	{
		super("Dealer");
		this.setSize(850, 800);
		initializeDealerUI();
		this.setVisible(true);
		this.setResizable(false);
		this.dealerPanel.setVisible(true);
	}
	
	private void initializeDealerUI()
	{
		this.dealerPanel = new DealerPanel();
		this.getContentPane().add(this.dealerPanel);
	}

}
