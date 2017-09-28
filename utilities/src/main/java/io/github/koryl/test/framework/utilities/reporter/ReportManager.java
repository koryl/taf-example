package io.github.koryl.test.framework.utilities.reporter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.koryl.test.framework.utilities.logger.Log;
import io.github.koryl.test.framework.utilities.propertieshandler.PathHandler;

public class ReportManager {

    private static ExtentReports extent;
    private final static String path = PathHandler.getPath("output.directory");

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance("run-report.html");

        Log.debug("ReportManager was invoked.");
        return extent;
    }

    private static void createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Example test run");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Example test run");

        extent = new ExtentReports();
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(htmlReporter);
        Log.debug("ReportManager was created.");
    }
}
