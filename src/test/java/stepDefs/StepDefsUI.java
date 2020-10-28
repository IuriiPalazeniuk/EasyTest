package stepDefs;

import api.ScenarioContext.ContextEnum;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

import static api.ScenarioContext.getContext;
import static assertions.AssertionsUI.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.ActionHelper.*;
import static helpers.FileHelper.*;
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

    /**
     * Navigate to appropriate url.
     *
     * @example: User navigates to "http://oursite.com"
     */
    @Given("User navigates to {string}")
    public void userNavigatesToURL(String url) {
        open(url);
    }

    /**
     * Print additional details into test log.
     *
     * @param message - string to be printed in log
     * @example: User prints comment "Click on Submit button"
     */
    @Given("User prints comment {string}")
    public void userPrintsComment(String message) {
        printIntoConsole(message);
    }

    /**
     * Click element by CSS or XPATH.
     *
     * @param elem    - element description
     * @param locator - css or xpath selector
     * @example: User clicks Search button "#searchButton"
     */
    @When("User clicks {string} {string}")
    public void userClicksOnElementByLocator(String elem, String locator) {
        log.info("User clicks on " + elem);
        clickOnElement(locator);
    }

    /**
     * Click browser back button.
     *
     * @example: User clicks browser back button
     */
    @When("User clicks browser back button")
    public void userClicksBrowserBackPage() {
        back();
    }

    /**
     * Click browser forward button.
     *
     * @example: User clicks browser forward button
     */
    @When("User clicks browser forward button")
    public void userClicksBrowserBackButton() {
        forward();
    }

    /**
     * Click element by CSS or XPATH by executing script.
     *
     * @param detail  - element description
     * @param locator - css or xpath selector
     * @example: User clicks Search button "#searchButton" by executing script
     */
    @When("User clicks {string} {string} by executing script")
    public void userClicksByExecutingScript(String detail, String locator) {
        log.info("User click " + detail);
        clickOnElementByJS(locator);
    }

    /**
     * Click element contains a certain string.
     *
     * @param text
     * @example: User clicks on element contains "Chapter 5"
     */
    @When("User clicks on element contains {string} text")
    public void userClicksOnElementContainsTextText(String text) {
        clickOnElementContainsText(text);
    }

    /**
     * Double click element by CSS or XPATH.
     *
     * @param details - element description
     * @param locator - css or xpath selector
     * @example: User double clicks Search button "#searchButton"
     */
    @When("User double clicks {string} {string}")
    public void userDoubleClicksDetailsLocator(String details, String locator) {

        log.info("user double clicks " + details + " with " + locator + " locator");
        doubleClickOnElement(locator);
    }

    /**
     * Enter text in text field by CSS or XPATH.
     * NOTE: Because of issues in components in FF , we have to send text by chars
     *
     * @param text
     * @param details - element description
     * @param locator - css or xpath selector
     * @example: User enters "test" in Search field "#search-field"
     */
    @When("User enters {string} in {string} {string}")
    public void userEntersTextInDetailLocator(String text, String details, String locator) {
        String locator1 = locator.contains("|") ? getVariable(locator) : locator;
        log.info("user enters " + text + " in " + details + " with " + locator + " locator");
        fillInInputField(locator1, text);
    }

    /**
     * Clear text from text field by CSS or XPATH.
     *
     * @param details - element description
     * @param locator - css or xpath selector
     * @example: User clears text from Search field "#search-field"
     */
    @When("User clears text from {string} {string}")
    public void userClearsTextFromDetailLocator(String details, String locator) {
        log.info("clear text from " + details + " with locator " + locator + "");
        clearText(locator);
    }

    /**
     * Move mouse over element by CSS or XPATH.
     *
     * @param details - element description
     * @param locator - css or xpath selector
     * @example: User moves mouse over Input field "#input-field"
     */
    @And("User moves mouse over {string} {string}")
    public void userMovesMouseOverDetailLocator(String details, String locator) {
        log.info("moves mouse over " + details + " with locator " + locator + "");
        moveToElement(locator);
    }

    /**
     * Refresh browser page.
     *
     * @example: User refreshes page
     */
    @And("User refreshes page")
    public void userRefreshesPage() {
        refresh();
    }

    /**
     * Check title
     *
     * @param title
     * @example: Check 'Cart' title
     */
    @Then("Check {string} title")
    public void userCheckTitle(String title) {
        checkTitle(title);
    }

    /**
     * Check element contains text by CSS or XPATH.
     *
     * @param text
     * @param locator - css or xpath selector
     * @example: Check 'Search' text of element "//button[@name='search']"
     */
    @Then("Check {string} text of element {string}")
    public void userCheckTextOfElement(String text, String locator) {
        checkElementText(text, locator);
    }

    /**
     * Check element by CSS or XPATH is displayed (not displayed)
     *
     * @param displayed - displayed or not displayed
     * @param locator   - css or xpath selector
     * @example: Check element "//button[@name='search']" is not displayed
     */
    @Then("Check element {string} is {string}")
    public void userCheckElementIsDisplayed(String locator, String displayed) {
        checkElementIsDisplayed(locator, displayed.equals("displayed") ? true : false);
    }

    /**
     * Select radio button element by CSS or XPATH with text
     *
     * @param text
     * @param locator - css or xpath selector
     * @example: User selects radio button "//div[@class='date']" with text 'year'
     */
    @When("User selects radio button {string} with text {string}")
    public void userSelectsRadioButtonLocatorWithTextText(String locator, String text) {
        selectRadioButton(locator, text);
    }

    /**
     * Select dropdown element by CSS or XPATH with text
     *
     * @param text
     * @param locator - css or xpath selector
     * @example: User selects dropdown "//div[@class='date']" with text 'year'
     */
    @When("User selects dropdown {string} with text {string}")
    public void userSelectsDropdownLocatorWithTextText(String locator, String text) {
        selectDropDown(locator, text);
    }

    /**
     * Wait needed amount of milliseconds.
     *
     * @example: User waits 1000 milliseconds
     */
    @When("User waits {long} milliseconds")
    public void userWaitsMilliseconds(long milliSeconds) {
        sleep(milliSeconds);
    }

    /**
     * Check checkbox by CSS or XPATH
     *
     * @param checkBox - 'checked' or 'unchecked'
     * @param locator  - css or xpath selector
     * @example: Check checkbox "//div[@class='date']" is 'checked'
     */
    @Then("Check checkbox {string} is {string}")
    public void userCheckCheckboxIsChecked(String locator, String checkBox) {
        checkCheckBox(locator, checkBox == "checked" ? true : false);
    }

    /**
     * Download file
     *
     * @param url - file's link
     * @example: User downloads file 'https://linkToThefile'
     */
    @When("User downloads file {string}")
    public void userDownloadsFile(String url) {
        downloadFile(url);
    }

    /**
     * Verify if file with appropriate name is exist.
     *
     * @param fileName - file name.
     * @example Check downloaded file with name 'Document.doc' exists"
     */
    @Then("Check downloaded file with name {string} exists")
    public void checkDownloadedFileWithNameTextExists(String fileName) {
        checkFileExist(fileName);
    }

    /**
     * Upload file.
     *
     * @param detail - file name.
     * @param filePath - path to the file.
     * @param details - upload field.
     * @param locator - CSS or XPATH.
     * @example User uploads 'Document' 'src/test/resources/CV.doc' using 'Input field' "//input[@name='cv_direct']"
     */
    @When("User uploads {string} {string} using {string} {string}")
    public void userUploadsFileUsingLocator(String detail, String filePath, String details, String locator) {
        log.info("user uploads file " + detail + " by " + details + "");
        uploadFileByPath(locator, replaceFileSeparator(filePath));
    }

    /**
     * Verify that downloaded file is equals to expected file.
     *
     * @param filePath - path to the expected file.
     * @example  Check downloaded file is equals to 'src/test/resources/CV.doc' file
     */
    @When("Check downloaded file is equals to {string} file")
    public void checkDownloadedFileIsEqualsToFile(String filePath) {
        File downloadedFile = getContext(ContextEnum.DOWNLOADED_FILE);
        checkFilesIsEquels(downloadedFile, new File(filePath));
    }

    /**
     * Verify that files are equals.
     *
     * @param filePath1 - path to the expected file.
     * @param filePath2 - path to the actual file.
     * @example Then Check files 'src/test/resources/CV1.doc' and 'src/test/resources/CV2.doc' are equals
     */
    @Then("Check files {string} and {string} are equals")
    public void checkFilesPathAndPathAreEquals(String filePath1, String filePath2) {
        checkFilesIsEquels(new File(filePath1), new File(filePath2));
    }

    /**
     * Verify that downloaded file contains text.
     *
     * @param text
     * @example Then Check downloaded file contains 'SOFTWARE TEST AUTOMATION ENGINEER'
     */
    @Then("Check downloaded file contains {string}")
    public void checkDownloadedFileContainsText(String text) {
        checkFileContainsText(text);
    }

    /**
     * Verify that file by path contains text.
     *
     * @param text
     * @param filePath - path to the file
     * @example Then Check file 'src/test/resources/document.doc' contains 'SOFTWARE TEST AUTOMATION ENGINEER'
     */
    @Then("Check file {string} contains {string}")
    public void checkFileContains(String filePath, String text) {
        checkFileContainsText(text, filePath);
    }

    /**
     * Click on item in collection
     *
     * @param index number of element in collection that should be clicked - started from 1
     * @param locator upload form css or xpath selector
     * @example:
     * When User clicks on 2 item in "cssOrXpathSelector" collection
     */
    @When("User clicks on {int} item in {string} collection")
    public void userClicksItemInLocatorCollection(int index, String locator) {
        clickOnElementFromCollectionBy(index - 1, locator);
    }

    /**
     * Click on item in collection by CSS or XPATH and child text
     *
     * @param index number of element in collection that should be clicked - started from 1
     * @param locator upload form css or xpath selector
     * @param text child element contains text
     * @example:
     * When User clicks on 2 item in "cssOrXpathSelector" collection
     */
    @When("User clicks on {int} item in {string} collection with child text {string}")
    public void userClicksItemInCollectionWithText(int index, String locator, String text) {
        clickOnChildElementFromCollectionByText(index - 1, locator, text);
    }

    /**
     * Scroll to element
     *
     * @param locator upload form css or xpath selector
     * @example:
     * When User scroll to "cssOrXpathSelector" element
     */
    @When("User scroll to {string} element")
    public void userScrollToLocatorElement(String locator) {
        scrollToElement(locator);
    }

    /**
     * Assert all step (must be added to scenario with @softAssertion tag)
     *
     * @example:
     * Then Check all assertions
     */
    @Then("Check all assertions")
    public void checkAllAssertions() {
        checkAllAssertion();
    }

    /**
     * Press Enter key in the element.
     *
     * @example:
     * User presses Enter key in the 'field' "loginPage|passwordInput"
     *
     * @param detail - element description
     * @param locator - css or xpath selector
     *
     */
    @When("User presses Enter key in {string} {string}")
    public void userPressesEnterKeyInDetail(String detail, String locator) {
        log.info("User presses Enter key in " + detail + "");
        pressEnter(locator);
    }

    /**
     * Take element's screenshot. (Screenshots are located in 'project/screenshots' folder with timestamp.png name)
     *
     * @example:
     * When User takes screenshot of 'search field' "//dd[@class='panel-0 cycle-slide active']"
     *
     * @param detail - element description
     * @param locator - css or xpath selector
     *
     */
    @When("User takes screenshot of {string} {string}")
    public void userTakesScreenshotOfDetailsLocator(String detail, String locator) {
        log.info("User takes screenshot of " + detail);
        takeElementScreenshot(getLocator(locator));
    }

    /**
     * Take element's screenshot. (Screenshots are located in 'project/screenshots' folder with providedName.png file name)
     *
     * @example:
     * When User takes screenshot of 'search field' "//dd[@class='panel-0 cycle-slide active']" and set 'searchField' name
     * (screenshot will be saved in project/screenshots/searchField.png)
     *
     * @param detail - element description
     * @param locator - css or xpath selector
     * @param screenshotName - screenshot name
     *
     */
    @When("User takes screenshot of {string} {string} and set {string} name")
    public void userTakesScreenshotOfDetailsWithNameName(String detail, String locator, String screenshotName) {
        log.info("User takes screenshot of " + detail + " with " + screenshotName + " name");
        takeElementScreenshot(getLocator(locator), screenshotName);
    }

    /**
     * Click on screenshot. (Screenshots are located in 'project/screenshots' folder)
     *
     * @example:
     * When User clicks on 'searchField' screenshot
     * (screenshot is located in project/screenshots/searchField.png)
     *
     * @param screenshotName - screenshot name
     *
     */
    @When("User clicks on {string} screenshot")
    public void userClicksOnCareer_linkScreenshot(String screenshotName) {
        clickOnScreenshot(screenshotName);
    }


    /**
     * Take screenshot of some whole page. (Screenshots are located in 'project/screenshots' folder)
     *
     * @example:
     * When User takes screenshot of 'Login' page and set 'LoginPage' name
     * (screenshot is located in project/screenshots/LoginPage.png)
     * @param detail - name of page
     * @param screenshotName - screenshot name
     *
     */
    @When("User takes screenshot of {string} page and set {string} name")
    public void userTakesScreenshotOfPageNamePageAndSetName(String detail, String screenshotName) {
        log.info("User takes screenshot of " + detail + " with " + screenshotName + " name");
        takePageScreenshot(screenshotName);
    }

    /**
     * Take screenshot of some whole page. (Screenshots are located in 'project/screenshots' folder)
     *
     * @example:
     * When User takes screenshot of 'Login' page
     * Timestamp will be name of the screenshot
     * (screenshot is located in project/screenshots/LoginPage.png)
     * @param detail - name of page
     *
     */
    @When("User takes screenshot of {string} page")
    public void userTakesScreenshotOfPageNamePage(String detail) {
        log.info("User takes screenshot of " + detail + "");
        takePageScreenshot();
    }

    /**
     * Wait some time for screenshot
     *
     * @example:
     * When User waits 5 seconds for screenshot element 'login' appears
     *
     * @param seconds - seconds
     * @param screenshotName - name of screenshot from 'projectName/screenshots/screenshotName.png'
     *
     */
    @When("User waits {int} seconds for screenshot element {string} appears")
    public void userWaitsSecondForScreenshotAppears(double seconds, String screenshotName) {
        waitForScreenshotElement(screenshotName, seconds);
    }

    /**
     * Type text into element by screenshot
     *
     * @example:
     * When User types 'somePassword' into 'password' input field screenshot
     *
     * @param text
     * @param screenshotName - name of screenshot from 'projectName/screenshots/screenshotName.png'
     *
     */
    @When("User types {string} into {string} input field screenshot")
    public void userTypesTextIntoScreenshotInputField(String text, String screenshotName) {
        typeTextInScreenshotElement(screenshotName, text);
    }

    /**
     * Screenshots verification
     *
     * @example:
     * When Check 'actualScreenshot' and 'expectedScreenshot' screenshots are the same
     *
     * @param actualScreenshot - name of screenshot from 'projectName/screenshots/actualScreenshot.png'
     * @param expectedScreenshot - name of screenshot from 'projectName/screenshots/expectedScreenshot.png'
     *
     */
    @Then("Check {string} and {string} screenshots are the same")
    public void checkScreenAndScreenshotScreenshotsAreTheSame(String actualScreenshot, String expectedScreenshot) {
        checkScreenshotsByPath(actualScreenshot, expectedScreenshot);
    }

    /**
     * Screenshots verification
     *
     * @example:
     * When Check 'screenshotName' and screenshot by "(//a[contains(text(),'Careers')])[2]" are the same
     *
     * @param screenshotName - name of screenshot from 'projectName/screenshots/screenshotName.png'
     * @param locator - locator to the element
     *
     */
    @Then("Check {string} and screenshot by {string} are the same")
    public void checkScreenshotPathAndScreenshotByLocatorAreTheSame(String screenshotName, String locator) {
        checkScreenshotsByPathAndLocator(screenshotName, locator);
    }

    /**
     * Wait some time for element
     *
     * @example:
     * When User waits 5 seconds for 'search input filed' is visible by 'CSS or XPATH'
     *
     * @param seconds
     * @param detail - element description
     * @param locator - locator to the element
     *
     */
    @When("User waits {int} seconds for {string} is visible by {string}")
    public void userWaitsSecondsForElementIsVisibleByLocator(int seconds, String detail, String locator) {
        log.info("User is waiting for " + detail + " element");
        waitForElement(locator, seconds);
    }

    /**
     * Collection size verification
     *
     * @example:
     * Then Check size of 'CSS or XPATH' collection is 5
     *
     * @param locator- element description
     * @param size - expected size of collection
     *
     */
    @Then("Check size of {string} collection is {int}")
    public void checkSizeOfLocatorCollection(String locator, int size) {
        checkCountOfElemnts(locator, size);
    }
}
