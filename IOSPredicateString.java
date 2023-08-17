import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class iOSPredicateString {

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("iOS");

        WebElement myElement = driver.findElement(
                AppiumBy.iOSNsPredicateString("label CONTAINS \"Activity Indicators\""));
        System.out.println(myElement.getText());

        myElement = driver.findElement(
                AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Activity Indicators\"]"));
        System.out.println(myElement.getText());
    }
}
