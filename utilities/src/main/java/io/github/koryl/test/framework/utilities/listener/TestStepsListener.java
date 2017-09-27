package io.github.koryl.test.framework.utilities.listener;

import io.github.koryl.test.framework.utilities.propertieshandler.PathHandler;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.drone.api.annotation.Default;
import org.jboss.arquillian.graphene.context.GrapheneContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestStepsListener extends TestListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestStepsListener.class);

    private WebDriver driver;

    public void onStart(ITestContext context) {
        LOGGER.info("Started testing on: " + context.getStartDate()
                .toString());
    }

    public void onTestFailure(ITestResult tr) {
        driver = GrapheneContext.getContextFor(Default.class).getWebDriver();
        LOGGER.info("Test failure!");
        String path = generateFileName(tr);
        try {
            takeSnapShot(driver, path);
            String image = "<a href='" + path + "'><img src='" + path
                    + "' height='200' width='400' /></a>";
            String message = tr.getThrowable().getMessage();
            Reporter.log(image);
            Reporter.log(message);
        } catch (Exception e) {
            LOGGER.error("Cannot take screenshot", e);
        }
    }

    public void onTestSkipped(ITestResult tr) {
        driver = GrapheneContext.getContextFor(Default.class).getWebDriver();
        LOGGER.info("Test skipped!");
        String path = generateFileName(tr);
        try {
            takeSnapShot(driver, path);
        } catch (Exception e) {
            LOGGER.error("Cannot take screenshot", e);
        }
    }

    public void onTestSuccess(ITestResult tr) {
        driver = GrapheneContext.getContextFor(Default.class).getWebDriver();
        LOGGER.info("Test success!");
        String path = generateFileName(tr);
        try {
            takeSnapShot(driver, path);
        } catch (Exception e) {
            LOGGER.error("Cannot take screenshot", e);
        }
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

    private static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile, destFile);
    }
}
