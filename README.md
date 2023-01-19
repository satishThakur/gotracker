# gotracker

### Local Development
1. Go to the deployments/local directory.
2. Run `sudo docker-compose build gotracker` to build the docker image.
3. Execute the following `sudo docker-compose up -d`


### Product Specification
We want to build an application which can track your learning goals. 
The application will have a mobile app interface for the users to track their goals and a web interface for the admin to manage the users and their goals.
Few of the learning goals can be:
- Read a book
- Read a paper.
- Periodic blogs or articles.
- Online course/skills.

Apart from just tracking the goals, time spent on the goals, app should also help user in habit building. 
For example, if the user wants to read a book, the app should remind the user to read the book at a particular time.
Assuming user uses App to plan his/her day/week ahead - can the App help setting realistic goals for the user?
Can the App help user in achieving the goals?

Once we have basic functionality can the App start suggesting goals to the user based on the user's past goals and habits?

### User Stories (Basics)
- As a user, I want to be able to add a goal.
- As a user, I want to be able to mark a goal as complete.
- As a user, I want to be able to see my goals.
- As a user, I want to be able to see my progress.
- As a user, I want to be able to see my streak.

### User Stories (Habit Forming) [TODO]

