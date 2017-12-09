package pedrofrayha.blackjack.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import pedrofrayha.blackjack.BlackjackLauncher;

public class DealerButtonClickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equalsIgnoreCase("EndGameCommand"))
		{
			System.exit(0);
		}
		else if(command.equalsIgnoreCase("SaveStateCommand"))
		{
			try {
				BlackjackLauncher.saveState();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(command.equalsIgnoreCase("NewRoundCommand"))
		{
			BlackjackLauncher.startNewRound();
		}
	}

}
