package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Beverage;
import model.WineInfo;

public class BeverageCrawler {

	protected final String emartBeverageUrl =
		"https://www.ssg.com/search.ssg?target=all&query=%EC%9D%8C%EB%A3%8C%EC%88%98&sort=sale&page=";
	public List<Beverage> getBeverageInfosFromSSGIndex(int index) {
		if (index < 0
			|| index > 19) {
			return null;
		}

		String url = emartBeverageUrl + index;
		Connection conn = Jsoup.connect(url);
		List<Beverage> result = new ArrayList<>();

		try {
			Document document = conn.get();

			Elements wineInfoList = document
				.select("li[class=cunit_t232]")
				.select("div[class=cunit_info]");

			for (Element element : wineInfoList) {
				Element beverageNameElement = element.select("div.cunit_md > div.title > a.clickable > em.tx_ko").get(0);
				Element beveragePriceElement = element.select("div.cunit_price > div.opt_price > em.ssg_price").get(0);

				String beverageName = crawlResultParser.parseWineName(beverageNameElement);
				Integer beveragePrice = crawlResultParser.parseWinePrice(beveragePriceElement);

				Beverage beverage = new Beverage(beverageName, beveragePrice);
				result.add(beverage);

				System.out.println("beverageInfo = " + beverage);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Beverage> getBeverageInfosFromSSGIndex(int startIndex, int endIndex) throws InterruptedException {
		if (startIndex >= endIndex) {
			return null;
		}

		List<Beverage> totalList = new ArrayList<>();
		for (int i = startIndex; i <= endIndex; i++) {
			totalList.addAll(getBeverageInfosFromSSGIndex(i));
			Thread.sleep(3000);
		}

		return totalList;
	}
}
