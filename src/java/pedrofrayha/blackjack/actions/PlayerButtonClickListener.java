package pedrofrayha.blackjack.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pedrofrayha.blackjack.BlackjackLauncher;
import pedrofrayha.blackjack.gui.PlayerPanel;
import pedrofrayha.blackjack.utils.Player;

public class PlayerButtonClickListener implements ActionListener
{
	private PlayerPanel getParentPlayerPanel(ActionEvent e)
	{
		JButton dummyButton = (JButton) e.getSource();
		JPanel dummyPanel1 = (JPanel) dummyButton.getParent();
		JPanel dummyPanel2 = (JPanel) dummyPanel1.getParent();
		PlayerPanel panel = (PlayerPanel) dummyPanel2.getParent();
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		PlayerPanel panel = getParentPlayerPanel(e);
		String command = e.getActionCommand();
		Player player = BlackjackLauncher.getPlayerById(panel.getId());
		
		if(command.equalsIgnoreCase("SetBetCommand"))
		{
			int betValue = panel.getBetAmount();
			try
			{
				player.bet(betValue);
			}
			catch(IllegalArgumentException e1)
			{
				JOptionPane.showMessageDialog(panel, "Valor de aposta maior que crédito disponível", "VALOR DE APOSTA INVÁLIDO", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(command.equalsIgnoreCase("ResetBetCommand"))
		{
			panel.resetBetAmount();
		}
		else if(command.equalsIgnoreCase("HitCommand"))
		{
			player.requestCard();
		}
		else if(command.equalsIgnoreCase("StandCommand"))
		{
			player.stand();
		}
	}
	
}
