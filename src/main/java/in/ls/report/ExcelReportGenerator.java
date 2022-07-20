package in.ls.report;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ls.bindigs.ResponsePlan;

public class ExcelReportGenerator {
		
	public void export(List<ResponsePlan> plan, HttpServletResponse response) throws Exception {
			
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Plans");
		XSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Plan Id");
		headerRow.createCell(1).setCellValue("Plan Name");
		headerRow.createCell(2).setCellValue("Holder Name");
		headerRow.createCell(3).setCellValue("Holder SSN");
		headerRow.createCell(4).setCellValue("Plan Status");
		
		for(int i=0;i<plan.size();i++) {
			ResponsePlan plans = plan.get(i);
			XSSFRow dataRow = sheet.createRow(i+1);
			dataRow.createCell(0).setCellValue(plans.getPlanId());
			dataRow.createCell(1).setCellValue(plans.getPlanName());
			dataRow.createCell(2).setCellValue(plans.getHolderName());
			dataRow.createCell(3).setCellValue(plans.getHolderSsn());
			dataRow.createCell(4).setCellValue(plans.getPlanStatus());
		
		}
		
		ServletOutputStream stream = response.getOutputStream();
		workbook.write(stream);
		workbook.close();
		stream.close();
		
	}
	
}
