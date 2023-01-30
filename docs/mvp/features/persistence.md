### Feature
This feature would implement postGreSQL based persistence for the API. We would use the `doobie` library to interact with the database. We would use the `flyway` library to manage database migrations. 
DB will be used to store the following entities:
- User
- Goal
- Activity

### Implementation Details
We would use `skunk` library to interact with the database. 

#### Schema 
We would need to define the schema for the following entities:
- User
- Goal
- Activity

#### Algebras
We would need to define the following algebras:
- UserAlgebra
- GoalAlgebra
- ActivityAlgebra

#### Program
We would need to define the following programs:
- UserProgram
- GoalProgram
- ActivityProgram
