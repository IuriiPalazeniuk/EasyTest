package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Slf4j
public class ActionHelper {

    public static long timeoutMilliSeconds = 2000;

    public static By getLocator(String locator) {
        return locator.startsWith("//") | locator.startsWith("./") | locator.startsWith("(./") | locator.startsWith("(//")
                ? By.xpath(locator) : By.cssSelector(locator);
    }

    public static SelenideElement getElement(String locator) {
        return $(getLocator(locator)).waitUntil(Condition.visible, timeoutMilliSeconds);
    }

    public static SelenideElement getElementByText(String text) {
        if (getListOfElements("//*[contains(text(), '" + text + "')]").size() > 1) {
            throw new NotFoundException("There are another elements with " + text + ".\n" +
                    "Please provide better locator");
        }
        return $(getLocator("//*[contains(text(), '" + text + "')]"))
                .waitUntil(Condition.visible, timeoutMilliSeconds);
    }

    public static List<SelenideElement> getListOfElements(String locator) {
        return $$(getLocator(locator));
    }

    public static List<SelenideElement> getListOfElementsByLocatorAndContainsChildText(String locator, String text) {
        return getElement(locator).findAll(By.xpath("./*[contains(text(),'" + text + "')]"));
    }

    public static List<SelenideElement> getListOfElementsContains(String text) {
        return $$(getLocator("//*[contains(text(),'" + text + "')]"));
    }

    public static List<SelenideElement> getListOfElementsByLocator(String locator) {
        return $$(getLocator(locator));
    }

    public static void clickOnElement(String locator) {
        getElement(locator).click();
    }

    public static void printIntoConsole(String message) {
        log.info(message);
    }

    public static void fillInInputField(String locator, String text) {
        getElement(locator).setValue(text);
    }

    public static void clickOnElementContainsText(String text) {
        getElementByText(text);
    }

    public static void clickOnElementByJS(String locator) {
        SelenideElement elem = getElement(locator);
        executeJavaScript("arguments[0].click()", elem);
    }

    public static void doubleClickOnElement(String locator) {
        getElement(locator).doubleClick();
    }

    public static void clearText(String locator) {
        getElement(locator).clear();
    }

    public static void moveToElement(String locator) {
        SelenideElement element = getElement(locator);
        actions().moveToElement(element);
    }

    public static void selectRadioButton(String locator, String text) {
        getElement(locator).selectRadio(text);
    }

    public static void selectDropDown(String locator, String text) {
        getElement(locator).selectOption(text);
    }

    public static void pressEnter(String locator) {
        getElement(locator).pressEnter();
    }

    public static void clickOnElementFromCollectionBy(int index, String locator) {
        getListOfElements(locator).get(index).click();
    }

    public static void clickOnChildElementFromCollectionByText(int index, String locator, String text) {
        getListOfElementsByLocatorAndContainsChildText(locator, text).get(index).click();
    }

    public static void scrollToElement(String locator) {
        getElement(locator).scrollTo();
    }

    public static void waitForElement(String locator, int seconds){
        WebDriverWait wait = new WebDriverWait(getWebDriver(),seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
    }

}
