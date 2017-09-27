package io.github.koryl.test.framework.pages.objects;

import org.jboss.arquillian.graphene.page.Location;

@Location("http://www.automationpractice.com")
public class StoreMainPage extends StoreAbstractPage {

    public void clickLogin(){
        header.clickLogin();
    }

    public boolean isAt(){
        return driver.getCurrentUrl().endsWith("automationpractice.com/index.php?");
    }
}
