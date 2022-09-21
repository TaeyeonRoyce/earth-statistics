package crawling;

import org.jsoup.nodes.Element;

public class crawlResultParser {

	public static String parseWineName(Element wineNameElement) {
		// [매장픽업] 앙드레 끌루에 샹파뉴 초키 => 앙드레 끌루에 샹파뉴 초키
		String rawText = wineNameElement.text();
		String[] split = rawText.split("\\]");

		return split[split.length - 1].trim();
	}

	public static Integer parseWinePrice(Element winePriceElement) {
		// 49,500 => 49500
		String rawText = winePriceElement.text();
		return Integer.parseInt(rawText.replaceAll("\\,", ""));
	}

	public static Double parseWineRate(Element wineRateElement) {
		// 별점 4.86점 => 4.86
		String rawText = wineRateElement.text();
		String rate = rawText.split(" ")[1].split("점")[0];
		return Double.parseDouble(rate);
	}

}
