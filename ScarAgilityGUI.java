package scripts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ScarAgilityGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField foodIDField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScarAgilityGUI frame = new ScarAgilityGUI();
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
	public ScarAgilityGUI() {
		setTitle("Scar Agility");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSlider slider = new JSlider();
		slider.setBorder(null);
		slider.setValue(130);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(10);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMaximum(180);
		slider.setMinimum(100);
		
		JLabel lblMouseSpeed = new JLabel("Mouse Speed");
		lblMouseSpeed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JCheckBox foodCheck = new JCheckBox("Eat Food?");
		foodCheck.setSelected(false);
		foodCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		foodCheck.setHorizontalAlignment(SwingConstants.LEFT);
		
		JCheckBox chckbxCollectMarksOf = new JCheckBox("Collect Marks of Grace?");
		chckbxCollectMarksOf.setSelected(false);
		chckbxCollectMarksOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblFoodId = new JLabel("Food ID:");
		lblFoodId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		foodIDField = new JTextField();
		foodIDField.setColumns(10);
		
		JLabel lblScarAgilitySettings = new JLabel("Scar Agility Settings");
		lblScarAgilitySettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblScarAgilitySettings.setFont(new Font("Tahoma", Font.PLAIN, 29));
		
		JSeparator separator = new JSeparator();
		
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblScarAgilitySettings, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(84)
									.addComponent(lblMouseSpeed, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(slider, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxCollectMarksOf, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
								.addComponent(foodCheck, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFoodId, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(foodIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(70)
							.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(5, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblScarAgilitySettings, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblMouseSpeed, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFoodId, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(foodIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(foodCheck, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxCollectMarksOf, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
