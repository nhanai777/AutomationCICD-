package personalproject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Report demo");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nhan Ai");
		extent.createTest(path);
		return extent;

		// ExtentReports: Quản lý việc thu thập dữ liệu kiểm thử và tạo ra báo cáo.
		// ExtentSparkReporter: Xác định cách mà báo cáo sẽ được hiển thị. Nó chịu trách
		// nhiệm về giao diện, bao gồm tên báo cáo, tiêu đề tài liệu, màu sắc, bố cục,
		// và các yếu tố trực quan khác.
	}
}
