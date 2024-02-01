package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class MutationListener implements ActionListener {
	JTextField promtMRate;
	SimulatorComponent simComp;
	
	public MutationListener(JTextField promtMRate, SimulatorComponent simComp) {
		// TODO Auto-generated constructor stub
		this.promtMRate = promtMRate;
		this.simComp = simComp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!this.promtMRate.getText().equals("")) {
			String userInput = promtMRate.getText();
			this.simComp.setPopMutationRateAndMutate(Integer.parseInt(userInput));
			
		}
	}

}
