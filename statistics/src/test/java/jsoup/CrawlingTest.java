package jsoup;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import crawling.crawlResultParser;
import crawling.WineSSGCrawlerV1;
import model.WineInfo;

public class CrawlingTest {

	@Test
	public void crawlingTest() {

		final String emartWineUrl =
			"https://www.ssg.com/search.ssg?query=%EC%99%80%EC%9D%B8&ctgId=6000099422&ctgLv=3&count=100&ctgLast=Y&parentCtgId=6000099420&page=";
		int page = 1;

		Connection conn = Jsoup.connect(emartWineUrl + page);

		try {
			Document document = conn.get();
			Elements wineNameList = document
				.select("li[class=cunit_t232]")
				.select("div[class=cunit_info]")
				.select("div.cunit_md > div.title > a.clickable > em.tx_ko");

			Elements winePriceList = document
				.select("li[class=cunit_t232]")
				.select("div[class=cunit_info]")
				.select("div.cunit_price > div.opt_price > em.ssg_price");

			System.out.println(wineNameList.size());
			System.out.println(winePriceList.size());

			for (Element element : wineNameList) {
				System.out.println(element);
			}

			for (Element element : winePriceList) {
				System.out.println(element);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void wineSSGCrawlerTest() throws InterruptedException {

		WineSSGCrawlerV1 wineSSGCrawler = new WineSSGCrawlerV1();
		List<WineInfo> wineInfosFromSSGIndex =
			wineSSGCrawler.getWineInfosFromSSGIndex(1,15);

		for (WineInfo infosFromSSGIndex : wineInfosFromSSGIndex) {
			System.out.println(infosFromSSGIndex.toString());
		}
	}

	@Test
	public void crawlingWithRateTest() {

		final String emartWineUrl =
			"https://www.ssg.com/search/jsonSearch.ssg?target=item&query=%EC%99%80%EC%9D%B8&ctgId=6000099420&ctgLv=2&page=";
		int page = 1;

		Connection conn = Jsoup.connect(emartWineUrl + page);

		try {
			Document document = conn.get();
			Elements wineNameList = document
				.select("li[class=cunit_t232]")
				.select("div[class=cunit_info]");

			for (Element element : wineNameList) {
				Elements wineRateElement = element.select("div.cunit_app > div.rating > div.rate_bg > span > span.blind");

				if (wineRateElement.size() == 1) {
					Element winePriceElement = element.select("div.cunit_price > div.opt_price > em.ssg_price").get(0);
					Integer winePrice = crawlResultParser.parseWinePrice(winePriceElement);
					Double wineRate = crawlResultParser.parseWineRate(wineRateElement.get(0));

					System.out.println(winePrice);
					System.out.println(wineRate);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
