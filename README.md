# RoboDog

Spring Boot project using H2 database and JDBC.

## Story

If tiny, friendly aliens came to Earth, they would definitely fall in love with dogs.
The only problem would be that they wouldn't have them on their home planet.
They would surely ask somebody to help them automatize creating Robo-Dogs and populate their planet with four-legged friends.
Let's prepare for this case, and bring world peace to the universe! 🐾

## Classes

- **Dog**: *id*, *breed*, *name*, *age*
- **Trick**: *id*, *name*
- **Skill**: *id*, *dogId*, *trickId*, *level*
- **Pedigree**: *id*, *momId*, *dadId*, *puppyId*

## How to test

You can try the basic CRUD operations. Beyond them there are special endpoints, for example:

- `/pedigree/dog/{dog_id}/pedigree`: the pedigree of the given dog
- `/skill/dogsByTrickId/{trick_id}`: all the dogs by the trick id
- `/skill/getOptional/dog/{dog_id}/trick/{trick_id}`: the skill by dog id and trick id
- `/skill/name/{trick_name}/dog/{dog_id}`: the skill by dog id and trick name

If you run the application, you can access the database at <http://localhost:8080/h2-console> with url *jdbc:h2:~/robodogschool*, 
or try it on Swagger: <http://localhost:8080/swagger-ui.html>
