package crawling;

import java.util.List;

import model.WineInfo;

public abstract class WineSSGCrawler {

	protected final String emartWineUrl =
		"https://www.ssg.com/search/jsonSearch.ssg?target=item&query=%EC%99%80%EC%9D%B8&ctgId=6000099420&ctgLv=2&page=";
	protected final String leftParam = "&_=1662967296634";

	/**
	 * SSG mall 에서 와인 가격, 이름 크롤링
	 *
	 * @param index 0 ~ 19
	 */
	public abstract List<WineInfo> getWineInfosFromSSGIndex(int index);

	public abstract List<WineInfo> getWineInfosFromSSGIndex(int startIndex, int endIndex) throws InterruptedException;
}
