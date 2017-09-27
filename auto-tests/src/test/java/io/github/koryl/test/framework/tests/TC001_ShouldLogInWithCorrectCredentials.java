package io.github.koryl.test.framework.tests;

import io.github.koryl.test.framework.pages.objects.StoreLoginPage;
import io.github.koryl.test.framework.pages.objects.StoreMainPage;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@RunAsClient
public class TC001_ShouldLogInWithCorrectCredentials extends Arquillian{

    @Page private StoreMainPage mainPage;
    @Page private StoreLoginPage loginPage;

    @Test
    public void step1OpenMainPageAndNavigateToLogin(){
        Graphene.goTo(StoreMainPage.class);
        assertTrue(mainPage.isAt());
        mainPage.clickLogin();
        assertTrue(loginPage.isAt());
    }

    @Test(parameters = {"Data1", "Data2"})
    public void step2LoginToStorePage(String login, String password) throws InterruptedException {
        System.out.println(login + password);
        loginPage.login(login, password);
        assertTrue(mainPage.isLogged());
    }
}
