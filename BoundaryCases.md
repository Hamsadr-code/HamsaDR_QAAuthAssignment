# Boundary Cases

| ID | Boundary | Value | Expected |
|---|---|---:|---|
| BND-001 | Name minimum - 1 | 4 | Fail |
| BND-002 | Name minimum | 5 | Pass |
| BND-003 | Name maximum | 24 | Pass |
| BND-004 | Name maximum + 1 | 25 | Fail |
| BND-005 | Password minimum - 1 | 11 | Fail |
| BND-006 | Password minimum | 12 | Pass if letters + numbers |
| BND-007 | 12 letters only | abcdefghijkl | Fail |
| BND-008 | 12 digits only | 123456789012 | Fail |
| BND-009 | Uppercase email | HAMSA.QA@EXAMPLE.COM | Normalize lowercase |
| BND-010 | Email surrounding spaces | `  hamsa@example.com  ` | Trim/handle per policy |
| BND-011 | Email internal space | hamsa qa@example.com | Fail |
| BND-012 | Missing local part | @example.com | Fail |
| BND-013 | Missing domain | hamsa@ | Fail |
| BND-014 | Long email | Near documented limit | Accept if valid; never 500 |
| BND-015 | Rate limit threshold | 10 attempts | Normal processing |
| BND-016 | Rate limit exceeded | 11th attempt | 429 |
| BND-017 | Confirm exact match | Same values | Pass |
| BND-018 | Confirm mismatch | One char different | Fail |
