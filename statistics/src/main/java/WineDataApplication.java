import excel.ExcelWriter;

public class WineDataApplication {

	public static void main(String[] args) throws InterruptedException {
		ExcelWriter excelWriter = new ExcelWriter();
		excelWriter.writeWineInfoV2();
	}
}
