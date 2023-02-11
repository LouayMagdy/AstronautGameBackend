# Back-End of Astronaut Game Web Application 👨‍🚀
## About the Game
* a back-end system of a web flash game about an astronaut trying to collect some doughnuts floating in space while some lava rocks are also floating.
* Collision with doughnuts 🍩 will add 5 points to his life, while collision with a lava rock 🔥 will take 20 points from his life.
* The astronaut should try to collect as much doughnuts 🍩 as possible as the score depends the most on the maximum number of collected food all over the played games.
* He also should also take care of his life; as the score depends on it in case equality of the previous aspect ⚡.

## About The Application
* The Game is developed using **Spring Boot Framework** 🍃 
* **Design Patterns** Applied:
  - Flyweight Design pattern .. for movables
  - Singleton Design Pattern ... when dealing with resources and factories
  - Bridge Design Pattern ... for moving the missles
  - Factory Method Design Pattern ... for producing the missles
  - Facade Design Pattern ... for dealing with Gson Library
* **Techs used And Principles followed**:
  - **Java Threads** and **Synchronization** .. to control movables
  - DB connection and **JPA** .. to save users, and their rankings
  - **WebSocket** and **Http protocols** .. during the game loop 
      1. sending movables position, and astronaut life
      2. receiving astronaut position, browser window dimensions, JWT token
  - **Spring Security** using JWT
  - **Resttemplate** .. to save the game
  - **Gson** Serialization and Deserialization
  - Dependency Inversion
  - Newton's law for curve Interpolation .. used to make the path of the movables
  
  ## Class Diagrams (uploaded as SVG with the source code) 
  * Registration Class Diagram
  ![image](https://user-images.githubusercontent.com/95547833/218262317-dc80e167-ec4e-4a14-aba3-b798bbc0dac5.png)
  * Game Class Diagram
  ![CD](https://user-images.githubusercontent.com/95547833/218262472-6cd49228-0321-4110-b9e8-a5c9cf2572fa.png)
  
  Note: I know it is not a good design decision to use threads at the backend, but this project is just a practice on the pre-mentioned techs
