# Test Data 

This document contains common test data used for Manual Testing and Selenium Automation Testing of the Demo Web Shop application.

## Registration Test Data

| ID | Scenario | Gender | First Name | Last Name | Email | Password | Confirm Password | Expected Result |
|---|---|---|---|---|---|---|---|---|
| TD-001 | Valid user registration | Female | Hamsa | DR | hamsa.qa01@gmail.com | HamsaPass1234 | HamsaPass1234 | Registration should be successful |
| TD-002 | Second valid user | Female | Hamsa | Test | hamsa.qa02@gmail.com | HamsaPass1234 | HamsaPass1234 | Registration should be successful |
| TD-003 | Duplicate email | Female | Hamsa | Duplicate | hamsa.qa01@gmail.com | HamsaPass1234 | HamsaPass1234 | Duplicate email message should be displayed |
| TD-004 | Invalid email format | Female | Hamsa | Invalid | hamsa.invalid@gmail | HamsaPass1234 | HamsaPass1234 | Email validation should be displayed |
| TD-005 | Weak password | Female | Hamsa | Weak | hamsa.weak@gmail.com | abc | abc | Password validation should be displayed |
| TD-006 | Short password | Female | Hamsa | Short | hamsa.short@gmail.com | Abc12 | Abc12 | Password length validation should be displayed |
| TD-007 | Unicode characters | Female | Hamsa@_~`| QA | hamsa.unicode@gmail.com | UnicodePass1234 | UnicodePass1234 | Application should handle Unicode input correctly |
| TD-008 | Leading/trailing spaces | Female |  Hamsa  |  DR  |  hamsa.space@gmail.com  | SpacePass1234 | SpacePass1234 | Spaces should be handled or validated correctly |
| TD-009 | Special characters | Female | Hamsa@QA | Test# | hamsa.special@gmail.com | Special@1234 | Special@1234 | Application should handle special characters correctly |
| TD-010 | Empty mandatory fields | Female | | | | | | Mandatory field validation should be displayed |

## Login Test Data

| ID | Scenario | Email | Password | Expected Result |
|---|---|---|---|---|
| TD-011 | Valid login | hamsadr@gmail.com | hamsadr | User should be logged in successfully |
| TD-012 | Invalid email | invalid.user@gmail.com | hamsadr | Invalid login message should be displayed |
| TD-013 | Invalid password | hamsadr@gmail.com | WrongPass123 | Invalid login message should be displayed |
| TD-014 | Empty credentials | | | Validation or login error should be displayed |

## Dashboard Security Test Data

| ID | Scenario | User State | Expected Result |
|---|---|---|---|
| TD-015 | Account access after login | Logged in | Customer account page should be accessible |
| TD-016 | Account access without login | Logged out | User should not get unauthorized account access |
| TD-017 | Account access after logout | Logged out after successful login | Account should not remain accessible |
