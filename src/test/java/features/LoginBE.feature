Feature: Login API Service

  @BERegression
  Scenario: Authentication with Valid Credentials
    Given I perform authentication operation for "/auth" with body
      | email                        | password   |
      | fatimasebiletezcan@gmail.com | Nagoya6622 |
    Then I should see 200 status code
    Then Expected to see "token" field of data are not null

  Scenario: Failed Authentication with Invalid Credentials
    Given I perform authentication operation for "/auth" with body
      | email                       | password   |
      | fatimasebiletezcan@gmail.cm | Nagoya6622 |
    Then I should see 400 status code
    Then Expected to see "Invalid email or password" message

  Scenario: Failed Authentication with Invalid Email
    Given I perform authentication operation for "/auth" with body
      | email              | password  |
      | fatimasebiletezcan | Nagoya662 |
    Then I should see 400 status code
    Then Expected to see "Valid email address required" message

  Scenario: Failed Authentication with Invalid Password
    Given I perform authentication operation for "/auth" with body
      | email                        | password |
      | fatimasebiletezcan@gmail.com | null     |
    Then I should see 400 status code
    Then Expected to see "Password is required" message
