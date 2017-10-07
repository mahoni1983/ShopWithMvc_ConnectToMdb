package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * menu and user interface
 * 
 * @author User
 *
 */
public class Model {
	/**
	 * show main menu
	 */
	Scanner scanner = new Scanner(System.in);

	private void show() {
		System.out.println();
		System.out.println("=== MENIU ===");
		System.out.println("1. Prideti nauja preke.");
		System.out.println("2. Perziureti prekes ir ju bendra kaina.");
		System.out.println("3. Uzregistruoti nauja pirkeja.");
		System.out.println("4. Perziureti pirkeju sarasa.");
		System.out.println("5. Parduoti preke pirkejui.");
		System.out.println("0. Iseiti.");
	}

	/**
	 * to get information from user what he wants
	 * 
	 * @return
	 */
	private int getMenuItem() {

		int item = -1;

		do {
			System.out.println("Please choose an option (0-5)");
			item = scanner.nextInt();

		} while (item < 0 || item > 5);
		System.out.println("Your choice is: " + item);
		return item;
	}

	/**
	 * to run appropriate method according to chosen menu item
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		show();
		int menuItem = getMenuItem();
		switch (menuItem) {
		case 1:
			doAddWare();
			break;
		case 2:
			getProductList();
			break;
		case 3:
			doAddUser();
			break;
		case 4:
			getBuyerList();
			break;
		case 5:
			doSell();
			break;
		case 0:
			break;
		}
		// todo 2.4.3.
	}

	/**
	 * to add a new ware
	 * 
	 * @throws Exception
	 */
	private void doAddWare() throws Exception {
		System.out.println("1. Prideti nauja preke.");
		WareSQL wareSQL = new WareSQL();
		Product newWare = new Product();
		System.out.println("Please input ware title");
		scanner.nextLine();
		newWare.title = scanner.nextLine();
		if (!wareSQL.isTitle(newWare.title)) {
			System.out.println("Please input ware price");
			newWare.price = Double.parseDouble(scanner.nextLine());
			System.out.println("Please input ware total");
			newWare.total = scanner.nextInt();
			newWare.product_id = wareSQL.addWare(newWare);
			System.out.println("The new ware has been added:");
			System.out.println(newWare);
		} else
			System.out.println("The ware hasn't been added. A ware with this title is already exists");
	}
	
	public int addNewProduct(Product newProduct) throws Exception {
	//	System.out.println("1. Prideti nauja preke.");
		WareSQL wareSQL = new WareSQL();
	//	Product newWare = new Product();
	//	System.out.println("Please input ware title");
		//scanner.nextLine();
	//	newProduct.title = scanner.nextLine();
		if (!wareSQL.isTitle(newProduct.title)) {
		//	System.out.println("Please input ware price");
		//	newWare.price = Double.parseDouble(scanner.nextLine());
		//	System.out.println("Please input ware total");
		//	newWare.total = scanner.nextInt();
			newProduct.product_id = wareSQL.addWare(newProduct);
			System.out.println("The new ware has been added:");
			System.out.println(newProduct);
			return newProduct.product_id;
		} else
			System.out.println("The ware hasn't been added. A ware with this title is already exists");
		return 0;
	}
	
	
	public int addNewUser(User newUser) throws Exception {
	//	System.out.println("3. Uzregistruoti nauja pirkeja.");
		UserSQL userSQL = new UserSQL();
	//	User newUser = new User();
	//	scanner.nextLine();
	//	System.out.println("Please input user name");
	//	newUser.name = scanner.nextLine();
	//	System.out.println("Please input user email");
	//	newUser.email = scanner.nextLine();
		if (!userSQL.isEmail(newUser.email)) {

	//		System.out.println("Please input user phone");
	//		newUser.phone = scanner.nextLine();
			newUser.user_id = userSQL.addUser(newUser);
			System.out.println("The new user has been added:");
			System.out.println(newUser);
			return newUser.user_id;
		} else
			System.out.println("The user hasn't been added. A user with this name is already exists");
		return 0;
	}
	

	/**
	 * to show all wares from DB
	 * 
	 * @throws Exception
	 */
	public ArrayList<Product> getProductList() throws Exception {
		System.out.println("model.getProductList starts");
		WareSQL wareSQL = new WareSQL();
		ArrayList<Product> productList = wareSQL.getExistWares();
		if (productList.size() == 0)
		{
			System.out.println("List is empty");
			return null;
		}
		else {
			
			System.out.println("Nr\t\tPavadinimas\t\tKaina\t\tKiekis\t\tBendra Kaina");
			double totalSum = 0;
			for (Product product : productList) {
				System.out.println(product.toStringForArray());
				totalSum += (product.price * product.total);
			}
			System.out.println("Total price of all wares: " + totalSum);
			return productList;
		}
		// todo 4.3.2
	}

	/**
	 * to add a new user to DB
	 * 
	 * @throws Exception
	 */
	private void doAddUser() throws Exception {
		System.out.println("3. Uzregistruoti nauja pirkeja.");
		UserSQL userSQL = new UserSQL();
		User newUser = new User();
		scanner.nextLine();
		System.out.println("Please input user name");
		newUser.name = scanner.nextLine();
		System.out.println("Please input user email");
		newUser.email = scanner.nextLine();
		if (!userSQL.isEmail(newUser.email)) {

			System.out.println("Please input user phone");
			newUser.phone = scanner.nextLine();
			newUser.user_id = userSQL.addUser(newUser);
			System.out.println("The new user has been added:");
			System.out.println(newUser);
		} else
			System.out.println("The user hasn't been added. A user with this name is already exists");
	}

	/**
	 * to show all users from DB
	 * 
	 * @throws Exception
	 */
	public ArrayList<User> getBuyerList() throws Exception {
		System.out.println("4. Perziureti pirkeju sarasa.");
		UserSQL userSQL = new UserSQL();
		ArrayList<User> userList = userSQL.getUsers();
		if (userList.size() == 0)
			System.out.println("List is empty");
		else {
			System.out.println("Nr\t\tVardas\t\tEl. paðtas\t\tTelefonas");
			double totalSum = 0;
			for (User user : userList) {
				System.out.println(user.toStringForArray());
			}
		}
		return userList;
	}

	/**
	 * make a sell
	 * 
	 * @throws SQLException
	 */
	private void doSell() throws SQLException {
		System.out.println("5. Parduoti preke pirkejui.");
		System.out.println("Write ware_id");
		int ware_id = scanner.nextInt();
		System.out.println("Write user_id");
		int user_id = scanner.nextInt();
		System.out.println("Write number");
		int number = scanner.nextInt();
		SellSQL sellSQL = new SellSQL();
		String sellResult = sellSQL.checkSellPossibility(ware_id, user_id, number);
		System.out.println(this.getErrorText(sellResult));
	}
	
	public String checkSaleIsAble(Sell newSale) throws Exception
	{
		SellSQL sellSQL = new SellSQL();
		return sellSQL.checkSellPossibility(newSale.product_id, newSale.buyer_id, newSale.count);
	}
	
	public int makeSale(Sell newSale) throws SQLException
	{
		SellSQL sellSQL = new SellSQL();
		newSale.sell_id = sellSQL.addSell(newSale.product_id, newSale.buyer_id, newSale.count, newSale.sell_date);
		return newSale.sell_id;
	}

	
	
	public ArrayList<Sell> getSaleList() throws Exception {
		System.out.println("6. Perziureti pardavimus sarasa.");
		SellSQL sellSQL = new SellSQL();
		ArrayList<Sell> sellList = sellSQL.getSales();
		if (sellList.size() == 0)
			System.out.println("List is empty");
		else {
			System.out.println("Nr\tProduct_id\tBuyer_id\tKiekis\t\tSale_date");
			for (Sell sell: sellList) {
				System.out.println(sell.sell_id + "\t" + sell.product_id + "\t\t" + 
			sell.buyer_id + "\t\t" + sell.count + "\t\t" + sell.sell_date);
			}
		}
		return  sellList;
		// todo 4.3.2
	}

	/**
	 * to output sell result
	 * 
	 * @param error
	 * @return
	 */
	private String getErrorText(String error) {
		switch (error) {
		case "ware not found":
			return "Preke nerasta";
		case "user not found":
			return "Pirkejas nerastas";
		case "not enough":
			return "Nepakankamas prekiu likutis";
		case "ok":
			return "Pardavimas sekmingas";
		default:
			return "Nepazistama klaida";
		}
	}

}
