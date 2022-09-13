package crawling;

import org.jsoup.nodes.Element;

public class WineParser {

	public static String parseWineName(Element wineNameElement) {
		String rawText = wineNameElement.text();
		String[] split = rawText.split("\\]");

		return split[split.length - 1].trim();
	}

	public static Integer parseWinePrice(Element winePriceElement) {
		String rawText = winePriceElement.text();
		return Integer.parseInt(rawText.replaceAll("\\,", ""));
	}

}
