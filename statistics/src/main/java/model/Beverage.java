package model;

public class Beverage {

	private final String beverageName;
	private final Integer price;

	public Beverage(String beverageName, Integer price) {
		this.beverageName = beverageName;
		this.price = price;
	}

	public String getBeverageName() {
		return beverageName;
	}

	public Integer getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "BeverageInfo{ " +
			"beverageName='" + beverageName +
			", price=" + price +
			'}';
	}
}
