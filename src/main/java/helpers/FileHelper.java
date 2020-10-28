package helpers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static api.ScenarioContext.ContextEnum.DOWNLOADED_FILE;
import static api.ScenarioContext.setContext;
import static com.codeborne.selenide.Selenide.download;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.ActionHelper.getElement;

@Slf4j
public class FileHelper {
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String OS = System.getenv("OS");

    public static String getBrowserName() {
        return getCapabilities().getBrowserName();
    }

    public static String getBrowserVersion() {
        return (String) getCapabilities().getCapability("browserVersion");
    }

    public static Capabilities getCapabilities() {
        return ((RemoteWebDriver) getWebDriver()).getCapabilities();
    }

    @SneakyThrows
    public static File downloadFile(String url) {
        File file = download(url);
        String filePath = file.getPath();
        log.info(String.format("File path fo downloaded file is %s", filePath));
        setContext(DOWNLOADED_FILE, file);
        return file;
    }

    public static void uploadFileByPath(String locator, String filePath) {
        getElement(locator).uploadFile(new File(filePath));
    }

    @SneakyThrows
    public static String readFileAsString(String file) {
        return replaceDoubleSpaces(new String(Files.readAllBytes(Paths.get(file)))
                .replace("\r", ""));
    }

    public static String replaceDoubleSpaces(String text) {
        return text.replaceAll(" +", " ");
    }

    public static String replaceFileSeparator(String filePath) {
        return !System.getenv("OS").equals("Windows_NT") ?
                filePath.replace("\\", "/") : filePath;
    }
}
