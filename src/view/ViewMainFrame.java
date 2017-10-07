package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Product;
import model.Sell;
import model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMainFrame {
	private JFrame mainFrame;
	private JPanel panelMain;
	private JPanel panelForTables;
	private JTabbedPane tabbedPane;
	private JButton btnAddProduct;
	private JButton btnAddBuyer;
	private JButton btnAddSale;
	private JButton btnAbout;
	private JScrollPane scrollPaneProducts;
	private JTable tableProducts;
	private JScrollPane scrollPaneBuyers;
	private JTable tableBuyers;
	private JScrollPane scrollPaneSales;
	private JTable tableSales;

	private ViewAddProductPanel viewAddProductPanel;
	private ViewAddBuyerPanel viewAddBuyerPanel;
	private ViewAddSalePanel viewAddSalePanel;

	private ArrayList<Observer> alObserver = new ArrayList<Observer>();
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<User> buyerList = new ArrayList<User>();
	private ArrayList<Sell> saleList = new ArrayList<Sell>();
	
	private Product newProduct;
	private User newUser;
	private Sell newSale;


	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		try {
			mainFrame = new JFrame("Shop");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setBounds(100, 100, 700, 300);

			panelMain = new JPanel();
			panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
			mainFrame.setContentPane(panelMain);
			panelMain.setLayout(new BorderLayout(0, 0));

			panelForTables = new JPanel();
			panelMain.add(panelForTables, BorderLayout.NORTH);

			btnAddProduct = new JButton("Add a new product");
			panelForTables.add(btnAddProduct);
			btnAddProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAddProductPanel();
				}
			});


			btnAddBuyer = new JButton("Add a new buyer");
			panelForTables.add(btnAddBuyer);
			btnAddBuyer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAddBuyerPanel();
				}
			});

			btnAddSale = new JButton("Add a new sale");
			panelForTables.add(btnAddSale);
			btnAddSale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAddSalePanel();
				}
			});
			
			btnAbout = new JButton("About program");
			panelForTables.add(btnAbout);
			btnAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showMessage("About program", 
							"\"Shop\" is a program to work with \"shop\" SQL database.\n"
							+ "Developer: Jevgenij Kariagin\n"
							+ "2017 ");
				}
			});

			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			panelMain.add(tabbedPane, BorderLayout.CENTER);
			
			tabbedPane.add("Products", scrollPaneProducts);
			for (Observer observer : alObserver) 
			observer.update(null, "" + getName()
					+ ".Starting");

			tabbedPane.add("Buyers", scrollPaneBuyers);
			tabbedPane.add("Sales", scrollPaneSales);

			tabbedPane.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("tabbedPane stateChanged Tab: "
							+ tabbedPane.getSelectedIndex());
					switch (tabbedPane.getSelectedIndex()){
					case 0: for (Observer observer : alObserver) 
						observer.update(null, "" + getName()
								+ ".Products tab selected");
						break;
					case 1: for (Observer observer : alObserver) 
							observer.update(null, "" + getName()
									+ ".Buyers tab selected");
							break;
					case 2: for (Observer observer : alObserver) 
							observer.update(null, getName()
									+ ".Sales tab selected");
					break;
					
					}
					
					/*if (tabbedPane.getSelectedIndex() == 1)
						
						}
					if (tabbedPane.getSelectedIndex() == 2)
						;
						}*/
				}
			});

			mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addObserver(Observer observer) {
		alObserver.add(observer);
	}

	public String getName() {
		// System.out.println("toString method is running");
		return "ViewMainFrame";

	}

	public void setProductList(ArrayList<Product> productList) {
		// TODO Auto-generated method stub
		this.productList = productList;
	}

	public void setBuyerList(ArrayList<User> buyerList) {
		// TODO Auto-generated method stub
		this.buyerList = buyerList;
	}

	public void setSaleList(ArrayList<Sell> saleList) {
		// TODO Auto-generated method stub
		this.saleList = saleList;
	}

	public void makeProductTable() {
		String[] columnNames = { "ID", "Name", "Price", "Quantity",
				"Total price" };
		String[][] dataForProductsTable = new String[productList.size()][5];
		int i = 0;
		for (Product product : productList) {
			dataForProductsTable[i][0] = Integer.toString(product.product_id);
			dataForProductsTable[i][1] = product.title;
			dataForProductsTable[i][2] = Double.toString((product.price));
			dataForProductsTable[i][3] = Integer.toString(product.total);
			dataForProductsTable[i][4] = Double.toString(product.price
					* product.total);
			i++;
		}
		tableProducts = new JTable(dataForProductsTable, columnNames);
		tableProducts.setFillsViewportHeight(true);
		scrollPaneProducts = new JScrollPane(tableProducts);
		tabbedPane.setComponentAt(0, scrollPaneProducts);
	}

	public void makeBuyerTable() {
		String[] columnNames = { "ID", "Name", "Email", "Phone" };
		String[][] dataForBuyerTable = new String[buyerList.size()][4];
		int i = 0;
		for (User buyer : buyerList) {
			dataForBuyerTable[i][0] = Integer.toString(buyer.user_id);
			dataForBuyerTable[i][1] = buyer.name;
			dataForBuyerTable[i][2] = buyer.email;
			dataForBuyerTable[i][3] = buyer.phone;
			i++;
		}
		tableBuyers = new JTable(dataForBuyerTable, columnNames);
		tableBuyers.setFillsViewportHeight(true);
		scrollPaneBuyers = new JScrollPane(tableBuyers);
		tabbedPane.setComponentAt(1, scrollPaneBuyers);
	}

	public void makeSaleTable() {
		String[] columnNames = { "SaleID", "ProductID", "BuyerID", "Count",
				"Date" };
		String[][] dataForSaleTable = new String[saleList.size()][5];
		int i = 0;
		for (Sell sell : saleList) {
			dataForSaleTable[i][0] = Integer.toString(sell.sell_id);
			dataForSaleTable[i][1] = Integer.toString(sell.product_id);
			dataForSaleTable[i][2] = Integer.toString(sell.buyer_id);
			dataForSaleTable[i][3] = Integer.toString(sell.count);
			dataForSaleTable[i][4] = sell.sell_date;
			i++;
		}
		tableSales = new JTable(dataForSaleTable, columnNames);
		tableSales.setFillsViewportHeight(true);
		scrollPaneSales = new JScrollPane(tableSales);
		tabbedPane.setComponentAt(2, scrollPaneSales);
	}

	private void showAddProductPanel() {
		// TODO Auto-generated method stub
		System.out.println("showAddProductPanel");
/*		if (viewAddBuyerPanel != null)
		{
			
			panelMain.remove(viewAddBuyerPanel);
			viewAddBuyerPanel=null;
		}*/
//		panelMain.removeAll();
		if (panelMain.getComponentCount() == 3)
			panelMain.remove(panelMain.getComponent(2));
		panelMain.repaint();
		if (viewAddProductPanel == null) 
			viewAddProductPanel = new ViewAddProductPanel(this);
		viewAddProductPanel.show();
		panelMain.add(viewAddProductPanel, BorderLayout.SOUTH);
		
/*		}
		else
		{
			viewAddProductPanel.show();
		}*/
		panelMain.revalidate();
	}
	
	public void setNewProduct(Product newProduct)
	{
		this.newProduct=newProduct;
	}
	public Product getNewProduct()
	{
		return newProduct;
	}
	
	private void showAddBuyerPanel() {
		// TODO Auto-generated method stub
		System.out.println("showAddBuyerPanel");
		if (panelMain.getComponentCount() == 3)
			panelMain.remove(panelMain.getComponent(2));
		panelMain.repaint();
		if (viewAddBuyerPanel == null) 
			viewAddBuyerPanel = new ViewAddBuyerPanel(this);
		viewAddBuyerPanel.show();
		panelMain.add(viewAddBuyerPanel, BorderLayout.SOUTH);

		panelMain.revalidate();
	}
	
	public void setNewBuyer(User newUser)
	{
		this.newUser=newUser;
	}
	public User getNewUser()
	{
		return newUser;
	}
	
	private void showAddSalePanel() {
		// TODO Auto-generated method stub
		System.out.println("showAddSalePanel");
		if (panelMain.getComponentCount() == 3)
			panelMain.remove(panelMain.getComponent(2));
		panelMain.repaint();
		if (viewAddSalePanel == null) 
			viewAddSalePanel = new ViewAddSalePanel(this);
		viewAddSalePanel.show();
		panelMain.add(viewAddSalePanel, BorderLayout.SOUTH);
		panelMain.revalidate();
	}
	
	public void setNewSale(Sell newSale) {
		// TODO Auto-generated method stub
		this.newSale=newSale;
	}
	public Sell getNewSale()
	{
		return newSale;
	}
	
	
	public void notifyObservers(Object object, String action)
	{
		for (Observer observer: alObserver)
		observer.update(null, "" + getName() + action);
	}

	public void showMessage(String title, String message) {
		// TODO Auto-generated method stub
		if (title == "")
			JOptionPane.showMessageDialog(mainFrame, message);
		else
			JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.PLAIN_MESSAGE);
	}


}
