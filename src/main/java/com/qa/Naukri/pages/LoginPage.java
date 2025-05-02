package com.qa.Naukri.pages;

import com.microsoft.playwright.Page;
import java.util.Properties;

import java.util.Properties;

public class LoginPage {


    private Properties prop;
    private Page page;



    private String naukriImg = "a.nI-gNb-header__logo.nI-gNb-company-logo";
    private String loginOption = "a[title='Jobseeker Login']";
    private String email = "input[placeholder='Enter your active Email ID / Username']";
    private String password = "input[placeholder='Enter your password']";
    private String loginButton = "button.loginButton";

    public LoginPage (Page page, Properties prop){
        this.page = page;
        this.prop = prop;
    }


    public String getLoginPageUrl(){
        String Url = page.url();
        System.out.println("URL ---->"+Url);
        return Url;
    }

    public String getLoginPageTitle(){
        String Title = page.title();
        System.out.println("Title ---->"+ Title);
        return Title;
    }

    public boolean IsNaukriLogoVisible(){

        if(page.locator(naukriImg).isVisible()){
            System.out.println("Naukri Title is Visible");
            return true;
        }
        System.out.println("Naukri Title is not Visible");
        return false;
    }

    public HomePage loginUser(){

        page.waitForSelector(loginOption);
        page.locator(loginOption).click();

        page.waitForSelector(email);
        page.locator(email).fill(prop.getProperty("email"));

        page.waitForSelector(password);
        page.locator(password).fill(prop.getProperty("password"));

        page.waitForSelector(loginButton);
        page.locator(loginButton).click();

        return new HomePage(page);

    }

}
