package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Product;
import model.User;

public class ViewAddBuyerPanel extends JPanel {
	ViewMainFrame viewMainFrame = null;
	private JLabel lblNewBuyer;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JButton btnAddBuyer;
	private JButton btnCancel;

	ViewAddBuyerPanel(ViewMainFrame viewMainFrame) {
		this.viewMainFrame = viewMainFrame;
		lblNewBuyer = new JLabel("New buyer:");
		this.add(lblNewBuyer);
		
		txtName = new JTextField();
		txtName.setColumns(8);
		txtName.setBorder(new TitledBorder("Name"));
		this.add(txtName);

		txtEmail = new JTextField();
		txtEmail.setColumns(6);
		txtEmail.setBorder(new TitledBorder("Email"));
		this.add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setColumns(5);
		txtPhone.setBorder(new TitledBorder("Phone"));
		this.add(txtPhone);

		btnAddBuyer = new JButton("Add buyer");
		btnAddBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnAddBuyer();
				// hideMe();
			}
		});
		this.add(btnAddBuyer);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// this = null;
				hideMe();
			}
		});

		this.add(btnCancel);

		System.out.println("ViewAddBuyerPanel created");
	}

	private void hideMe() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		txtName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
	}

	private void actBtnAddBuyer() {
		User newBuyer = new User();
		newBuyer.name = txtName.getText();
		newBuyer.email = txtEmail.getText();
		newBuyer.phone = txtPhone.getText();
		viewMainFrame.setNewBuyer(newBuyer);
		viewMainFrame.notifyObservers(this, ".Add new buyer button clicked");
		if (viewMainFrame.getNewUser().user_id != 0)
			hideMe();
		viewMainFrame.setNewBuyer(null);
	}
}
