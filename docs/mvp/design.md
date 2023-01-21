### Scope
The initial scope is to have just one feature. User should be able to add reading book as a goal.
We would have option to start, update and finish goal.
Few key features:
- Authentication via OpenId Connect and JWT tokens.
- Session management via JWT tokens.
- Authorization via JWT tokens.
- CRUD operations for goals - which is book reading.
- Creating minimal App in flutter with features to authenticate, create, update and finish goal.

### Functional Requirements
On App if auth token is missing, user will be redirected to login page. After successful login user will be redirected to home page.
The login will happen via google account. After successful login user will be redirected to home page.
While authorizing we would store users name, email and picture in database.
On home page user will be able to see list of goals. User will be able to add new goal, update existing goal and finish goal.


### Authentication
Authentication will be done via OpenId Connect and JWT tokens. Once user profile 
is fetched from google, we will store user details in database. At the same time we would encode session 
details in JWT and send it to App via header. 
Flutter app would store JWT token in local storage and send it in header for all subsequent requests.
If JWT token is missing in header, user will be redirected to login page.
If JWT token is invalid, user will be redirected to login page.
If JWT token is valid, user will be redirected to home page.
If JWT token is expired, user will be redirected to login page.
If user logs out, JWT token will be removed from local storage and user will be redirected to login page.

### Authorization
Authorization will be done via JWT tokens. Admin login will also be done via google openid and JWT tokens. 
We wouuld store in database all the admin users and encode the same in JWT tokens. 
Once we decode the JWT token, we would check if the user is admin or not. Same would be available to all API end points.
- There would be certain API end point which would be accessible to all users. For example, login, register, home page.
- There would be certain API end point which would be accessible to only admin users. For example, admin page, admin dashboard.

### Design Details

#### OpenId Connect using Google, Flutter App and Scala Backend (HTTP4S + Cats Effect)
