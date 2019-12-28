Feature: Bank

Scenario Outline: Displaying bank details

Given I visit Bank Page
When I select bank id <id> and click Show
Then Details : Bank Name <bank-name>, Bank short name <short-name> and list of accounts <accounts> appear

 Examples:

      | TC id | id   | bank-name                | short-name | accounts                                |
      | 1.1   | 1002 | Aac Holdings Inc         | AAC        | 2011 2057 2147                          |
      | 1.2   | 1004 | Advance Auto Parts Inc   | AAP        | 2019 2059 2145 2149                     |
      | 1.3   | 1011 | Ambev S.A.               | ABEV       | 2003 2004 2015 2016 2017 2029 2033 2034 |
