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

