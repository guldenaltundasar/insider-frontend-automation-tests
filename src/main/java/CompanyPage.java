import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyPage {
    private WebDriver driver;

    @FindBy(linkText = "Careers")
    private WebElement careersLink;

    public CompanyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CareersPage navigateToCareers() {
        careersLink.click();
        return new CareersPage(driver);
    }
}
