package scripts.scarPlanks;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 186, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JCheckBox usePotions = new JCheckBox("Use Potions");
		contentPane.add(usePotions);
		
		JComboBox potionSelect = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.WEST, usePotions, 0, SpringLayout.WEST, potionSelect);
		sl_contentPane.putConstraint(SpringLayout.EAST, usePotions, 0, SpringLayout.EAST, potionSelect);
		sl_contentPane.putConstraint(SpringLayout.NORTH, potionSelect, 117, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, potionSelect, 26, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, potionSelect, 140, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, potionSelect, 123, SpringLayout.WEST, contentPane);
		potionSelect.setMaximumRowCount(3);
		potionSelect.setModel(new DefaultComboBoxModel(new String[] {"Stamina", "Super Energy", "Energy"}));
		contentPane.add(potionSelect);
		
		JComboBox plankBox = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, plankBox, 173, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, plankBox, 26, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, plankBox, 196, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, plankBox, 123, SpringLayout.WEST, contentPane);
		plankBox.setModel(new DefaultComboBoxModel(new String[] {"Planks", "Oak Planks", "Mahogany Planks", "Teak Planks"}));
		contentPane.add(plankBox);
		
		JLabel lblPotionToUse = new JLabel("Potion to use:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPotionToUse, 92, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPotionToUse, 26, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPotionToUse, 123, SpringLayout.WEST, contentPane);
		lblPotionToUse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPotionToUse.setLabelFor(potionSelect);
		contentPane.add(lblPotionToUse);
		
		JLabel lblSelectPlankTo = new JLabel("Plank to use:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSelectPlankTo, 149, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSelectPlankTo, 26, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSelectPlankTo, 141, SpringLayout.WEST, contentPane);
		lblSelectPlankTo.setLabelFor(plankBox);
		lblSelectPlankTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblSelectPlankTo);
		
		JButton btnNewButton = new JButton("Start");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 228, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 26, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, 123, SpringLayout.WEST, contentPane);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ScarPlanks");
		sl_contentPane.putConstraint(SpringLayout.NORTH, usePotions, 2, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 11, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, 50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 160, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlankValues.guiComplete = true;
			}
		});
		
		usePotions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(usePotions.isSelected()){
		            PlankValues.usePotions = true;
		        }else{
		            PlankValues.usePotions = false;
		        }
			}
		});
		
		potionSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(potionSelect.getSelectedItem() == "Stamina"){
		        	int[] staminapotionIDS = {12631, 12629, 12627, 12625};
		            PlankValues.potionIds = staminapotionIDS;
		        }else if(potionSelect.getSelectedItem() == "Super Energy"){
		        	int[] sepotionIDS = {3016, 3018, 3020, 3022};
		            PlankValues.potionIds = sepotionIDS;
		        }else{
		        	int[] epotionIDS = {3016, 3018, 3020, 3022};
		            PlankValues.potionIds = epotionIDS;
		        }
			}
		});
		
		plankBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(plankBox.getSelectedItem() == "Planks"){
		            PlankValues.interfaceChild = 89;
		            PlankValues.logs = "Logs";
		            PlankValues.planks = "Plank";
		            PlankValues.plankInt = 960;
		        }else if(plankBox.getSelectedItem() == "Oak planks"){
		            PlankValues.interfaceChild = 90;
		            PlankValues.logs = "Oak logs";
		            PlankValues.planks = "Oak plank";
		            PlankValues.plankInt = 8778;
		        }else if(plankBox.getSelectedItem() == "Teak planks"){
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
			}
		});
		
	}
}
