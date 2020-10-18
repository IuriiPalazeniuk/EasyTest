package stepDefs;

import api.ScenarioContext.ContextEnum;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static api.ScenarioContext.getContext;
import static assertions.AssertionsUI.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.ActionHelper.*;
import static helpers.FileHelper.downloadFile;
import static helpers.FileHelper.uploadFileByPath;
import static helpers.PageHelper.getVariable;
import static helpers.ScreenshotHelper.*;


@Slf4j
public class StepDefsUI {

    @Before("@softAssertion")
    public void initializeSoftAssertion() {
        softAssertion = true;
    }

    @Before
    public void configPreparation() {
        Configuration.screenshots = false;
        Configuration.startMaximized = true;
        Configuration.timeout = System.getProperty("timeout") == null ? 4000
                : Long.parseLong(System.getProperty("timeout"));
    }

    @Given("User navigates to {string}")
    public void userNavigatesToURL(String url) {
        open(url);
    }

    @Given("User prints comment {string}")
    public void userPrintsComment(String message) {
        printIntoConsole(message);
    }

    @When("User clicks {string} {string}")
    public void userClicksOnElementByLocator(String elem, String locator) {
        log.info("User clicks on " + elem);
        clickOnElement(locator);
    }

    @When("User clicks browser back page")
    public void userClicksBrowserBackPage() {
        back();
    }

    @When("User clicks browser forward page")
    public void userClicksBrowserBackButton() {
        forward();
    }

    @When("User refresh page")
    public void userRefreshPage() {
        refresh();
    }

    @When("User clicks {string} {string} by executing script")
    public void userClicksByExecutingScript(String locator) {
        clickOnElementByJS(locator);
    }

    @When("User clicks on element contains {string} text")
    public void userClicksOnElementContainsTextText(String text) {
        clickOnElementContainsText(text);
    }

    @When("User double clicks {string} {string}")
    public void userDoubleClicksDetailsLocator(String details, String locator) {

        log.info("user double clicks " + details + " with " + locator + " locator");
        doubleClickOnElement(locator);
    }

    @When("User enters {string} in {string} {string}")
    public void userEntersTextInDetailLocator(String text, String details, String locator) {
        String locator1 = locator.contains("|") ? getVariable(locator) : locator;
        log.info("user enters " + text + " in " + details + " with " + locator + " locator");
        fillInInputField(locator1, text);
    }

    @When("User clears text from {string} {string}")
    public void userClearsTextFromDetailLocator(String details, String locator) {
        log.info("clear text from " + details + " with locator " + locator + "");
        clearText(locator);
    }

    @And("User moves mouse over {string} {string}")
    public void userMovesMouseOverDetailLocator(String details, String locator) {
        log.info("moves mouse over " + details + " with locator " + locator + "");
        moveToElement(locator);
    }

    @And("User refreshes page")
    public void userRefreshesPage() {
        refresh();
    }

    @Then("Check {string} title")
    public void userCheckTitle(String title) {
        checkTitle(title);
    }

    @Then("Check {string} text of element {string}")
    public void userCheckTextOfElement(String text, String locator) {
        checkElementText(text, locator);
    }

    @Then("Check element {string} is {string}")
    public void userCheckElementIsDisplayed(String locator, String displayed) {
        checkElementIsDisplayed(locator, displayed.equals("displayed") ? true : false);
    }

    @When("User selects radio button {string} with text {string}")
    public void userSelectsRadioButtonLocatorWithTextText(String locator, String text) {
        selectRadioButton(locator, text);
    }

    @When("User selects dropdown {string} with text {string}")
    public void userSelectsDropdownLocatorWithTextText(String locator, String text) {
        selectDropDown(locator, text);
    }

    @When("User waits {long} milliseconds")
    public void userWaitsMilliseconds(long milliSeconds) {
        sleep(milliSeconds);
    }

    @Then("Check checkbox {string} is {string}")
    public void userCheckCheckboxIsChecked(String locator, String checkBox) {
        checkCheckBox(locator, checkBox == "checked" ? true : false);
    }

    @When("User downloads file {string}")
    public void userDownloadsFile(String url) {
        downloadFile(url);
    }

    @Then("Check downloaded file with name {string} exists")
    public void checkDownloadedFileWithNameTextExists(String fileName) {
        checkFileExist(fileName);
    }

    @When("User uploads {string} {string} using {string} {string}")
    public void userUploadsFileUsingLocator(String detail, String filePath, String details, String locator) {
        log.info("user uploads file " + detail + " by " + details + "");
        uploadFileByPath(locator, filePath);
    }

    @When("Check downloaded file is equals to {string} file")
    public void checkDownloadedFileIsEqualsToFile(String filePath) {
        File downloadedFile = getContext(ContextEnum.DOWNLOADED_FILE);
        checkFilesIsEquels(downloadedFile, new File(filePath));
    }

    @Then("Check files {string} and {string} are equals")
    public void checkFilesPathAndPathAreEquals(String filePath1, String filePath2) {
        checkFilesIsEquels(new File(filePath1), new File(filePath2));
    }

    @Then("Check downloaded file contains {string}")
    public void checkDownloadedFileContainsText(String text) {
        checkFileContainsText(text);
    }

    @Then("Check file {string} contains {string}")
    public void checkFileContains(String filePath, String text) {
        checkFileContainsText(text, filePath);
    }

    @When("User clicks on {int} item in {string} collection")
    public void userClicksItemInLocatorCollection(int index, String locator) {
        clickOnElementFromCollectionBy(index - 1, locator);
    }

    @When("User clicks on {int} item in {string} collection with child text {string}")
    public void userClicksItemInCollectionWithText(int index, String locator, String text) {
        clickOnChildElementFromCollectionByText(index - 1, locator, text);
    }

    @When("User scroll to {string} element")
    public void userScrollToLocatorElement(String locator) {
        scrollToElement(locator);
    }

    @Then("Check all assertions")
    public void checkAllAssertions() {
        checkAllAssertion();
    }

    @When("User presses Enter key in {string} {string}")
    public void userPressesEnterKeyInDetail(String detail, String locator) {
        log.info("User presses Enter key in " + detail + "");
        pressEnter(locator);
    }

    @When("User clicks {string} {string} with text {string} on {string} {string} with text {string}")
    public void userClicksDetailLocatorWithTextTextOnDetailLocatorWithTextText() {

    }

    @When("User clicks {string} {string} on {string} {string} with text {string}")
    public void userClicksDetailLocatorOnDetailLocatorWithTextText() {

    }

    @When("User takes screenshot of {string} {string}")
    public void userTakesScreenshotOfDetailsLocator(String detail, String locator) {
        log.info("User takes screenshot of " + detail + "");
        takeElementScreenshot(getLocator(locator));

    }

    @When("User takes screenshot of {string} {string} and set {string} name")
    public void userTakesScreenshotOfDetailsWithNameName(String detail, String locator, String screenshotName) {
        log.info("User takes screenshot of " + detail + " with " + screenshotName + " name");
        takeElementScreenshot(getLocator(locator), screenshotName);
    }

    @When("User clicks on {string} screenshot")
    public void userClicksOnCareer_linkScreenshot(String screenshotName) {
        clickOnScreenshot(screenshotName);
    }

    @When("User takes screenshot of {string} page and set {string} name")
    public void userTakesScreenshotOfPageNamePageAndSetName(String detail, String screenshotName) {
        log.info("User takes screenshot of " + detail + " with " + screenshotName + " name");
        takePageScreenshot(screenshotName);
    }

    @When("User takes screenshot of {string} page")
    public void userTakesScreenshotOfPageNamePage(String detail) {
        log.info("User takes screenshot of " + detail + "");
        takePageScreenshot();
    }

    @When("User waits {int} seconds for screenshot element {string} appears")
    public void userWaitsSecondForScreenshotAppears(double seconds, String screenshotName) {
        waitForScreenshotElement(screenshotName, seconds);
    }

    @When("User types {string} into {string} input field")
    public void userTypesTextIntoScreenshotInputField(String text, String screenshotName) {
        typeTextInScreenshotElement(screenshotName, text);
    }

    @Then("Check {string} and {string} screenshots are the same")
    public void checkScreenAndScreenshotScreenshotsAreTheSame(String actualScreenshot, String expectedScreenshot) {
        checkScreenshotsByPath(actualScreenshot, expectedScreenshot);
    }

    @Then("Check {string} and screenshot by {string} are the same")
    public void checkScreenshotPathAndScreenshotByLocatorAreTheSame(String screenshotPath, String locator) {
        checkScreenshotsByPathAndLocator(screenshotPath, locator);
    }

    @When("User waits {int} seconds for {string} is visible by {string}")
    public void userWaitsSecondsForElementIsVisibleByLocator(int seconds, String detail, String locator) {
        log.info("User is waiting for " + detail + " element");
        waitForElement(locator, seconds);
    }

    @Then("Check size of {string} collection is {int}")
    public void checkSizeOfLocatorCollection(String locator, int size) {
        checkCountOfElemnts(locator, size);
    }
}
