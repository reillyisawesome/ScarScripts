package scripts.scarPlanks;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JPanel {
	
	   public MainGUI() {
		   initComponents();
	    }

	/**
	 * Create the panel.
	 * @return 
	 */
	public void initComponents() {
		setLayout(null);
		
		JCheckBox usePotions = new JCheckBox("Use Potions?");
		usePotions.setBounds(22, 51, 97, 23);
		add(usePotions);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Stamin Potion", "Super Energy Potion", "Energy Potion"}));
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(22, 103, 97, 20);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Type of Potion:");
		lblNewLabel.setBounds(22, 81, 79, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type of Log:");
		lblNewLabel_1.setBounds(22, 137, 97, 14);
		add(lblNewLabel_1);
		
		JComboBox plankBox = new JComboBox();
		plankBox.setMaximumRowCount(4);
		plankBox.setModel(new DefaultComboBoxModel(new String[] {"Regular Plank", "Oak Plank", "Teak Plank", "Mahogany Plank"}));
		plankBox.setBounds(22, 156, 97, 20);
		add(plankBox);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(22, 220, 97, 23);
		add(btnNewButton);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnNewButtonactionPerformed(evt);
            }

			private void btnNewButtonactionPerformed(ActionEvent evt) {
		        if(usePotions.isSelected()){
		            PlankValues.usePotions = true;
		        }else{
		            PlankValues.usePotions = false;
		        }
		        if(comboBox.getSelectedItem() == "Stamina Potion"){
		        	int[] staminapotionIDS = {12631, 12629, 12627, 12625};
		            PlankValues.potionIds = staminapotionIDS;
		        }else if(comboBox.getSelectedItem() == "Super Energy Potion"){
		        	int[] sepotionIDS = {3016, 3018, 3020, 3022};
		            PlankValues.potionIds = sepotionIDS;
		        }else if(comboBox.getSelectedItem() == "Energy Potion") {
		        	int[] epotionIDS = {3016, 3018, 3020, 3022};
		            PlankValues.potionIds = epotionIDS;
		        }else{
		        	PlankValues.potionIds = null;
		        }
		        if(plankBox.getSelectedItem() == "Regular Plank"){
		            PlankValues.interfaceChild = 89;
		            PlankValues.logs = "Logs";
		            PlankValues.planks = "Plank";
		            PlankValues.plankInt = 960;
		        }else if(plankBox.getSelectedItem() == "Oak plank"){
		            PlankValues.interfaceChild = 90;
		            PlankValues.logs = "Oak logs";
		            PlankValues.planks = "Oak plank";
		            PlankValues.plankInt = 8778;
		        }else if(plankBox.getSelectedItem() == "Teak plank"){
		            PlankValues.interfaceChild = 91;
		            PlankValues.logs = "Teak logs";
		            PlankValues.planks = "Teak plank";
		            PlankValues.plankInt = 8780;
		        }else{
		            PlankValues.interfaceChild = 92;
		            PlankValues.logs = "Mahogany logs";
		            PlankValues.planks = "Mahogany plank";
		            PlankValues.plankInt = 8782;
		        }
		        PlankValues.guiComplete = true;
			}
        });

	}

}
