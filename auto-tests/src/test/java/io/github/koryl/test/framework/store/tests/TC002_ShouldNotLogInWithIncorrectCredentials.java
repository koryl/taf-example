package io.github.koryl.test.framework.store.tests;

import io.github.koryl.test.framework.store.pages.objects.StoreLoginPage;
import io.github.koryl.test.framework.store.pages.objects.StoreMainPage;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@RunAsClient
public class TC002_ShouldNotLogInWithIncorrectCredentials extends Arquillian {

    @Page
    private StoreMainPage mainPage;
    @Page private StoreLoginPage loginPage;

    @Test
    public void step1OpenMainPageAndNavigateToLogin(){

        Graphene.goTo(StoreMainPage.class);
        assertTrue(mainPage.isAt());
        mainPage.clickLogin();
        assertTrue(loginPage.isAt(), "Login Page was not loaded properly.");
    }

    @Test(parameters = {"Data1", "Data2"})
    public void step2LoginToStorePage(String login, String password) {

        loginPage.login(login, password);
        assertFalse(mainPage.isLogged());
        assertEquals(mainPage.getCurrentUrl(), "http://automationpractice.com/index.php?controller=authentication", "User wasn't redirected properly:");
    }
}
