package model;

public class WineInfo implements Comparable<WineInfo>{

	private final String wineTitle;
	private Integer price;

	public WineInfo(String wineTitle, Integer price) {
		this.wineTitle = wineTitle;
		this.price = price;
	}

	public String getWineTitle() {
		return this.wineTitle;
	}

	public int getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "WineInfo{" +
			"wineTitle='" + wineTitle + '\'' +
			", price=" + price +
			'}';
	}

	@Override
	public int compareTo(WineInfo o) {
		return price - o.price;
	}
}
