package Pages;


    import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;


public class ExcelRead
    {
        public static XSSFSheet worksheet;
        public static XSSFWorkbook workbook;
        public static FileInputStream work_file;


        public static String[] readExcelData(String sheetName) throws Exception {
            //Get the excel file path
            File src=new File("Sign In Data.xlsx");
            work_file = new FileInputStream(src);
            workbook = new XSSFWorkbook(work_file);

            worksheet = workbook.getSheet(sheetName);
            XSSFRow row = worksheet.getRow(1);
            //Using the sheet name passed to this method, read the data and store it in a string array
            String[] data = new String[row.getLastCellNum()];
            try{
                for (int i = 0; i < row.getLastCellNum(); i++){
                    data[i]=row.getCell(i).getStringCellValue(); // Store the excel data into String array
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            

            
            return data;
        }


    }