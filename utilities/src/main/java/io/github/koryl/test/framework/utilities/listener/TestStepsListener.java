package io.github.koryl.test.framework.utilities.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.koryl.test.framework.utilities.logger.Log;
import io.github.koryl.test.framework.utilities.propertieshandler.PathHandler;
import io.github.koryl.test.framework.utilities.reporter.ReportManager;
import io.github.koryl.test.framework.utilities.screenshooter.ScreenshotProvider;
import org.testng.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestStepsListener implements ITestListener {

    private static ExtentReports extent = ReportManager.getInstance();
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public synchronized void onStart(ITestContext testContext) {
        ExtentTest parent = extent.createTest(testContext.getName());
        parentTest.set(parent);
        Log.startTestCase(testContext.getName());
        Log.info("Started testing on: " + testContext.getStartDate().toString());
    }

    public synchronized void onFinish(ITestContext testContext) {
        extent.flush();
        Log.endTestCase();
    }

    public synchronized void onTestStart(ITestResult tr) {
        ExtentTest child = parentTest.get().createNode(tr.getMethod().getMethodName());
        test.set(child);
        Log.info(tr.getName() + " started.");
    }

    public synchronized void onTestFailure(ITestResult tr) {

        Log.info("Test failure!");
        String filePath = generateFileName(tr);
        ScreenshotProvider.takeSnapShot(filePath);
        try {
            test.get().fail(tr.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
        } catch (IOException e) {
            Log.error("Exception: " + e.getMessage());
        }
    }

    public synchronized void onTestSkipped(ITestResult tr) {

        test.get().skip(tr.getThrowable());
        Log.info("Test skipped!");
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult tr) {

    }

    public synchronized void onTestSuccess(ITestResult tr) {

        test.get().pass("Test passed");
        Log.info("Test success!");
    }

    private String generateFileName(ITestResult tr) {

        String path = PathHandler.getPath("output.directory.screenshots");
        String simpleName = tr.getTestClass().getRealClass().getSimpleName();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"));
        String result;
        if(tr.isSuccess()) {
            result = "Success-" + tr.getName() + "(" + date + ")";
        } else {
            result = "Failed-" + tr.getName() + "(" + date + ")";
        }
        return path + simpleName + "/" + result + ".png";
    }
}
