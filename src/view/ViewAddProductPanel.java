package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Product;

public class ViewAddProductPanel extends JPanel {
	ViewMainFrame viewMainFrame = null;
	private JLabel lblAddProduct;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTextField txtTotalPrice;
	private JButton btnAddProduct;
	private JButton btnCancel;

	ViewAddProductPanel(ViewMainFrame viewMainFrame) {
		this.viewMainFrame = viewMainFrame;
		lblAddProduct = new JLabel("New product: ");
		this.add(lblAddProduct);
		
		txtName = new JTextField();
		txtName.setColumns(8);
		txtName.setBorder(new TitledBorder("Name"));
		this.add(txtName);

		txtPrice = new JTextField();
		txtPrice.setColumns(6);
		txtPrice.setBorder(new TitledBorder("Price"));
		this.add(txtPrice);

		txtQuantity = new JTextField();
		txtQuantity.setColumns(5);
		txtQuantity.setBorder(new TitledBorder("Quantity"));
		this.add(txtQuantity);

		txtTotalPrice = new JTextField();
		txtTotalPrice.setColumns(7);
		txtTotalPrice.setEditable(false);
		txtTotalPrice.setBorder(new TitledBorder("Total price"));
		this.add(txtTotalPrice);

		btnAddProduct = new JButton("Add product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actBbtnAddProduct();
				// hideMe();
			}
		});
		this.add(btnAddProduct);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// this = null;
				hideMe();
			}
		});

		this.add(btnCancel);

		System.out.println("ViewAddProductPanel created");
	}

	private void hideMe() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		txtName.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
	}

	private void actBbtnAddProduct() {
		Product newProduct = new Product();
		newProduct.title = txtName.getText();
		newProduct.price = Double.parseDouble(txtPrice.getText());
		newProduct.total = Integer.parseInt(txtQuantity.getText());
		viewMainFrame.setNewProduct(newProduct);
		viewMainFrame.notifyObservers(this, ".Add new product button clicked");
		if (viewMainFrame.getNewProduct().product_id != 0)
			hideMe();
		viewMainFrame.setNewProduct(null);
	}
}
