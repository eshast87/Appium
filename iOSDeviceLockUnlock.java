
/*
mobile: lock
Lock the device (and optionally unlock it after a certain amount of time).
Only simple (e.g. without a password) locks are supported.
 */
@Test
public void test_lock() {
 
    ((IOSDriver) driver).executeScript(
            "mobile: lock", ImmutableMap.of(
                    "seconds", "0"
            ));
 
    boolean isLocked = (boolean) ((IOSDriver) driver).executeScript("mobile: isLocked");
    Assert.assertTrue(isLocked);
}
 
@Test
public void test_lockForDuration() {
    ((IOSDriver) driver).executeScript(
            "mobile: lock", ImmutableMap.of(
                    "seconds", "5"
            ));
 
    boolean isLocked = (boolean) ((IOSDriver) driver).executeScript("mobile: isLocked");
 
    Assert.assertFalse(isLocked);
}


//References:

//https://appium.github.io/appium-xcuitest-driver/4.32.5/execute-methods/#mobile-lock

//https://appium.github.io/appium-xcuitest-driver/4.32.5/execute-methods/#mobile-islocked



