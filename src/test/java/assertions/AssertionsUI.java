package assertions;


import com.codeborne.selenide.Condition;
import helpers.ScreenshotHelper;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import static api.ScenarioContext.ContextEnum.DOWNLOADED_FILE;
import static api.ScenarioContext.ContextEnum.SCREENSHOT;
import static api.ScenarioContext.getContext;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.title;
import static helpers.ActionHelper.*;
import static helpers.ScreenshotHelper.SCREENSHOT_FOLDER;
import static helpers.ScreenshotHelper.takeElementScreenshot;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.ashot.util.ImageBytesDiffer.areImagesEqual;

public class AssertionsUI {

    private static SoftAssertions assertion = new SoftAssertions();
    public static boolean softAssertion;

    public static void checkTitle(String title) {
        if (softAssertion) {
            assertion.assertThat(title().equals(title)).isTrue().as("Another title is displayed");
        } else {
            assertEquals("Another title is displayed" + title, title(), title);
        }
    }

    public static void checkElementText(String locator, String expectedText) {
        if (softAssertion) {
            assertion.assertThat(getElement(locator).getText().equals(expectedText)).isTrue();
        } else {
            getElement(locator).shouldHave(text(expectedText));
        }
    }

    public static void checkElementIsDisplayed(String locator, boolean displayed) {
        if (softAssertion) {
            assertion.assertThat(displayed ? getElement(locator).isDisplayed() :
                    !getElement(locator).isDisplayed()).isTrue();
        } else {
            getElement(locator).shouldBe(displayed ? visible : not(visible));
        }
    }

    public static void checkCheckBox(String locator, boolean checked) {
        if (softAssertion) {
            assertion.assertThat(checked ? getElement(locator).isSelected()
                    : !getElement(locator).isSelected()).isTrue();
        } else {
            if (checked) {
                getElement(locator).shouldBe(Condition.checked);
            } else {
                getElement(locator).shouldNotBe(Condition.checked);
            }
        }
    }

    @SneakyThrows
    public static void checkFilesIsEquels(File actualFile, File expectedFile) {
        if (softAssertion) {
            assertion.assertThat(FileUtils.contentEquals(actualFile, expectedFile)).isTrue()
                    .as("Content of the files are different!");
        } else {
            assertTrue("Content of the files are different!", FileUtils.contentEquals(actualFile, expectedFile));
        }
    }

    public static void checkFileExist(String fileName) {
        File file = getContext(DOWNLOADED_FILE);
        String name = file.getName();
        if (softAssertion) {
            assertion.assertThat(name.equals(fileName)).isTrue()
                    .as("Downloaded file has different name.");
            assertion.assertThat(file.exists()).isTrue().as("File does not exist!");
        } else {
            assertEquals("Downloaded file has different name", name, fileName);
            assertTrue("File does not exist!", file.exists());
        }
    }

    @SneakyThrows
    public static void checkFileContainsText(String text, String... filePath) {
        File file = filePath.length == 0 ? getContext(DOWNLOADED_FILE) : new File(filePath[0]);
        boolean flag = false;
        Scanner sc2 = new Scanner(new FileInputStream(file));
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();
            if (line.indexOf(text) != -1) {
                flag = true;
            }
        }
        if (softAssertion) {
            assertion.assertThat(flag).isTrue().as("File does not contains %s", text);
        } else {
            assertTrue(String.format("File does not contains %s", text), flag);
        }
    }

    @SneakyThrows
    private static BufferedImage createImage(String screenshotPath) {
        Image image = ImageIO.read(new File(SCREENSHOT_FOLDER + screenshotPath + ".png"));
        return (BufferedImage) image;
    }

    public static void checkScreenshotsByPath(String actualScreenshotPath, String expectedScreenshotPath) {
        BufferedImage actual = createImage(actualScreenshotPath);
        BufferedImage expected = createImage(expectedScreenshotPath);
        if (softAssertion) {
            assertion.assertThat(areImagesEqual(actual, expected)).isTrue()
                    .as("Screenshots are different");
        } else {
            assertTrue(areImagesEqual(actual, expected));
        }
    }

    public static void checkScreenshotsByPathAndLocator(String actualScreenshotPath, String locator) {
        BufferedImage expected = createImage(actualScreenshotPath);
        BufferedImage actual = takeElementScreenshot(getLocator(locator));

        if (softAssertion) {
            assertion.assertThat(areImagesEqual(actual, expected)).isTrue()
                    .as("Screenshots are different");
        } else {
            assertTrue(areImagesEqual(actual, expected));
        }
    }

    public static void checkCountOfElemnts(String locator, int count) {
        int actualSize = getListOfElements(locator).size();
        if (softAssertion) {
            assertion.assertThat(actualSize == count).isTrue()
                    .as("Collection size is %d", count);
        } else {
            assertTrue(getListOfElementsContains(locator).size() == count);
        }
    }

    public static void checkAllAssertion() {
        assertion.assertAll();
    }
}
