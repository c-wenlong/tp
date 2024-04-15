---
name: local-part@domain
show-in: [ug]
---

The local-part@domain format specifies the following constraints for email addresses:
1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
1. This local-part is followed by a '@' and then a domain name. The domain name is made up of domain labels **separated by periods**.
   The domain name must:
    - end with a domain label at least 2 characters long
    - have each domain label start and end with alphanumeric characters
    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
