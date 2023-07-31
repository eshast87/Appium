import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class iOSGestures {
    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("iOS");
        slider(driver);
    }

    public static void slider(AppiumDriver driver){
        driver.findElement(AppiumBy.accessibilityId("Sliders")).click();

        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString("value == \"42%\""));
        element.sendKeys("0");

        element = driver.findElement(AppiumBy.iOSNsPredicateString("value == \"0%\""));
        element.sendKeys("1");
    }

    public static void pickerWheel(AppiumDriver driver){
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();

        boolean flag = false;
        while(!flag){
            WebElement redPickerWheel = driver.findElement(AppiumBy.
                    iOSNsPredicateString("label == \"Red color component value\""));
            Map<String, Object> params = new HashMap<>();
            params.put("order", "next");
            params.put("offset", 0.15);
            params.put("element", ((RemoteWebElement) redPickerWheel).getId());
            driver.executeScript("mobile: selectPickerWheelValue", params);
            if(redPickerWheel.getText().equals("90")){
                flag = true;
            }
        }

        flag = false;
        while(!flag){
            WebElement redPickerWheel = driver.findElement(AppiumBy.
                    iOSNsPredicateString("label == \"Green color component value\""));
            Map<String, Object> params = new HashMap<>();
            params.put("order", "previous");
            params.put("offset", 0.15);
            params.put("element", ((RemoteWebElement) redPickerWheel).getId());
            driver.executeScript("mobile: selectPickerWheelValue", params);
            if(redPickerWheel.getText().equals("190")){
                flag = true;
            }
        }

        flag = false;
        while(!flag){
            WebElement redPickerWheel = driver.findElement(AppiumBy.
                    iOSNsPredicateString("label == \"Blue color component value\""));
            Map<String, Object> params = new HashMap<>();
            params.put("order", "next");
            params.put("offset", 0.15);
            params.put("element", ((RemoteWebElement) redPickerWheel).getId());
            driver.executeScript("mobile: selectPickerWheelValue", params);
            if(redPickerWheel.getText().equals("135")){
                flag = true;
            }
        }

    }

    public static void dragAndDrop(AppiumDriver driver){
        Map<String, Object> params = new HashMap<>();
        params.put("fromX", 60);
        params.put("fromY", 300);
        params.put("toX", 60);
        params.put("toY", 0);
        params.put("duration", 1);
        driver.executeScript("mobile: dragFromToForDuration", params);
    }

    public static void tap(AppiumDriver driver){
        WebElement element = driver.findElement(AppiumBy.accessibilityId("Steppers"));

        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) element).getId());
        params.put("x", 0);
        params.put("y", 0);
        driver.executeScript("mobile: tap", params);
    }

    public static void touchAndHold(AppiumDriver driver){
        driver.findElement(AppiumBy.accessibilityId("Steppers")).click();

        WebElement element = driver.findElement(AppiumBy
                .iOSClassChain("**/XCUIElementTypeButton[`label == \"Increment\"`][1]"));

        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) element).getId());
        params.put("duration", 5);
        driver.executeScript("mobile: touchAndHold", params);
    }

    public static void pinchGesture(AppiumDriver driver){
//        driver.findElement(AppiumBy.
//                iOSClassChain("**/XCUIElementTypeButton[`label == \"Continue\"`]")).click();

        Map<String, Object> params1 = new HashMap<>();
        params1.put("scale", 20);
        params1.put("velocity", 2.2);
        driver.executeScript("mobile: pinch", params1);

        WebElement element = driver.findElement(AppiumBy.
                iOSClassChain("**/XCUIElementTypeOther[`name == \"OverlayView\"`][1]"));

        Map<String, Object> params2 = new HashMap<>();
        params2.put("elementId", ((RemoteWebElement) element).getId());
        params2.put("scale", 0.1);
        params2.put("velocity", -2.2);
        driver.executeScript("mobile: pinch", params2);

    }

    public static void scrollGesture(AppiumDriver driver) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        driver.executeScript("mobile: scroll", params);


/*        WebElement parentElement = driver.findElement(AppiumBy.
                iOSNsPredicateString("type == \"XCUIElementTypeTable\""));*/
        WebElement childElement = driver.findElement(AppiumBy.
                accessibilityId("Activity Indicators"));
        params = new HashMap<>();
//        params.put("direction", "down");
        params.put("elementId", ((RemoteWebElement) childElement).getId());
        //        params.put("name", "Web View");
//        params.put("predicateString", "label == \"Web View\"");
        params.put("toVisible", true);
        driver.executeScript("mobile: scroll", params);
    }

    public static void swipeGesture(AppiumDriver driver) {
        WebElement element = driver.findElement(AppiumBy.
                iOSNsPredicateString("type == \"XCUIElementTypeTable\""));
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
 //       params.put("velocity", 2500);
        params.put("element", ((RemoteWebElement) element).getId());
        driver.executeScript("mobile: swipe", params);
    }
}
