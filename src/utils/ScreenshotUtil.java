package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver,
            String screenshotName) {

        String folderPath = "Evidence";

        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File source =
                ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destination =
                new File(
                        folderPath
                        + File.separator
                        + screenshotName
                        + ".png"
                );

        try {

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot",
                    e
            );
        }

        return destination.getAbsolutePath();
    }
}