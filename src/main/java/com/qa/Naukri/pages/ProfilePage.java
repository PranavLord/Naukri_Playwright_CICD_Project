package com.qa.Naukri.pages;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.Naukri.constants.AppConstants;

import java.nio.file.Paths;

public class ProfilePage {

    private Page page;

    private String quickLinksHeader = "div.card.quickLink ul.collection li.collection-header";
    private String resumePath = "//span[text()='Resume']";
    private String resumeUpdate = "input.dummyUpload";
    private String successMsg = "p.msg";

    private String resumeHeadline = "(//span[text()='Resume headline'])[1]";
    private String resumeHeadlineEdit = "//div[contains(@class,'widgetHead')]//span[@class='widgetTitle typ-16Bold' and text()='Resume headline']/following-sibling::span[@class='edit icon' and text()='editOneTheme']";
    private String resumeHeadlineTextBox = "#resumeHeadlineTxt";
    private String resumeHeadlineSubmit = "//button[@class='btn-dark-ot' and @type='submit']";

    private String keySkillsLine = "(//span[text()='Key skills'])[1]";
    private String keySkillsLineEdit = "//div[contains(@class,'widgetHead')]//span[@class='widgetTitle' and text()='Key skills']/following-sibling::span[@class='edit icon' and text()='editOneTheme']";
    private String keySkillsLineSubmit = "//button[@class='btn-dark-ot' and @id='saveKeySkills']";

    private String careerProfileLine = "(//span[text()='Career profile'])[1]";
    private String careerProfileLineEdit = "//div[contains(@class,'widgetHead')]//span[@class='widgetTitle typ-16Bold' and text()='Career profile']/following-sibling::span[@class='edit icon' and text()='editOneTheme']";
    private String careerProfileLineSubmit = "//button[@class='btn-dark-ot' and @type='button']";

    private String personalDetailsLine = "(//span[text()='Personal details'])[1]";
    private String personalDetailsLineEdit = "//div[contains(@class,'widgetHead')]//span[@class='widgetTitle typ-16Bold' and text()='Personal details']/following-sibling::span[@class='icon edit' and text()='editOneTheme']";
    private String personalDetailsLineSubmit = "//button[@class='btn-dark-ot' and @type='button']";


    public ProfilePage(Page page) {
        this.page = page;
    }

    public void waitForProfilePageToLoad() {
        page.waitForSelector(quickLinksHeader);
    }

    public void waitForResumeHeaderToLoad() {
        page.waitForSelector(resumeHeadline);
    }

    public void waitForKeySkillsToLoad() {
        page.waitForSelector(keySkillsLine);
    }

    public void waitForCareerProfileToLoad() {
        page.waitForSelector(careerProfileLine);
    }

    public void waitForPersonalDetailsToLoad() {
        page.waitForSelector(personalDetailsLine);
    }

    public String getProfilePageUrl() {
        String profileUrl = page.url();
        System.out.println("Profile Page URL ----> " + profileUrl);
        return profileUrl;
    }

    public String getProfilePageTitle() {
        String profileTitle = page.title();
        System.out.println("Profile Page TITLE ----> " + profileTitle);
        return profileTitle;
    }

    // Helper method to wait for the success message to disappear
    private void waitForSuccessMessageToDisappear() {
        try {
            page.waitForSelector(successMsg, new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.valueOf("detached"))   // <-- Corrected here!
                    .setTimeout(10000));
            System.out.println("Success message disappeared successfully.");
        } catch (Exception e) {
            System.out.println("Success message did not disappear in expected time.");
        }
    }


    public String uploadResume(String filePath) {
        page.locator(resumePath).click();
        page.waitForSelector(resumeUpdate);

        FileChooser fileChooser = page.waitForFileChooser(() -> {
            page.locator(resumeUpdate).click();
        });

        fileChooser.setFiles(Paths.get(filePath));

        try {
            page.waitForSelector(successMsg, new Page.WaitForSelectorOptions().setTimeout(5000));
            String message = page.locator(successMsg).innerText().trim();
            waitForSuccessMessageToDisappear();
            return message;
        } catch (Exception e) {
            System.out.println("Success message disappeared too fast or not visible!");
            return "No success message captured";
        }
    }

    public String updateResumeHeadline() {
        page.locator(resumeHeadline).click();
        page.waitForSelector(resumeHeadlineEdit);
        page.locator(resumeHeadlineEdit).click();
        page.waitForSelector(resumeHeadlineTextBox);
        page.locator(resumeHeadlineTextBox).fill(AppConstants.PROFILE_PAGE_RESUME_NEWMSG);
        page.locator(resumeHeadlineSubmit).click();

        try {
            page.waitForSelector(successMsg, new Page.WaitForSelectorOptions().setTimeout(5000));
            String message = page.locator(successMsg).innerText().trim();
            waitForSuccessMessageToDisappear();
            return message;
        } catch (Exception e) {
            System.out.println("Success message disappeared too fast or not visible!");
            return "No success message captured";
        }
    }

    public String updateKeySkills() {
        page.locator(keySkillsLine).click();
        page.waitForSelector(keySkillsLineEdit);
        page.locator(keySkillsLineEdit).click();
        page.waitForSelector(keySkillsLineSubmit);
        page.locator(keySkillsLineSubmit).click();

        try {
            page.waitForSelector(successMsg, new Page.WaitForSelectorOptions().setTimeout(5000));
            String message = page.locator(successMsg).innerText().trim();
            waitForSuccessMessageToDisappear();
            return message;
        } catch (Exception e) {
            System.out.println("Success message disappeared too fast or not visible!");
            return "No success message captured";
        }
    }

    public String updateCareerProfile() {
        page.locator(careerProfileLine).click();
        page.waitForSelector(careerProfileLineEdit, new Page.WaitForSelectorOptions().setTimeout(5000).setState(WaitForSelectorState.VISIBLE));
        page.locator(careerProfileLineEdit).click();

        Locator submitBtn = page.locator(careerProfileLineSubmit);
        submitBtn.scrollIntoViewIfNeeded();

        // Wait until the button is visible and enabled (extra reliable)
        submitBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        page.waitForTimeout(1000); // Give UI a second to settle if needed
        submitBtn.click();

        try {
            page.waitForSelector(successMsg, new Page.WaitForSelectorOptions().setTimeout(5000).setState(WaitForSelectorState.VISIBLE));
            String message = page.locator(successMsg).innerText().trim();
            waitForSuccessMessageToDisappear();
            return message;
        } catch (Exception e) {
            System.out.println("Success message disappeared too fast or not visible!");
            return "No success message captured";
        }
    }


    public String updatePersonalDetails() {
        page.locator(personalDetailsLine).click();

        // Wait until the edit icon is visible and clickable
        page.waitForSelector(personalDetailsLineEdit, new Page.WaitForSelectorOptions()
                .setTimeout(5000)
                .setState(WaitForSelectorState.VISIBLE));
        page.locator(personalDetailsLineEdit).click();

        // Scroll to the submit button and wait for it to stabilize
        Locator submitBtn = page.locator(personalDetailsLineSubmit);
        submitBtn.scrollIntoViewIfNeeded();

        // Wait until the button is visible and enabled
        submitBtn.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));

        // Give it a moment for UI transitions (important on Naukri UI)
        page.waitForTimeout(1000);

        // Try to click
        submitBtn.click();

        try {
            // Wait for the success message
            page.waitForSelector(successMsg, new Page.WaitForSelectorOptions()
                    .setTimeout(5000)
                    .setState(WaitForSelectorState.VISIBLE));
            String message = page.locator(successMsg).innerText().trim();
            waitForSuccessMessageToDisappear();
            return message;
        } catch (Exception e) {
            System.out.println("Success message disappeared too fast or not visible!");
            return "No success message captured";
        }
    }

}






