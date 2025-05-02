package com.qa.Naukri.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;
import com.qa.Naukri.factory.playwrightFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import static com.qa.Naukri.factory.playwrightFactory.takeScreenshot;

public class ExtentReportListener implements ITestListener {

    private static final String OUTPUT_FOLDER = System.getProperty("user.dir") + "/build/";
    private static final String FILE_NAME = "TestExecutionReport.html";

    private static ExtentReports extent = init();
    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports init() {
        try {
            Files.createDirectories(Paths.get(OUTPUT_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Test Automation Report");
        extentReports.attachReporter(reporter);

        extentReports.setSystemInfo("System", "Windows");
        extentReports.setSystemInfo("Author", "Pranav Kashyap");
        extentReports.setSystemInfo("Team", "Naukri Portal");

        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String browser = (String) result.getTestContext().getAttribute("browser");
        if (browser == null) {
            browser = "unknown";
        }

        // Add browser name to test title
        ExtentTest extentTest = extent.createTest(methodName + " [" + browser + "]");
        extentTest.assignCategory(browser); // Add browser as category (filter in report)

        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " passed!");
        test.get().pass("Test passed");

        try {
            Page page = playwrightFactory.getPage();
            test.get().pass("Screenshot on Success",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(page)).build());
        } catch (Exception e) {
            test.get().warning("Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " failed!");
        test.get().fail(result.getThrowable());

        try {
            Page page = playwrightFactory.getPage();
            test.get().fail("Screenshot on Failure",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(page)).build());
        } catch (Exception e) {
            test.get().warning("Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " skipped!");
        test.get().skip(result.getThrowable());
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
