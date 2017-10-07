package view;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class panelAdding extends JPanel {
	private JTextField txtHello;

	/**
	 * Create the panel.
	 */
	public panelAdding() {
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "JPanel title fhfhfhg", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_3);
		
		JTextPane txtpnHello = new JTextPane();
		panel_3.add(txtpnHello);
		txtpnHello.setText("Hello");
		
		JPanel panel = new JPanel();
		panel.setToolTipText("fhff");
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		
		txtHello = new JTextField();
		txtHello.setEditable(false);
		panel_1.add(txtHello);
		txtHello.setText("Hello2");
		txtHello.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_2.add(lblNewLabel);

	}

}
