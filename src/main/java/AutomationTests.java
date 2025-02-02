import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AutomationTests {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\yandexdriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Lord\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--disable-features=NetworkService");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Test 1: Регистрация с правильными данными
        driver.get("https://qa-course-01.andersenlab.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Registration"))).click();
        driver.findElement(By.name("firstName")).sendKeys("TestFirstName");
        driver.findElement(By.name("lastName")).sendKeys("TestLastName");
        WebElement dateInput = driver.findElement(By.name("dateOfBirth"));
        dateInput.sendKeys("01/01/1995");
        dateInput.sendKeys(Keys.ESCAPE);
        driver.findElement(By.name("email")).sendKeys("testuser@example.com");
        driver.findElement(By.name("password")).sendKeys("TestPassword123");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("TestPassword123");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("react-datepicker__month-container")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")));
        if (submitButton.isEnabled()) {
            submitButton.click();
            Thread.sleep(3000);
            System.out.println("Test 1: Регистрация с правильными данными - PASSED");
        } else {
            System.out.println("Test 1: Регистрация с правильными данными - FAILED: Submit button is disabled");
        }

        // Test 2: Регистрация с пустыми полями
        driver.get("https://qa-course-01.andersenlab.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Registration"))).click();

        driver.findElement(By.name("firstName")).sendKeys(Keys.TAB);
        driver.findElement(By.name("lastName")).sendKeys(Keys.TAB);
        driver.findElement(By.name("dateOfBirth")).sendKeys(Keys.TAB);
        driver.findElement(By.name("email")).sendKeys(Keys.TAB);
        driver.findElement(By.name("password")).sendKeys(Keys.TAB);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(Keys.TAB);

        WebElement submitButtonEmpty = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));

        String disabledAttr2 = submitButtonEmpty.getAttribute("disabled");
        if (disabledAttr2 != null && disabledAttr2.length() > 0) {

            WebElement requiredError = driver.findElement(By.xpath("//*[contains(text(),'Required')]"));
            if (requiredError.isDisplayed()) {
                System.out.println("Test 2: Регистрация с пустыми полями - PASSED");
            } else {
                System.out.println("Test 2: Регистрация с пустыми полями - FAILED: 'Required' error not displayed");
            }
        } else {
            System.out.println("Test 2: Регистрация с пустыми полями - FAILED: Submit button is enabled");
        }

        // Test 3: Регистрация с датой рождения в будущем
        driver.get("https://qa-course-01.andersenlab.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Registration"))).click();
        driver.findElement(By.name("firstName")).sendKeys("FutureTest");
        driver.findElement(By.name("lastName")).sendKeys("User");
        WebElement futureDateInput = driver.findElement(By.name("dateOfBirth"));

        futureDateInput.sendKeys("12/31/2050");
        futureDateInput.sendKeys(Keys.ESCAPE);
        driver.findElement(By.name("email")).sendKeys("futureuser@example.com");
        driver.findElement(By.name("password")).sendKeys("TestPassword123");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("TestPassword123");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("react-datepicker__month-container")));

        driver.findElement(By.name("firstName")).sendKeys(Keys.TAB);
        driver.findElement(By.name("lastName")).sendKeys(Keys.TAB);
        driver.findElement(By.name("dateOfBirth")).sendKeys(Keys.TAB);
        driver.findElement(By.name("email")).sendKeys(Keys.TAB);
        driver.findElement(By.name("password")).sendKeys(Keys.TAB);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(Keys.TAB);
        WebElement submitButtonFuture = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        String disabledAttr3 = submitButtonFuture.getAttribute("disabled");
        if (disabledAttr3 != null && disabledAttr3.length() > 0) {
            System.out.println("Test 3: Регистрация с датой рождения в будущем - PASSED (Submit button is disabled)");
        } else {
            System.out.println("Test 3: Регистрация с датой рождения в будущем - FAILED: Submit button is enabled");
        }

        // Test 4: Регистрация с email без символа '@'
        driver.get("https://qa-course-01.andersenlab.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Registration"))).click();
        driver.findElement(By.name("firstName")).sendKeys("InvalidEmail");
        driver.findElement(By.name("lastName")).sendKeys("User");
        WebElement dateInputInvalid = driver.findElement(By.name("dateOfBirth"));
        dateInputInvalid.sendKeys("01/01/1995");
        dateInputInvalid.sendKeys(Keys.ESCAPE);

        driver.findElement(By.name("email")).sendKeys("invalidemail.example.com");
        driver.findElement(By.name("password")).sendKeys("TestPassword123");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("TestPassword123");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("react-datepicker__month-container")));

        driver.findElement(By.name("email")).sendKeys(Keys.TAB);
        WebElement submitButtonInvalidEmail = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        String disabledAttr4 = submitButtonInvalidEmail.getAttribute("disabled");
        if (disabledAttr4 != null && disabledAttr4.length() > 0) {

            WebElement invalidEmailError = driver.findElement(By.xpath("//*[contains(text(),'Invalid email address')]"));
            if (invalidEmailError.isDisplayed()) {
                System.out.println("Test 4: Регистрация с email без '@' - PASSED");
            } else {
                System.out.println("Test 4: Регистрация с email без '@' - FAILED: Error message not displayed");
            }
        } else {
            System.out.println("Test 4: Регистрация с email без '@' - FAILED: Submit button is enabled");
        }

        // Test 5: Проверка соответствия полей Password и Confirm password
        driver.get("https://qa-course-01.andersenlab.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Registration"))).click();
        driver.findElement(By.name("firstName")).sendKeys("CaseTest");
        driver.findElement(By.name("lastName")).sendKeys("User");
        WebElement dateInputMismatch = driver.findElement(By.name("dateOfBirth"));
        dateInputMismatch.sendKeys("01/01/1995");
        dateInputMismatch.sendKeys(Keys.ESCAPE);
        driver.findElement(By.name("email")).sendKeys("casetest@example.com");

        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("1234567890");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("react-datepicker__month-container")));

        driver.findElement(By.name("passwordConfirmation")).sendKeys(Keys.TAB);
        WebElement submitButtonMismatch = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        String disabledAttr5 = submitButtonMismatch.getAttribute("disabled");
        if (disabledAttr5 != null && disabledAttr5.length() > 0) {

            WebElement passwordMismatchError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@name='passwordConfirmation']/ancestor::div[1]//span[contains(text(),'Passwords must match')]")
            ));
            if (passwordMismatchError != null && passwordMismatchError.isDisplayed()) {
                System.out.println("Test 5: Регистрация с несовпадающими паролями - PASSED");
            } else {
                System.out.println("Test 5: Регистрация с несовпадающими паролями - FAILED: Error message not displayed");
            }
        } else {
            System.out.println("Test 5: Регистрация с несовпадающими паролями - FAILED: Submit button is enabled");
        }

        driver.quit();
    }
}











