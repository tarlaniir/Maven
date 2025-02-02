import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AvatarUploadTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\yandexdriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Lord\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--disable-features=NetworkService");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get("https://qa-course-01.andersenlab.com");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("registereduser@example.com");
        passwordField.sendKeys("UserPassword123");
        WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
        signInButton.click();


        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".w-\\[95px\\]")));


        Actions actions = new Actions(driver);
        actions.moveToElement(avatar).perform();


        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='file'][accept='image/*'].hidden")
        ));
        String imagePath = "C:\\Users\\LORD\\Pictures\\Screenshots\\avataranderson.png";
        fileInput.sendKeys(imagePath);


        WebElement confirmationPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div > div.bg-slate-950 > div > main > div.fixed.inset-0.flex.justify-center.items-center.bg-\\[rgba\\(2\\,13\\,28\\,0\\.7\\)\\].z-50 > section")));


        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[contains(@class, 'cursor-pointer') and contains(@class, 'w-5') and contains(@class, 'h-5')]")
        ));
        closeButton.click();



        Thread.sleep(3000);
        System.out.println("Avatar upload scenario completed successfully.");

        driver.quit();
    }
}



