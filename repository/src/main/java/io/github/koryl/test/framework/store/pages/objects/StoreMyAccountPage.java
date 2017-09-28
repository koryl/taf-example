package io.github.koryl.test.framework.store.pages.objects;

import io.github.koryl.test.framework.store.pages.navigators.StoreMyAccountNavigator;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.support.FindBy;

@Location("http://automationpractice.com/index.php?controller=my-account")
public class StoreMyAccountPage extends StoreAbstractPage {

    @FindBy(css = "i.icon-building") private GrapheneElement buttonAddress;
    @FindBy(css = "i.icon-user") private GrapheneElement buttonPersonalInformation;

    public void goTo(StoreMyAccountNavigator navigator){

        switch (navigator){
            case MY_PERSONAL_INFORMATION:
                buttonPersonalInformation.click();
                break;
            case MY_ADDRESS:
                buttonAddress.click();
                break;
        }
    }

    public boolean isAt(){

        return driver.getCurrentUrl().endsWith("controller=my-account");
    }
}
