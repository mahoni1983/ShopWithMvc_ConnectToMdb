package model;

/**
 * ware class to create objects like ware in MySQL
 * 
 * @author User
 *
 */
public class Product {
	public int product_id;
	public String title;
	public double price;
	public int total;

	public String toString() {
		String string = "product_id: " + product_id + " title: " + title + " price: " + price + " total: " + total;
		return string;
	}
	
	public String toStrings() {
		String string = "product_id: " + product_id + "\ntitle: " + title + "\nprice: " + price + "\ntotal: " + total;
		return string;
	}

	public String toStringForArray() {
		String string = "" + product_id + "\t\t" + title + "\t\t" + price + "\t\t" + total + "\t\t" + price * total;
		return string;

	}
}
