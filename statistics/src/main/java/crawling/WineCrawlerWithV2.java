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

public class WineCrawlerWithV2 extends WineSSGCrawler {

	@Override
	public List<WineInfo> getWineInfosFromSSGIndex(int index) {
		if (index < 0
			|| index > 19) {
			return null;
		}

		String url = emartWineUrl + index + leftParam;
		Connection conn = Jsoup.connect(url);
		List<WineInfo> result = new ArrayList<>();

		try {
			Document document = conn.get();

			Elements wineInfoList = document
				.select("li[class=cunit_t232]")
				.select("div[class=cunit_info]");

			for (Element element : wineInfoList) {
				Elements wineRateElement = element.select("div.cunit_app > div.rating > div.rate_bg > span > span.blind");

				//평점이 존재하는 경우만
				if (wineRateElement.isEmpty()) {
					continue;
				}

				Element wineNameElement = element.select("div.cunit_md > div.title > a.clickable > em.tx_ko").get(0);
				Element winePriceElement = element.select("div.cunit_price > div.opt_price > em.ssg_price").get(0);

				String wineName = WineCrawlResultParser.parseWineName(wineNameElement);
				Integer winePrice = WineCrawlResultParser.parseWinePrice(winePriceElement);
				Double wineRate = WineCrawlResultParser.parseWineRate(wineRateElement.get(0));

				WineInfo wineInfo = new WineInfo(wineName, winePrice, wineRate);
				result.add(wineInfo);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
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
