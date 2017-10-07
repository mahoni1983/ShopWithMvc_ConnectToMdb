package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * class for making sell with work with DB
 * 
 * @author User
 *
 */




public class SellSQL {
	
	
	public ArrayList<Sell> getSales() throws SQLException {
		ArrayList<Sell> saleList = new ArrayList<Sell>();
		try {
			String query = "select * from sell;"; // order by title
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				int sellt_id = rs.getInt("sell_id");
				int product_id = rs.getInt("ware_id");
				int buyer_id = rs.getInt("user_id");
				int count = rs.getInt("number");
				String sell_date = rs.getString("sell_date");
				Sell sell = new Sell();
				sell.sell_id = sellt_id;
				sell.product_id = product_id;
				sell.buyer_id = buyer_id;
				sell.count = count;
				sell.sell_date = sell_date;
				saleList.add(sell);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("SellSQL getSales error");
		}
		return saleList;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public int addSell(int ware_id, int user_id, int number, String date) throws SQLException {
		int sellId = 0;
		try {

			String query = "insert into sell(ware_id, user_id, number, sell_date) values(?, ?, ?, ?);";
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
	//		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
			ps.setInt(1, ware_id);
			ps.setInt(2, user_id);
			ps.setInt(3, number);
			if (date.equals(""))
				ps.setString(4, LocalDate.now().toString());
			else  // new  // Date(date
				ps.setString(4, date);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			System.out.println("Sell added to DB: " + (rs.next()));
			sellId = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sellId;

	}
	
	

	/**
	 * to update ware table in DB
	 * 
	 * @param ware_id
	 * @param number
	 * @throws SQLException
	 */
	private void subWareTotal(int ware_id, int number) throws SQLException {

		try {
			String query = "update ware set total = total - ? where ware_id=?;";
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
			ps.setInt(1, number);
			ps.setInt(2, ware_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * to make a transaction and get a message
	 * 
	 * @param ware_id
	 * @param user_id
	 * @param number
	 * @return
	 * @throws SQLException
	 */
	public String checkSellPossibility(int ware_id, int user_id, int number) throws SQLException {
		try {
			WareSQL wareSQL = new WareSQL();
			UserSQL userSQL = new UserSQL();
			if (!wareSQL.isProductID(ware_id))
				return "Product ID does not exist.";
			if (!userSQL.isUserID(user_id))
				return "Buyer ID does not exist.";
			if (!wareSQL.isEnough(ware_id, number))
				return "It is not enough product to sell.";
		//	this.addSell(ware_id, user_id, number, "");
			this.subWareTotal(ware_id, number);
		} catch (Exception e) {
			e.printStackTrace();
			return "Unknown error.";
		}
		return "ok";
	}
	
	public String sell(int ware_id, int user_id, int number, String date) throws SQLException {
		try {
			WareSQL wareSQL = new WareSQL();
			UserSQL userSQL = new UserSQL();
			if (!wareSQL.isProductID(ware_id))
				return "ware not found";
			if (!userSQL.isUserID(user_id))
				return "user not found";
			if (!wareSQL.isEnough(ware_id, number))
				return "not enough";
			this.addSell(ware_id, user_id, number, date);
			this.subWareTotal(ware_id, number);
		} catch (Exception e) {
			e.printStackTrace();
			return "unknown error";
		}
		return "ok";
	}
}
