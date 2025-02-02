import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MultiWindowTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\yandexdriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Lord\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--disable-features=NetworkService");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.get("about:blank");


        String[] urls = {
                "http://www.automationpractice.pl/index.php",
                "https://zoo.waw.pl/",
                "https://www.w3schools.com/",
                "https://www.clickspeedtester.com/click-counter/",
                "https://andersenlab.com/"
        };

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (String url : urls) {
            js.executeScript("window.open('" + url + "', '_blank');");
            Thread.sleep(1000);
        }


        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(windowHandles);


        for (String handle : handlesList) {
            driver.switchTo().window(handle);
            Thread.sleep(2000);
            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Title: " + title);
            System.out.println("URL: " + currentUrl);

            if (title.toLowerCase().contains("zoo") || currentUrl.toLowerCase().contains("zoo")) {
                driver.close();
                System.out.println("Закрыто окно, в котором обнаружено 'Zoo'.");
            }
        }


        if (!driver.getWindowHandles().isEmpty()) {
            driver.switchTo().window(driver.getWindowHandles().iterator().next());
        }
        driver.quit();
    }
}

