package com.qa.Naukri.BaseTest;

import com.microsoft.playwright.Page;
import com.qa.Naukri.factory.playwrightFactory;
import com.qa.Naukri.pages.HomePage;
import com.qa.Naukri.pages.LoginPage;
import com.qa.Naukri.pages.ProfilePage;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {

    playwrightFactory pf;
    Page page;
    protected Properties prop;

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProfilePage profilePage;

    @BeforeSuite
    public void cleanUpScreenshots() {
        System.out.println("Deleting old screenshots before test suite...");
        playwrightFactory.clearScreenshotFolder();
    }

    // Accept browser parameter from testng.xml
    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browserName, ITestContext context) {
        context.setAttribute("browser", browserName); // <-- Add this line
        pf = new playwrightFactory();
        prop = pf.init_prop();
        prop.setProperty("browser", browserName); // override config if passed via XML
        page = pf.launchBrowser(prop);
        loginPage = new LoginPage(page, prop);
    }

    @AfterClass
    public void tearDown() {
        if (page != null) {
            page.context().browser().close();
        }
    }
}
