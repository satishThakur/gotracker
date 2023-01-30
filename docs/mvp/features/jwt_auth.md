### Feature
Implement JWT authentication for the API. We should be able to enforce JWT based authentication
for a specific API endpoint. 

### Implementation Details
- We would need to implement a `AuthMiddleware` which would be able to enforce JWT based authentication.
- The middleware would extract the JWT token from the request header and validate it.
- If the token is valid, the middleware would extract the user details from the token and pass it to the API endpoint.
- If the token is invalid, the middleware would return a `401` response.

### JWT Service
- We would need to implement a `JwtService` which would be able to generate and validate JWT tokens.
- We need to define what encryption algorithm we would use to encrypt the token.
- We would prefer to use public/private key encryption algorithm.
- We would need to generate a public/private key pair.
- We would need to store the private key in a secure location.
- Public key can be stored in the codebase. In future the same key can be shared with other
services which would need to validate the token. This would allow us not to have a central point of failure.
- We would use the ECDSA algorithm to generate the key pair.

