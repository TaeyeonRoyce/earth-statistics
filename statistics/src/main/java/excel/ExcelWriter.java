package excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crawling.BeverageCrawler;
import crawling.WineCrawlerWithV2;
import crawling.WineSSGCrawler;
import crawling.WineSSGCrawlerV1;
import model.Beverage;
import model.WineInfo;

public class ExcelWriter {

	private WineSSGCrawler wineSSGCrawler;
	private BeverageCrawler beverageCrawler;

	/**
	 * WineInfo V1 contains (name, price)
	 *
	 * @throws InterruptedException
	 */
	public void writeWineInfoV1() throws InterruptedException {
		this.wineSSGCrawler = new WineSSGCrawlerV1();

		try (Workbook workbook = new XSSFWorkbook()) {
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

			List<WineInfo> wineInfoList = wineSSGCrawler.getWineInfosFromSSGIndex(1, 19);
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

	/**
	 * WineInfo V2 contains (name, price, rate)
	 * Works only for wines with rating information
	 * @throws InterruptedException
	 */
	public void writeWineInfoV2() throws InterruptedException {
		this.wineSSGCrawler = new WineCrawlerWithV2();
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("와인 정보");
			int headerRowNum = 3;
			Row headerRow = sheet.createRow(headerRowNum);

			int wineNameColumnNumber = 3;
			int winePriceColumnNumber = 5;
			int wineRateColumnNumber = 7;
			headerRow.createCell(wineNameColumnNumber).setCellValue("와인 이름");
			headerRow.createCell(winePriceColumnNumber).setCellValue("와인 가격");
			headerRow.createCell(wineRateColumnNumber).setCellValue("와인 평점");

			CellStyle headStyle = workbook.createCellStyle();
			headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			headerRow.getCell(wineNameColumnNumber).setCellStyle(headStyle);
			headerRow.getCell(winePriceColumnNumber).setCellStyle(headStyle);
			headerRow.getCell(wineRateColumnNumber).setCellStyle(headStyle);

			List<WineInfo> wineInfoList = wineSSGCrawler.getWineInfosFromSSGIndex(1, 19);
			Collections.sort(wineInfoList);

			int valueRowNum = headerRowNum + 1;
			for (WineInfo wineInfo : wineInfoList) {
				String wineTitle = wineInfo.getWineTitle();
				int winePrice = wineInfo.getPrice();
				Double rate = wineInfo.getRate();

				Row row = sheet.createRow(valueRowNum++);
				row.createCell(wineNameColumnNumber).setCellValue(wineTitle);
				row.createCell(winePriceColumnNumber).setCellValue(winePrice);
				row.createCell(wineRateColumnNumber).setCellValue(rate);
			}

			try (FileOutputStream fos = new FileOutputStream("wine_info.xlsx")) {
				workbook.write(fos);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeBeverageInfoV1() throws InterruptedException {
		this.beverageCrawler = new BeverageCrawler();
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("음료수 정보");
			int headerRowNum = 3;
			Row headerRow = sheet.createRow(headerRowNum);

			int wineNameColumnNumber = 2;
			int winePriceColumnNumber = 3;
			headerRow.createCell(wineNameColumnNumber).setCellValue("음료 상품 이름");
			headerRow.createCell(winePriceColumnNumber).setCellValue("음료 가격");

			CellStyle headStyle = workbook.createCellStyle();
			headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			headerRow.getCell(wineNameColumnNumber).setCellStyle(headStyle);
			headerRow.getCell(winePriceColumnNumber).setCellStyle(headStyle);

			List<Beverage> beverageInfosFromSSGIndex = beverageCrawler.getBeverageInfosFromSSGIndex(1, 3);

			int valueRowNum = headerRowNum + 1;
			for (Beverage beverage : beverageInfosFromSSGIndex) {
				String beverageName = beverage.getBeverageName();
				int beveragePrice = beverage.getPrice();

				Row row = sheet.createRow(valueRowNum++);
				row.createCell(wineNameColumnNumber).setCellValue(beverageName);
				row.createCell(winePriceColumnNumber).setCellValue(beveragePrice);
			}

			try (FileOutputStream fos = new FileOutputStream("beverage_info.xlsx")) {
				workbook.write(fos);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
