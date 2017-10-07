import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.Product;
import model.Sell;
import model.User;
import view.ViewMainFrame;

public class Controller implements Observer {
	private Model model;
	private ViewMainFrame viewMainFrame;

	public Controller(ViewMainFrame viewMainFrame, Model model) {
		this.viewMainFrame = viewMainFrame;
		this.model = model;
		this.viewMainFrame.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Controller received as observer: "
				+ arg1.toString());
		switch (arg1.toString()) {
		case "ViewMainFrame.Products tab selected":
		case "ViewMainFrame.Starting":
			try {
				viewMainFrame.setProductList(model.getProductList());
				viewMainFrame.makeProductTable();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// showProducts();
			break;

		case "ViewMainFrame.Buyers tab selected":
			try {
				viewMainFrame.setBuyerList(model.getBuyerList());
				viewMainFrame.makeBuyerTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
			break;
		case "ViewMainFrame.Sales tab selected":
			try {
				viewMainFrame.setSaleList(model.getSaleList());
				viewMainFrame.makeSaleTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
			break;

		case "ViewMainFrame.Add new product button clicked":
			try {
				Product newProduct = viewMainFrame.getNewProduct();
				newProduct.product_id = model.addNewProduct(newProduct);
				viewMainFrame.setNewProduct(newProduct);
				if (newProduct.product_id != 0) {
					viewMainFrame.showMessage("A new product has been added",
							"A new product has been added successfully: \n"
									+ newProduct.toStrings());
					viewMainFrame.setProductList(model.getProductList());
					viewMainFrame.makeProductTable();
				} else
					viewMainFrame.showMessage("Error",
							"A new product hasn't been added. Possibly "
									+ newProduct.title + " already exists.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
			break;

		case "ViewMainFrame.Add new buyer button clicked":
			try {
				User newBuyer = viewMainFrame.getNewUser();
				newBuyer.user_id = model.addNewUser(newBuyer);
				viewMainFrame.setNewBuyer(newBuyer);
				if (newBuyer.user_id != 0) {
					viewMainFrame.showMessage("A new buyer has been added",
							"A new buyer has been added successfully: \n"
									+ newBuyer.toStrings());
					viewMainFrame.setBuyerList(model.getBuyerList());
					viewMainFrame.makeBuyerTable();
				} else
					viewMainFrame.showMessage("Error",
							"A new buyer hasn't been added. Possibly "
									+ newBuyer.email + " already exists.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
			break;

		case "ViewMainFrame.Add new sale button clicked":
			try {
				Sell newSale = viewMainFrame.getNewSale();
				String sellAbilityResult = model.checkSaleIsAble(newSale);
				if (sellAbilityResult.equals("ok")) 
				{	//make sale if ok
					newSale.sell_id = model.makeSale(newSale);
					if (newSale.sell_date.equals(""))
						newSale.sell_date = LocalDate.now().toString();
					viewMainFrame.setNewSale(newSale);
					if (newSale.sell_id != 0) {
						viewMainFrame.showMessage("A new sale has been added",
								"A new sale has been added successfully: \n"
										+ newSale.toStrings());
						viewMainFrame.setSaleList(model.getSaleList());
						viewMainFrame.makeSaleTable();
						
						viewMainFrame.setProductList(model.getProductList());
						viewMainFrame.makeProductTable();
					} else
						viewMainFrame.showMessage("Error",
								"A new sale hasn't been added.");
				}
				else
				{
					viewMainFrame.showMessage("Error",
							"A new sale hasn't been added.\n" + sellAbilityResult);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
			break;

		default:
			System.out.println("Can't define observable method");
		}
	}

	private void showProducts() {
		// TODO Auto-generated method stub
		System.out.println("Controller.showProducts() starts");
		try {
			viewMainFrame.setProductList(model.getProductList());
			// viewMainFrame.buildTabbedPane();
			// viewMainFrame.updateProductTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
