package io.github.koryl.test.framework.pages.objects;

import io.github.koryl.test.framework.pages.fragments.StoreHeaderFragment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StoreAbstractPage {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Drone protected WebDriver driver;

    @FindBy(id = "header") protected StoreHeaderFragment header;
    @FindBy(tagName = "body") protected WebElement context;

    public boolean isLogged(){
        return header.isLogged();
    }

    public void logout(){
        header.clickLogout();
        LOGGER.info("User was logout from site.");
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
