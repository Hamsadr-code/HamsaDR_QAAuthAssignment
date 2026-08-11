# Manual Test Cases — User Authentication & Authorization (JWT)

| ID | Title | Preconditions | Steps | Test Data | Expected Result | Priority |
|---|---|---|---|---|---|---|
| REG-001 | Successful registration | Registration available | Enter valid data and submit | Hamsa QA / unique email / HamsaPass1234 | **201 Created** + success message | P0 |
| REG-002 | Name below minimum | Registration available | Submit 4-character name | Hams | **400 Bad Request**; no account | P1 |
| REG-003 | Name minimum boundary | Registration available | Submit 5-character name | Hamsa | Accepted | P1 |
| REG-004 | Name maximum boundary | Registration available | Submit 24-character name | 24-char name | Accepted | P1 |
| REG-005 | Name above maximum | Registration available | Submit 25-character name | 25-char name | **400**; no account | P1 |
| REG-006 | Missing required fields | Registration available | Leave each required field blank and submit | Blank name/email/password/confirm | UI validation + backend **400** | P1 |
| REG-007 | Invalid email format | Registration available | Submit invalid emails | hamsa@ / @example.com / hamsa | **400** | P1 |
| REG-008 | Uppercase email | Registration available | Register uppercase email | HAMSA.QA@EXAMPLE.COM | Email normalized to lowercase | P0 |
| REG-009 | Email leading/trailing spaces | Registration available | Submit surrounding spaces | `  hamsa@example.com  ` | Trim/handle per policy; no malformed account | P1 |
| REG-010 | Duplicate email | Existing account | Register same email again | Existing email | **409 Conflict** | P0 |
| REG-011 | Duplicate email different case | Existing account | Register same email with different case | hamsa@example.com / HAMSA@EXAMPLE.COM | **409 Conflict** after normalization | P0 |
| REG-012 | Password below minimum | Registration available | Submit 11-character password | Abc12345678 | **400** | P1 |
| REG-013 | Password minimum | Registration available | Submit 12 chars containing letters/numbers | Abcdef123456 | Accepted | P1 |
| REG-014 | Password letters only | Registration available | Submit 12 letters | abcdefghijkl | **400** | P1 |
| REG-015 | Password numbers only | Registration available | Submit 12 digits | 123456789012 | **400** | P1 |
| REG-016 | Confirm password mismatch | Registration available | Enter different confirm password | HamsaPass1234 / HamsaPass1235 | **400** | P1 |
| REG-017 | Unicode/special input | Registration available | Submit Unicode/special name | Håmsa QA / 测试 | Controlled result; no 500 | P2 |
| REG-018 | Security input | Registration/API available | Submit script/SQL-like values | `<script>alert(1)</script>` / `' OR '1'='1` | No execution, bypass, SQL error, or 500 | P0 |
| REG-019 | Rate limit attempts 1–10 | Clean IP/window | Send 10 attempts | Requests 1–10 | No **429** before threshold | P0 |
| REG-020 | Rate limit attempt 11 | Same IP has 10 attempts | Send 11th request | Request #11 | **429 Too Many Requests** | P0 |
| LOG-001 | Successful login | Valid user exists | Enter correct credentials | Valid email/password | JWT returned; redirected to Dashboard | P0 |
| LOG-002 | Invalid password | Valid user exists | Enter wrong password | WrongPass1234 | **401 Unauthorized**; no Dashboard | P0 |
| LOG-003 | Unregistered email | Login available | Login with unknown email | unknown@example.com | **401 Unauthorized** | P0 |
| AUTH-001 | Dashboard with valid JWT | Successful login | Open Dashboard | Valid JWT | Dashboard shows email + route name | P0 |
| AUTH-002 | Dashboard without JWT | Logged out | Directly open Dashboard | No token | Redirect to Login | P0 |
| AUTH-003 | Tampered JWT | Valid token available | Modify token then access Dashboard | Modified token | Token rejected; no protected content | P0 |
| AUTH-004 | Expired JWT | Expired token available | Access Dashboard | Expired token | Rejected; Login/401 | P0 |
| API-001 | Protected API without JWT | Protected endpoint known | Call endpoint without Authorization | No token | **401**; no data | P0 |
| API-002 | Malformed JWT | Protected endpoint known | Send malformed Bearer token | Bearer invalid-token | **401** | P0 |
| CON-001 | Concurrent same-email registration | API capability | Send two same-email requests together | Same normalized email | Exactly one **201**, one **409** | P0 |
| API-003 | Backend validation bypass | API available | Send invalid payload directly | Invalid name/password/email | Backend still returns **400** | P0 |
| AUTH-005 | Cross-user authorization | Two users exist | Use User A token for User B resource | A token + B resource | Access denied; no data leakage | P0 |
