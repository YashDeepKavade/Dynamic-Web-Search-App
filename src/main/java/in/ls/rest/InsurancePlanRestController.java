package in.ls.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import in.ls.bindigs.RequestPlan;
import in.ls.bindigs.ResponsePlan;
import in.ls.report.ExcelReportGenerator;
import in.ls.report.PdfReportGenerator;
import in.ls.service.InsurancePlanService;

@RestController
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService service;

	@GetMapping("/pdf")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users.pdf";
		response.setHeader(headerKey, headerValue);
		
		List<ResponsePlan> plan = service.getPlan(null);
		PdfReportGenerator pdf=new PdfReportGenerator();
		pdf.exportPdf(response, plan);
		
	}

	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<ResponsePlan> plan = service.getPlan(null);
		ExcelReportGenerator excel = new ExcelReportGenerator();
		excel.export(plan, response);

	}

	@PostMapping("/plans")
	public ResponseEntity<List<ResponsePlan>> getPlan(@RequestBody RequestPlan request) {

		List<ResponsePlan> plan = service.getPlan(request);
		return new ResponseEntity<>(plan, HttpStatus.OK);

	}

	@GetMapping("/planname")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> planStatus = service.getUniquePlanStatus();
		return new ResponseEntity<>(planStatus, HttpStatus.OK);
	}

}
