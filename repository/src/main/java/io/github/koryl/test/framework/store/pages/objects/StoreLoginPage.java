package io.github.koryl.test.framework.store.pages.objects;

import io.github.koryl.test.framework.utilities.logger.Log;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("http://www.automationpractice.com/index.php?controller=authentication&back=my-account")
public class StoreLoginPage extends StoreAbstractPage{

    @FindBy private WebElement email;
    @FindBy private WebElement passwd;
    @FindBy (id="SubmitLogin") private WebElement signIn;

    public void login(String login, String password){
        email.clear();
        passwd.clear();
        email.sendKeys(login);
        passwd.sendKeys(password);
        signIn.click();
        Log.info("User typed credentials and clicked Sign In button.");
    }

    public boolean isAt(){
        return driver.getCurrentUrl().endsWith("index.php?controller=authentication&back=my-account");
    }
}
