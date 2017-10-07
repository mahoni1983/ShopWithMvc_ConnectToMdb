package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Product;
import model.Sell;
import model.User;

public class ViewAddSalePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ViewMainFrame viewMainFrame = null;
	private JLabel lblNewSale;
	private JTextField txtProductId;
	private JTextField txtBuyerId;
	private JTextField txtCount;
	private JTextField txtDate;
	private JButton btnAddSale;
	private JButton btnCancel;

	ViewAddSalePanel(ViewMainFrame viewMainFrame) {
		this.viewMainFrame = viewMainFrame;
		lblNewSale = new JLabel("New sale:");
		this.add(lblNewSale);
		
		txtProductId = new JTextField();
		txtProductId.setColumns(8);
		txtProductId.setBorder(new TitledBorder("ProductID"));
		this.add(txtProductId);

		txtBuyerId = new JTextField();
		txtBuyerId.setColumns(6);
		txtBuyerId.setBorder(new TitledBorder("BuyerID"));
		this.add(txtBuyerId);

		txtCount = new JTextField();
		txtCount.setColumns(5);
		txtCount.setBorder(new TitledBorder("Count"));
		this.add(txtCount);
		
		txtDate = new JTextField();
		txtDate.setColumns(5);
		txtDate.setBorder(new TitledBorder("Date"));
		this.add(txtDate);

		btnAddSale = new JButton("Add sale");
		btnAddSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBtnAddSale();
				// hideMe();
			}
		});
		this.add(btnAddSale);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// this = null;
				hideMe();
			}
		});

		this.add(btnCancel);

		System.out.println("ViewAddSalePanel created");
	}

	private void hideMe() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		txtProductId.setText("");
		txtBuyerId.setText("");
		txtCount.setText("");
		txtDate.setText("");
	}

	private void actBtnAddSale() {
		Sell newSale = new Sell();
		newSale.product_id = Integer.parseInt(txtProductId.getText());
		newSale.buyer_id = Integer.parseInt(txtBuyerId.getText());
		newSale.count = Integer.parseInt(txtCount.getText());
		newSale.sell_date = txtDate.getText();
	////////	
		viewMainFrame.setNewSale(newSale);
		viewMainFrame.notifyObservers(this, ".Add new sale button clicked");
		if (viewMainFrame.getNewSale().sell_id != 0)
			hideMe();
		viewMainFrame.setNewSale(null);
	}
}
