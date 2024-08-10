package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import main.HomePage;


public class MakeMyTripTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        homePage.closeInitialModal();
    }

    @Test
    public void searchFlights() {
        homePage.enterFromCity("Kanpur");
        homePage.enterToCity("Pune");
        homePage.selectDepartureDate();
        homePage.clickSearchButton();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
