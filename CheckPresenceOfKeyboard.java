


/*I would like to correct one thing that tutor said we cannot check the presence of keyboard.

Actually we can check the presence pf keyboard. Appium XCUITest provides a way to achieve it using executeScript() call to mobile: iskeyboardShown:*/



boolean iskeyboardShown = (boolean) driver.executeScript("mobile: isKeyboardShown");


//Similarly we can hide the keyboard also:



HashMap<String, String[]> map = new HashMap<String, String[]>();
String[] keys = {"Done"};
map.put("keys", keys);
((IOSDriver) driver).executeScript("mobile: hideKeyboard", map);


//https://appium.github.io/appium-xcuitest-driver/4.32.5/execute-methods/#mobile-hidekeyboard

//https://appium.github.io/appium-xcuitest-driver/4.32.5/execute-methods/#mobile-iskeyboardshown

