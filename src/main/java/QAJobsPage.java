import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;

public class QAJobsPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 'btn-outline-secondary')]")
    private WebElement seeAllQAJobsButton;
    @FindBy(xpath = "//*[@role=\"combobox\"][contains(@aria-labelledby, 'filter-by-location')]")
    private WebElement locationDropdown;

    @FindBy(xpath = "//*[contains(@class, 'select2-results__option') and contains(text(), 'Istanbul, Turkey')]")
    private WebElement selectLocation;

    @FindBy(xpath = "//*[@role=\"combobox\"][contains(@aria-labelledby, 'filter-by-department')]")
    private WebElement departmentDropdown;

    @FindBy(xpath = "//*[contains(@class, 'select2-results__option') and contains(text(), 'Quality Assurance')]")
    private WebElement selectDepartment;
    @FindBy(xpath = "(//*[contains(@class, \"position-department\")])[1]")
    private WebElement jobListPosition;

    @FindBy(xpath = "(//*[contains(@class, \"position-location\")])[1]")
    private WebElement jobListLocation;
    @FindBy(xpath = "(//*[text()='View Role'])[1]")
    private WebElement viewRoleButton;

    @FindBy(xpath = "//span[@class=\"select2-selection__rendered\" and @title=\"Quality Assurance\"]")
    private WebElement departmentQAPresence;

    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSeeAllQAJobs() {
        seeAllQAJobsButton.click();
    }

    public void filterJobs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(departmentQAPresence, "Quality Assurance"));
        locationDropdown.click();
        selectLocation.click();
        departmentDropdown.click();
        selectDepartment.click();

    }

    public boolean isJobListPositionVisible() {
        return jobListPosition.isDisplayed();
    }

    public boolean isJobListLocationVisible() {
        return jobListLocation.isDisplayed();
    }

    public void jobListHover() {
        Actions action = new Actions(driver);
        action.scrollByAmount(0, 300).perform();
        action.moveToElement(jobListPosition).perform();
    }

    public boolean areJobDetailsPositionCorrect(String position) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(jobListPosition, position));
        return jobListPosition.getText().contains(position);
    }

    public boolean areJobDetailsLocationCorrect(String location) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(jobListLocation, location));
        return jobListLocation.getText().contains(location);
    }

    public void clickViewRole() {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action.moveToElement(jobListPosition).perform();
        action.scrollToElement(viewRoleButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(viewRoleButton));
        viewRoleButton.click();
    }

    public boolean isRedirectSuccessful() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.titleContains("Quality Assurance Engineer"));
        return driver.getCurrentUrl().contains("lever.co");
    }
}
