package main.java;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private static final By fromCityInput = By.id("fromCity");
    private static final By fromAirportList = By.xpath("//div[@class='makeFlex column flexOne']");
    private static final By toCityInput = By.id("toCity");
    private static final By toCityList = By.xpath("//li[@id='react-autowhatever-1-section-0-item-0']//div[@class='makeFlex column flexOne']");
    private static final By departureDate = By.xpath("//div[@class='DayPicker-Day' and @aria-label='Sun Aug 25 2024']");
    private static final By searchButton = By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']");
    private static final By modalCloseButton = By.xpath("//span[@class='commonModal__close']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterFromCity(String cityName) {
        driver.findElement(fromCityInput).sendKeys(cityName);
        waitForSuggestionsAndSelect(fromAirportList, cityName);
    }

    public void enterToCity(String cityName) {
        driver.findElement(toCityInput).sendKeys(cityName);
        waitForSuggestionsAndSelect(toCityList, cityName);
    }

    public void selectDepartureDate() {
        driver.findElement(departureDate).click();
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void closeInitialModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton)).click();
    }

    private void waitForSuggestionsAndSelect(By locator, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        for (WebElement option : options) {
            if (option.getText().contains(expectedText)) {
                option.click();
                break;
            }
        }
    }
}
