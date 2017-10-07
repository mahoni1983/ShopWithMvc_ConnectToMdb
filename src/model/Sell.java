package model;

public class Sell {

		public int sell_id;
		public int product_id;
		public int buyer_id;
		public int count;
		public String sell_date;

		public String toString() {
			String string = "sell_id: " + sell_id + " product_id: " + product_id + " buyer_id: " + buyer_id + " count: " + count + " sell_date: " + sell_date;
			return string;

		}
		
		public String toStrings() {
			String string = "sell_id: " + sell_id + "\nproduct_id: " + product_id + "\nbuyer_id: " + buyer_id + "\ncount: " + count + "\nsell_date: " + sell_date;
			return string;

		}
		
}
