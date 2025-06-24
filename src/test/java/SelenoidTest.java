import com.codeborne.selenide.Configuration;
import io.cucumber.core.cli.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.HashMap;

public class SelenoidTest {

    private void runScript(String command) throws IOException, InterruptedException {
        String scriptPath = System.getProperty("user.dir") + "\\src\\test\\resources\\selenoid_manager.bat";

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", scriptPath, command);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
        //sleep(3000);

    }

    private void runScriptClose(String command) throws IOException, InterruptedException {
        String scriptPath = System.getProperty("user.dir") + "\\src\\test\\resources\\selenoid_manager_close.bat";

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", scriptPath, command);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
       // sleep(3000);

    }


    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        runScript("start");

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "128.0";
        Configuration.browserSize = "1920x1080";
        //Configuration.timeout = 15000;

        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "Test badge...");
            put("sessionTimeout", "15m");
            put("enableVNC", true);
        }});
        Configuration.browserCapabilities = options;
    }

    @Test
    public void runCucumberTests() throws Exception {
        byte exitStatus = Main.run(new String[]{
                "--glue", "steps",
                "classpath:features/Rfpl.feature",
                "--plugin", "pretty",
                "--plugin", "html:target/cucumber-report.html"
        }, Thread.currentThread().getContextClassLoader());

        // задержка перед проверкой статуса
        Thread.sleep(10000);

        if (exitStatus != 0) {
            throw new RuntimeException("Тесты не пройдены: " + exitStatus);
        }
    }

    @AfterEach
    public void tearDown() throws IOException, InterruptedException {
        runScript("stop");
        runScriptClose("start");
        Thread.sleep(3000);
        runScriptClose("stop");
    }
}


