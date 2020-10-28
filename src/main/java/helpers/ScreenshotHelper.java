package helpers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.FileHelper.*;

@Getter
public class ScreenshotHelper {

    public static final String SCREENSHOT_FOLDER = "screenshots" + FILE_SEPARATOR + OS + FILE_SEPARATOR +
            getBrowserName() + "_" + getBrowserVersion() + FILE_SEPARATOR;
    public static final String IMAGE_NAME = "%s.png";

    public static long getTimeStamp() {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }

    private static String pathOfScreenshot(String name) {
        return SCREENSHOT_FOLDER + name + ".png";
    }

    @SneakyThrows
    private static void createFolders() {
        Path path = Paths.get(SCREENSHOT_FOLDER);
        Files.createDirectories(path);
    }

    @SneakyThrows
    public static BufferedImage takeElementScreenshot(By by, String... screenshotName) {
        String name = screenshotName.length == 0 ? String.valueOf(getTimeStamp()) : screenshotName[0];
        BufferedImage image = takeElementImage($(by));
        createFolders();
        ImageIO.write(image, "png", new File(pathOfScreenshot(name)));
        return image;
    }

    private static BufferedImage takeElementImage(WebElement webElement) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getWebDriver(), webElement);
        return screenshot.getImage();
    }


    @SneakyThrows
    public static void takePageScreenshot(String... screenshotName) {
        String name = screenshotName.length == 0 ? String.valueOf(getTimeStamp()) : screenshotName[0];
        Screenshot aShot = new AShot().takeScreenshot(getWebDriver());
        BufferedImage image = aShot.getImage();
        createFolders();
        ImageIO.write(image, "png", new File(pathOfScreenshot(name)));
    }

    @SneakyThrows
    public static void clickOnScreenshot(String screenshotName) {
        ImagePath.add(SCREENSHOT_FOLDER);
        Screen s = new Screen();
        Pattern element = new Pattern(String.format(IMAGE_NAME, screenshotName));
        s.click(element);
    }

    @SneakyThrows
    public static void waitForScreenshotElement(String screenshotName, double timeout) {
        ImagePath.add(SCREENSHOT_FOLDER);
        Screen s = new Screen();
        Pattern element = new Pattern(String.format(IMAGE_NAME, screenshotName));
        s.wait(element, timeout);
    }

    @SneakyThrows
    public static void typeTextInScreenshotElement(String screenshotName, String text) {
        ImagePath.add(SCREENSHOT_FOLDER);
        Screen s = new Screen();
        Pattern element = new Pattern(String.format(IMAGE_NAME, screenshotName));
        s.type(element, text);
    }
}
