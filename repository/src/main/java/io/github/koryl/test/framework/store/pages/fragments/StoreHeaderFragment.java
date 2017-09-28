package io.github.koryl.test.framework.store.pages.fragments;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.support.FindBy;

public class StoreHeaderFragment {

    @FindBy(css = "a.login") private GrapheneElement buttonLogin;
    @FindBy(css = "a.logout") private GrapheneElement buttonLogout;
    @FindBy(css = "a.account") private GrapheneElement buttonAccount;

    public void clickLogin(){
        buttonLogin.click();
    }

    public void clickLogout(){
        buttonLogout.click();
    }

    public void clickMyAccount(){
        buttonAccount.click();
    }

    public boolean isLogged() {
        return buttonAccount.isPresent();
    }
}
