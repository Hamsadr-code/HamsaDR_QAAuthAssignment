# Assumptions

1. A QA/test environment contains Registration, Login and Dashboard routes.
2. Selenium + Java is used for UI automation. API cases are documented as API scenarios because no endpoint specification or API tool was supplied.
3. Email is converted to lowercase before uniqueness comparison.
4. Name length is inclusive at 5–24 characters. Password minimum is 12 characters and requires letters and numbers.
5. Attempt 11 within one hour from the same IP returns 429.
6. Missing, malformed, tampered and expired JWTs must not grant protected access.
