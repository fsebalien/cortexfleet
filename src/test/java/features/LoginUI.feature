Feature: Login UI

  Background:
    Given Enter the page "https://fleet.cortexpowered.com"

  @UIRegression
  Scenario: Check Login is Successful with Valid Credentials
    Given Enter the page "https://fleet.cortexpowered.com"
    When Login with "fatimasebiletezcan@gmail.com" username and "Nagoya6622" password
    Then See logged in successfully

  Scenario: Check Login Failed with Invalid Credentials
    When Login with "fatimasebiletezcan@gmail.com" username and "Nagoya662!" password
    Then See logged in successfully

  Scenario: Check Failed Login Warning Message
    When Login with "fatimasebiletezcan@gmail.com" username and "Nagoya662!" password
    Then See invalid credentials message

  Scenario: Check Forgot Password
    When Click the link "Forgot Password?"
    Then Check the page "https://fleet.cortexpowered.com/login/forgot-password"
    When Enter email address of the forgotten account "fatimasebiletezcan@gmail.com"
    Then See email is sent




