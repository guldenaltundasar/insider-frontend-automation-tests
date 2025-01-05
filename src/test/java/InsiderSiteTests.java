import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;

public class InsiderSiteTests {
    private WebDriver driver;
    private HomePage homePage;
    private CompanyPage companyPage;
    private CareersPage careersPage;
    private QAJobsPage qaJobsPage;


    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("dom.webnotifications.enabled", false);
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.get("https://useinsider.com/");
        homePage = new HomePage(driver);
        WebElement acceptCookiesButton = driver.findElement(By.xpath("//a[@id='wt-cli-accept-btn']"));
        acceptCookiesButton.click();

    }


    @Test(priority = 1)
    public void testHomePageIsLoaded() {
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded.");
    }

    @Test(priority = 2)
    public void testNavigateToCareersAndCheckBlocks() {
        companyPage = homePage.navigateToCompany();
        careersPage = companyPage.navigateToCareers();
        Assert.assertTrue(careersPage.areBlocksVisible(), "One or more blocks are not visible on the Careers page.");
    }

    @Test(priority = 3)
    public void testNavigateToQAJobsAndCheckFilters() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
        qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.clickSeeAllQAJobs();
        qaJobsPage.filterJobs();
        Assert.assertTrue(qaJobsPage.isJobListPositionVisible(), "Job list position is not visible after filters applied.");
        Assert.assertTrue(qaJobsPage.isJobListLocationVisible(), "Job list location is not visible after filters applied.");

    }

    @Test(priority = 4)
    public void testJobDetails() {
        Assert.assertTrue(qaJobsPage.areJobDetailsPositionCorrect("Quality Assurance"), "Job positions details do not match the specified criteria.");
        Assert.assertTrue(qaJobsPage.areJobDetailsLocationCorrect("Istanbul, Turkey"), "Job location details do not match the specified criteria.");
    }

    @Test(priority = 5)
    public void testViewRoleRedirects() {
        qaJobsPage.jobListHover();
        qaJobsPage.clickViewRole();
        Assert.assertTrue(qaJobsPage.isRedirectSuccessful(), "Did not redirect to the Lever Application form page.");
    }

    @AfterMethod
    public void captureScreenShotOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("screenshots/" + result.getName() + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

