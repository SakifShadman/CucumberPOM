Feature: Contact Us Feature

  Scenario Outline: Contact us scenario with different set of data

    Given user navigate to contact us page
    When user fills the form from given sheetName "<SheetName>" and rowNumber <RowNumber>
    And user clicks on send button
    Then it shows a successful message "Your message has been successfully sent to our team."

    Examples:
      | SheetName | RowNumber |
      | contactus | 0         |
      | contactus | 1         |
      | contactus | 2         |
      | contactus | 3         |
      | contactus | 4         |
      | contactus | 5         |