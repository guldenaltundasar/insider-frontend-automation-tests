import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(text(), \"Our Locations\")]")
    private WebElement locationsBlock;
    @FindBy(xpath = "//*[contains(text(), \"Find your calling\")]")
    private WebElement teamsBlock;
    @FindBy(xpath = "//*[contains(text(), \"Life at Insider\")]")
    private WebElement lifeAtInsiderBlock;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean areBlocksVisible() {
        return locationsBlock.isDisplayed() && teamsBlock.isDisplayed() && lifeAtInsiderBlock.isDisplayed();
    }
}
