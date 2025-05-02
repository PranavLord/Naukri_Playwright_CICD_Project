package com.qa.Naukri.Tests;

import com.qa.Naukri.BaseTest.BaseTest;
import com.qa.Naukri.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {


    @Test
    public void loginPageTitleTest(){
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle,AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test
    public void loginPageUrlTest(){
        String actualUrl = loginPage.getLoginPageUrl();
        Assert.assertEquals(actualUrl,prop.getProperty("url"));
    }

    @Test
    public void naukriLogoTest(){
        boolean isVisible = loginPage.IsNaukriLogoVisible();
        Assert.assertTrue(isVisible, "Naukri logo should be visible after launch.");
    }

    @Test
    public void loginUserTest() {
        homePage = loginPage.loginUser();
        homePage.waitForHomePageToLoad(); // Add a new method like this in HomePage class

        Assert.assertEquals(homePage.getHomePageTitle(),AppConstants.HOME_PAGE_TITLE);
        Assert.assertEquals(homePage.getHomePageUrl(),AppConstants.HOME_PAGE_URL);

        System.out.println("User Logged In Successfully");
    }


}
