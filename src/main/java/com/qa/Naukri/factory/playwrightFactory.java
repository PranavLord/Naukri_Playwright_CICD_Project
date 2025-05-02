package com.qa.Naukri.factory;

import com.microsoft.playwright.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class playwrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    static Page page;
    Properties prop;

    public static Page getPage() {
        return page;
    }

    public Page launchBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is: " + browserName);
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;

            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            default:
                System.out.println("Invalid browser name provided: " + browserName);
        }

        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 1005));
        page = browserContext.newPage();
        page.navigate(prop.getProperty("url").trim());
        return page;
    }

    public Properties init_prop() {
        try (FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties")) {
            prop = new Properties();
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String takeScreenshot(Page page) {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        return Base64.getEncoder().encodeToString(buffer);
    }

    public static void clearScreenshotFolder() {
        File folder = new File(System.getProperty("user.dir") + "/screenshot/");
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}
