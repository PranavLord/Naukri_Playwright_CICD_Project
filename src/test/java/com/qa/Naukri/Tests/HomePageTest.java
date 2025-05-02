package com.qa.Naukri.Tests;

import com.qa.Naukri.BaseTest.BaseTest;
import com.qa.Naukri.constants.AppConstants;
import com.qa.Naukri.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @BeforeClass
    public void setupHomePage() {
        homePage = loginPage.loginUser();
        homePage.waitForHomePageToLoad();
    }

    @Test
    public void homePageTitleTest(){
        homePage.waitForHomePageToLoad();
        String actualHomeTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualHomeTitle, AppConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void homePageUrlTest(){
        homePage.waitForHomePageToLoad();
        String actualHomeUrl = homePage.getHomePageUrl();
        Assert.assertEquals(actualHomeUrl,AppConstants.HOME_PAGE_URL);
    }

    @Test
    public void navigateToProfilePage(){
        homePage.waitForHomePageToLoad();
        profilePage = homePage.navigateProfilePage();
        profilePage.waitForProfilePageToLoad();

        Assert.assertEquals(profilePage.getProfilePageUrl(),AppConstants.PROFILE_PAGE_URL);
        Assert.assertEquals(profilePage.getProfilePageTitle(),AppConstants.PROFILE_PAGE_TITLE);

        System.out.println("User Profile Page Loaded Successfully");

    }

}
