package helpers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static api.ScenarioContext.ContextEnum.DOWNLOADED_FILE;
import static api.ScenarioContext.setContext;
import static com.codeborne.selenide.Selenide.download;
import static helpers.ActionHelper.getElement;

@Slf4j
public class FileHelper {

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
}
