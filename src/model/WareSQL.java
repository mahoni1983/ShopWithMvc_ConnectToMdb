package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * to work with ware sql queries
 * 
 * @author User
 *
 */
public class WareSQL {
	public int addWare(Product product) throws SQLException {
		try {
			String query = "insert into ware(title, price, total) values(?, ?, ?);";
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
			ps.setString(1, product.title);
			ps.setString(2, Double.toString(product.price));
			ps.setInt(3, product.total);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				product.product_id = rs.getInt(1);
			return product.product_id;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
/**
 * check if ware with a title already exists
 * @param title
 * @return
 * @throws SQLException
 */
	public boolean isTitle(String title) throws SQLException {
		try {
			String query = "select * from ware where title = ?;";
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query);
			ps.setString(1, title);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("WareSQL isTitle error");
		}

		return false;
	}
/**
 * read out all wares from DB
 * @return
 * @throws SQLException
 */
	public ArrayList<Product> getExistWares() throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			String query = "select * from ware order by title;";
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				String title = rs.getString("title");
				int product_id = rs.getInt("ware_id");
				Product product = new Product();
				product.product_id = product_id;
				product.title = title;
				product.price = rs.getDouble("price");
				product.total = rs.getInt("total");
				productList.add(product);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("WareSQL getWares error");
		}
		return productList;
	}
/**
 * checks if there is a ware by id
 * @param product_id
 * @return
 * @throws SQLException
 */
	public boolean isProductID(int product_id) throws SQLException {
		try {
			String query = "select * from ware where ware_id=?;";
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query);
			ps.setInt(1, product_id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("ProductSQL isProduct_id error");
		}

		return false;
	}
/**
 * checks if ware quantity enough to make a sell
 * @param product_id
 * @param number
 * @return
 * @throws SQLException
 */
	public boolean isEnough(int product_id, int number) throws SQLException {
		try {
			String query = "select total from ware where ware_id=?;";
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query);
			ps.setInt(1, product_id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next())
			
				return (rs.getInt("total") > number);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("WareSQL isWare_id error");
		}

		return false;
	}
}
