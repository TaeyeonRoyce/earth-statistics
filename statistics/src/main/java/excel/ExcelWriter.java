package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crawling.WineSSGCrawler;
import model.WineInfo;

public class ExcelWriter {

	public void writeWineInfo() throws InterruptedException {

		try(Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("와인 정보");
			int headerRowNum = 3;
			Row headerRow = sheet.createRow(headerRowNum);

			int wineNameColumnNumber = 3;
			int winePriceColumnNumber = 5;
			headerRow.createCell(wineNameColumnNumber).setCellValue("와인 이름");
			headerRow.createCell(winePriceColumnNumber).setCellValue("와인 가격");

			CellStyle headStyle = workbook.createCellStyle();
			headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			headerRow.getCell(wineNameColumnNumber).setCellStyle(headStyle);
			headerRow.getCell(winePriceColumnNumber).setCellStyle(headStyle);


			WineSSGCrawler wineSSGCrawler = new WineSSGCrawler();
			List<WineInfo> wineInfoList = wineSSGCrawler.getWineInfosFromSSGIndex(1, 15);
			Collections.sort(wineInfoList);

			int valueRowNum = headerRowNum + 1;
			for (WineInfo wineInfo : wineInfoList) {
				String wineTitle = wineInfo.getWineTitle();
				int winePrice = wineInfo.getPrice();

				Row row = sheet.createRow(valueRowNum++);
				row.createCell(wineNameColumnNumber).setCellValue(wineTitle);
				row.createCell(winePriceColumnNumber).setCellValue(winePrice);
			}

			try (FileOutputStream fos = new FileOutputStream("wine_info.xlsx")) {
				workbook.write(fos);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
