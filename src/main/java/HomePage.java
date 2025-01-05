import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "(//*[@id=\"navbarDropdownMenuLink\"])[5]")
    private WebElement companyMenu;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CompanyPage navigateToCompany() {
        companyMenu.click();
        return new CompanyPage(driver);
    }

    public boolean isPageLoaded() {
        return driver.getTitle().contains("Insider");
    }
}
