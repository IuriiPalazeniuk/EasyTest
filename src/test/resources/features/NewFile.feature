Feature: Log in

  Scenario: positive case
    Given User navigates to 'http://automationpractice.com/index.php'
    When User clicks 'Search button' "//button[@name='search']"
    When User clicks browser back page
    Then Check element "//button[@type='submit']" is 'displayed'

  Scenario: 1 positive case
    Given User navigates to 'https://www.foresee.com/'
    And User waits 500 milliseconds
    When User takes screenshot of 'pageName' page and set 'main_page' name
    When User takes screenshot of 'details' "//dd[@class='panel-0 cycle-slide active']"
    And User waits 5000 milliseconds
    When User takes screenshot of 'details' "(//a[contains(text(),'Careers')])[2]" and set 'login' name
    When User takes screenshot of 'pageName' page
    When User waits 1 seconds for 'element' is visible by 'locator'
    When User waits 5 seconds for screenshot element 'login' appears
    When User clicks on 'login' screenshot
    Then Check '1602774772' and '1602774772' screenshots are the same
    Then Check 'login' and screenshot by "(//a[contains(text(),'Careers')])[2]" are the same
    When User types 'text' into 'screenshot' input field screenshot
    When User clicks on 'career_link' screenshot
    When User enters '10rubliv@gmail.com' in 'logIn input field' "MainPage|PASSWORD"
    When User enters 'Yura19861116' in 'password input field' "//input[@name='password']"
    When User clicks 'submit button' "//button[@type='submit']"
    When User downloads file 'https://cv.djinni.co/c6/65a3ef7e81bc8ba6590655538ccfcf/Iurii_Palazeniuk__1_.doc'
    Then Check downloaded file contains 'SOFTWARE TEST AUTOMATION ENGINEER'
    Then Check file 'src/test/resources/Iurii_Palazeniuk__1_.doc' contains 'SOFTWARE TEST AUTOMATION ENGINEER'
    Then Check downloaded file is equals to 'src/test/resources/Iurii_Palazeniuk__1_.doc' file
    Then Check downloaded file with name 'Iurii_Palazeniuk__1_.doc' exists
    Then Check files 'Iurii_Palazeniuk__1_.doc' and 'Iurii_Palazeniuk__2_.doc' are equals
    When User uploads 'file' 'src/test/resources/Iurii_Palazeniuk__1_.doc' using 'detail' "//input[@name='cv_direct']"
    When User clicks 'Search button' "//button[@name='submit_search']"
    When User double clicks 'details' 'locator'
    When User enters 'text' in 'log in filed' "//input[@name='lgIn']"
    When User clears text from 'detail' 'locator'
    When User clicks browser back button
    And  User clicks browser forward button
    And User moves mouse over 'detail' 'locator'
    And User refreshes page
    And User waits 2000 milliseconds
    Then Check 'title' title
    And Check 'text' text of element 'locator'
    Then Check element "//input[@name='lgIn']" is 'displayed'
    Then Check element "//input[@name='lgIn']" is 'not displayed'
    When User selects radio button 'locator' with text 'text'
    When User selects dropdown 'locator' with text 'text'
    When User presses Enter key in 'detail' "locator"
    Then Check checkbox 'locator' is 'checked'

    When User clicks 'detail' 'locator' with text 'text' on 'detail' 'locator' with text 'text'
    When User clicks 'detail' 'locator' on 'detail' 'locator' with text 'text'

    When User scroll to 'locator' element

    When User clicks on 2 item in 'locator' collection
    When User clicks on 2 item in "cssOrXpathSelector" collection with child text "collection name"
    Then Check size of 'locator' collection is 5
    Then Check all assertions

    Scenario: 2 positive
      Given User navigates to 'https://www.foresee.com/'
      When User moves mouse over 'detail' "//li[@id='menu-item-33415']"
      And User clicks on element contains 'Webinars' text

