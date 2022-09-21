package model;

public class WineInfo implements Comparable<WineInfo>{

	private final String wineTitle;
	private final Integer price;
	private final Double rate;

	public WineInfo(String wineTitle, Integer price, Double rate) {
		this.wineTitle = wineTitle;
		this.price = price;
		this.rate = rate;
	}

	public String getWineTitle() {
		return this.wineTitle;
	}

	public int getPrice() {
		return this.price;
	}

	public Double getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "WineInfo{ " +
			"wineTitle='" + wineTitle +
			", price=" + price +
			", rate=" + rate +
			'}';
	}

	@Override
	public int compareTo(WineInfo o) {
		return price - o.price;
	}
}
