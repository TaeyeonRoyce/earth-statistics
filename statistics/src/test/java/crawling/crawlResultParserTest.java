package crawling;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class crawlResultParserTest {
	@Test
	public void nameParseTest() {
	    //given
		String parseText = "[매장픽업][22 추석] 아이스바인 잔세트(벨탁스 아이스바인 피노누아";

		String[] split = parseText.split("\\]");

		System.out.println(Arrays.toString(split));

	}


}