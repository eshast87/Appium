/*I am sharing the code for saucdemo.com which I used for safari browser automation in my iOS simulator.

I tested my code on iOS 16.2 simulator with. iPhone 14.

While automating safari on device simulator, I had many problems at first like some locators which worked for Chrome on device emulator were not working in safari. It was a bit strange for me.

Furthermore, the javascript scroll into view execute script also does not work in mobile safari browser, so I had to use W3C Actions for scrolling.

Here is my code:

Test Code:



/*
 This is just a demo for automating the mobile browser safari with appium
 */
@Test
public void test_saucedemoE2E() {
    driver.get("https://www.saucedemo.com/");
 
    //Login
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='user-name']"))).sendKeys("standard_user");
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='password']"))).sendKeys("secret_sauce");
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='login-button']"))).click();
 
    //Reset app state
    wait.until(visibilityOfElementLocated(By.xpath("//button[@id='react-burger-menu-btn']"))).click();
    wait.until(visibilityOfElementLocated(By.xpath("//a[@id='reset_sidebar_link']"))).click();
 
    //Close the sidebar
    wait.until(visibilityOfElementLocated(By.xpath("//button[@id='react-burger-cross-btn']"))).click();
 
    //Scroll down to add to cart button
    Gestures.scroll(driver, Gestures.ScrollDirection.DOWN, 0.1);
 
    //add to cart
    wait.until(visibilityOfElementLocated(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"))).click();
 
    //scroll to the top to add to cart button
    Gestures.scroll(driver, Gestures.ScrollDirection.UP, 0.1);
 
    //Open Cart
    wait.until(visibilityOfElementLocated(By.xpath("//a[@class='shopping_cart_link']"))).click();
 
    //Proceed for checkout
    wait.until(visibilityOfElementLocated(By.xpath("//button[@id='checkout']"))).click();
 
    //Fill personal details and continue
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='first-name']"))).sendKeys("test");
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='last-name']"))).sendKeys("user");
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='postal-code']"))).sendKeys("12345");
    wait.until(visibilityOfElementLocated(By.xpath("//input[@id='continue']"))).click();
 
    //Validate order summary
    WebElement total = wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@class, 'summary_total_label')]")));
    float totalBill = Float.parseFloat(total.getText().split("Total: \\$")[1]);
    Assert.assertEquals(totalBill, totalBill);
 
    //Finish order
    wait.until(visibilityOfElementLocated(By.xpath("//button[@id='finish']"))).click();
 
    //Validate order status
    WebElement orderStatus =  wait.until(visibilityOfElementLocated(By.xpath("//div[@id='header_container']//span[@class='title']")));
    Assert.assertEquals(orderStatus.getText(), "Checkout: Complete!");
 
    //Logout
    wait.until(visibilityOfElementLocated(By.xpath("//button[@id='react-burger-menu-btn']"))).click();
    wait.until(visibilityOfElementLocated(By.xpath("//a[@id='logout_sidebar_link']"))).click();
 
    //Validation for successful logout
    WebElement loginLogo =  wait.until(visibilityOfElementLocated(By.xpath("//div[@class='login_logo']")));
    Assert.assertTrue(loginLogo.isEnabled());
}


Gestures Utility class:

public class Gestures {
    private static Dimension windowSize;
    private static Duration SCROLL_DUR = Duration.ofMillis(100);
 
    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }
 
    private static Dimension getWindowSize(AppiumDriver driver) {
        if (windowSize == null) {
            windowSize = driver.manage().window().getSize();
        }
        return windowSize;
    }
 
    public static void scroll(AppiumDriver driver, ScrollDirection dir, double scrollRatio) {
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
 
        Dimension size = getWindowSize(driver);
 
        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
 
        int top = midPoint.y - (int)(midPoint.y * scrollRatio);
        int bottom = midPoint.y + (int)(midPoint.y * scrollRatio);
        int left = midPoint.x - (int)(midPoint.x*scrollRatio);
        int right = midPoint.x + (int)(midPoint.x*scrollRatio);
 
        if (dir == ScrollDirection.UP) {
            swipe(driver,new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(driver,new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(driver,new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(driver,new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
 
    }
 
 
 
    protected static void swipe(AppiumDriver driver,Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
 
        ((AppiumDriver) driver).perform(ImmutableList.of(swipe));
    }
 
}
