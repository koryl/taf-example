package io.github.koryl.test.framework.store.pages.objects;

import io.github.koryl.test.framework.store.pages.fragments.StoreHeaderFragment;
import io.github.koryl.test.framework.utilities.logger.Log;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class StoreAbstractPage {

    @Drone protected WebDriver driver;

    @FindBy(id = "header") protected StoreHeaderFragment header;
    @FindBy(tagName = "body") protected WebElement context;

    public boolean isLogged(){
        return header.isLogged();
    }

    public void logout(){
        header.clickLogout();
        Log.info("User was logout from site.");
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public abstract boolean isAt();
}
