package io.github.koryl.test.framework.store.tests;

import io.github.koryl.test.framework.store.pages.navigators.Navigator;
import io.github.koryl.test.framework.store.pages.navigators.StoreMyAccountNavigator;
import io.github.koryl.test.framework.store.pages.objects.StoreLoginPage;
import io.github.koryl.test.framework.store.pages.objects.StoreMainPage;
import io.github.koryl.test.framework.store.pages.objects.StoreMyAccountPage;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import static io.github.koryl.test.framework.store.pages.navigators.StoreMyAccountNavigator.*;
import static org.testng.Assert.*;

@RunAsClient
public class TC001_ShouldLogInWithCorrectCredentials extends Arquillian {

    @Page private StoreMainPage mainPage;
    @Page private StoreLoginPage loginPage;
    @Page private StoreMyAccountPage myAccountPage;

    @Test(  description = "Step 1 - User opens tested site and open login page."    )
    public void step1OpenMainPageAndNavigateToLogin(){

        Graphene.goTo(StoreMainPage.class);
        assertTrue(mainPage.isAt());
        mainPage.clickLogin();
        assertTrue(loginPage.isAt());
    }

    @Test(  description = "Step 2 - User types given credentials",
            dependsOnMethods = "step1OpenMainPageAndNavigateToLogin",
            parameters = {"Data1", "Data2"} )
    public void step2LoginToStorePage(String login, String password) {

        loginPage.login(login, password);
        assertTrue(mainPage.isLogged(), "User didn't loegged properly, expected element wasn't found:");
    }

    @Test(  description = "Step 3 - User types given credentials",
            dependsOnMethods = "step1OpenMainPageAndNavigateToLogin"   )
    public void step3UserShouldNavigateToPersonalInformation() {

        assertTrue(myAccountPage.isAt());
        myAccountPage.goTo(MY_PERSONAL_INFORMATION);
        assertTrue(false, "This test should fail and you will see this message in report. Snapshot should be taken.");
    }
}
