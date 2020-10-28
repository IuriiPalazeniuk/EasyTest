package helpers;

import enums.PageEnum;
import lombok.extern.slf4j.Slf4j;
import pages.BasePage;
import pages.LogInPage;
import pages.MainPage;
import pages.ResultPage;

import static enums.PageEnum.getPageWithName;

@Slf4j
public class PageHelper {

    private static BasePage basePage;

    public static BasePage getPage(PageEnum pageName) {
        switch (pageName) {
            case MAIN:
                basePage = new MainPage();
                break;
            case LOGIN:
                basePage = new LogInPage();
                break;
            case RESULT:
                basePage = new ResultPage();
                break;
            default:
                log.info("Page is not created!");
        }
        return basePage;
    }

    public static String[] getPageObject(String text) {
        return text.split("\\|");
    }

    public static String getVariableFromClass(String[] classPage) {
        String pageName = classPage[0];
        String variableName = classPage[1];
        BasePage page = getPage(getPageWithName(pageName));
        log.info("Page " + page.getClass().toString() + " is initialized.");
        return page.getVariable(variableName);
    }

    public static String getVariable(String locator) {
        String[] classPage = getPageObject(locator);
        return getVariableFromClass(classPage);
    }

}
