package com.qa.Naukri.Tests;

import com.qa.Naukri.BaseTest.BaseTest;
import com.qa.Naukri.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProfilePageTest extends BaseTest {

    @BeforeClass
    public void profilePageSetUp() {
        homePage = loginPage.loginUser(); // LOGIN FIRST
        homePage.waitForHomePageToLoad();
        profilePage = homePage.navigateProfilePage(); // THEN GO TO PROFILE
        profilePage.waitForProfilePageToLoad();
    }

    @Test(priority = 1)
    public void uploadResumeTest() {
        String filePath = "C:\\Users\\pkashyap\\OneDrive - Comscore\\Desktop\\HR\\ISQBT\\Pranav Kashyap_Automation.pdf";
        String resumeSuccessMsg = profilePage.uploadResume(filePath);
        System.out.println("Resume Update Message ----> " + resumeSuccessMsg);
        Assert.assertEquals(resumeSuccessMsg, AppConstants.PROFILE_PAGE_RESUME_MSG);
    }

    @Test(priority = 2)
    public void updateResumeHeaderTest(){
        profilePage.waitForResumeHeaderToLoad();
        String headerSuccessMsg = profilePage.updateResumeHeadline();
        System.out.println("Resume Headline Update Message ----> " + headerSuccessMsg);
        Assert.assertEquals(headerSuccessMsg, AppConstants.PROFILE_PAGE_RESUME_HEADLINE_MSG);
    }

    @Test(priority = 3)
    public void updateKeySkillsTest(){
        profilePage.waitForKeySkillsToLoad();
        String skillsSuccessMsg = profilePage.updateKeySkills();
        System.out.println("Key Skills Update Message ----> " + skillsSuccessMsg);
        Assert.assertEquals(skillsSuccessMsg, AppConstants.KEY_SKILLS_MSG);
    }

    @Test(priority = 4)
    public void updateCareerProfileTest(){
        profilePage.waitForCareerProfileToLoad();
        String careerSuccessMsg = profilePage.updateCareerProfile();
        System.out.println("Career Profile Update Message ----> " + careerSuccessMsg);
        Assert.assertEquals(careerSuccessMsg,AppConstants.CAREER_PROFILE_MSG);
    }

    @Test(priority = 5)
    public void updatePersonalDetailsTest(){
        profilePage.waitForPersonalDetailsToLoad();
        String personalDetailsMsg = profilePage.updatePersonalDetails();
        System.out.println("Career Profile Update Message ---->  "+ personalDetailsMsg);
        Assert.assertEquals(personalDetailsMsg,AppConstants.Personal_DETAILS_MSG);
    }

    @AfterMethod
    public void addWaitBetweenTests() throws InterruptedException {
        Thread.sleep(5000); // Wait for 5 seconds after each @Test
    }
}
