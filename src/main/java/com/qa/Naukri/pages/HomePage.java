package com.qa.Naukri.pages;

import com.microsoft.playwright.Page;


public class HomePage {

    private Page page;

    private String viewProfileLink = "div.view-profile-wrapper a[href='/mnjuser/profile']";


    public  HomePage(Page page)
    {
            this.page = page;
    }

    public void waitForHomePageToLoad() {
        page.waitForSelector(viewProfileLink);
    }

    public String getHomePageUrl(){
        String HomeUrl = page.url();
        System.out.println("Home Page URL ---->"+HomeUrl);
        return HomeUrl;
    }

    public String getHomePageTitle(){
        String HomeTitle = page.title();
        System.out.println("Home Page TITLE ---->"+HomeTitle);
        return HomeTitle;
    }


    public ProfilePage navigateProfilePage(){

        page.waitForSelector(viewProfileLink);
        page.locator(viewProfileLink).click();
        return new ProfilePage(page);
    }



}
