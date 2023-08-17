/**
 * This gesture performs a simple "swipe" gesture on the particular screen element or on the application element, which is usually the whole screen.
 * https://github.com/clarabez/appium-1/blob/master/docs/en/writing-running-appium/ios/ios-xctest-mobile-gestures.md#mobile-swipe
 */
@Test
public void test_swipe() {
    WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString("type='XCUIElementTypeTable'"));
    Map<String, Object> params = new HashMap<>();
    params.put("direction", "up");
    //params.put("velocity", 2500);
    params.put("element", ((RemoteWebElement) element).getId());
    driver.executeScript("mobile: swipe", params);
 
    WebElement webViewElement = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"Web View\"`]"));
    Assert.assertTrue(webViewElement.isDisplayed());
}
