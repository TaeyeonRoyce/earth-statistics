package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.WineInfo;

public class WineSSGCrawler {
	private final String emartWineUrl =
		"https://www.ssg.com/search/jsonSearch.ssg?target=item&query=%EC%99%80%EC%9D%B8&ctgId=6000099420&ctgLv=2&page=";
	private final String leftParam = "&_=1662967296634";

	/**
	 * SSG mall 에서 와인 가격, 이름 크롤링
	 *
	 * @param index 0 ~ 15
	 */

	public List<WineInfo> getWineInfosFromSSGIndex(int index) {
		if (index < 0
			|| index > 15) {
			return null;
		}

		String url = emartWineUrl + index + leftParam;
		Connection conn = Jsoup.connect(url);
		List<WineInfo> result = new ArrayList<>();

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

			for (int i = 0; i < wineNameList.size(); i++) {
				Element wineNameElement = wineNameList.get(i);
				Element winePriceElement = winePriceList.get(i);

				String wineName = WineParser.parseWineName(wineNameElement);
				Integer winePrice = WineParser.parseWinePrice(winePriceElement);

				WineInfo wineInfo = new WineInfo(wineName, winePrice);
				result.add(wineInfo);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<WineInfo> getWineInfosFromSSGIndex(int startIndex, int endIndex) throws InterruptedException {
		if (startIndex >= endIndex) {
			return null;
		}

		List<WineInfo> totalList = new ArrayList<>();
		for (int i = startIndex; i <= endIndex; i++) {
			totalList.addAll(getWineInfosFromSSGIndex(i));
			Thread.sleep(3000);
		}

		return totalList;
	}
}
