package io.github.koryl.test.framework.utilities.screenshooter;

import io.github.koryl.test.framework.utilities.logger.Log;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.drone.api.annotation.Default;
import org.jboss.arquillian.graphene.context.GrapheneContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotProvider {

    public static void takeSnapShot(String fileWithPath)  {
        WebDriver driver = GrapheneContext.getContextFor(Default.class).getWebDriver();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(srcFile, destFile);
            Log.debug("Screenshot was taken correctly.");
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
    }
}
