# Quiz App
Quiz App is a web application that allows users to take quizzes, track their performance, and compete with others based on their quiz results. The app provides detailed feedback on quiz results, including the number of attempts, correct answers, and total score.

## Tech Stack
* Java 17+
* Spring Boot for the backend
* Maven as the build tool
* H2 Database for in-memory storage (can be replaced with other databases)
* Spring Data JPA for database interaction
* Postman for API testing


## Getting Started
### Prerequisites
#### To run the project on your local system, ensure you have the following installed:

* Java 17+ (For building and running the Spring Boot application)
* Maven (For dependency management and building the project)
* Postman (For testing the APIs)

#### Steps to Run the Application Locally
* Clone the repository: First, clone the repository to your local machine:
* mvn clean install
* Run the Spring Boot application: Start the application with Maven:
* mvn spring-boot:run
* To access the H2 Console: Go to [H2-console](http://localhost:8080/h2-console)  and connect using the default JDBC URL: jdbc:h2:mem:testdb, with the username sa and no password.
## Features
* User Registration: Users can create their profiles.
* Take a Quiz: Users can take random quizzes and submit answers.
* Performance Tracking: View detailed quiz performance, including attempts, correct answers, and score.


## API Endpoints
### Here are the main API endpoints implemented in this app:

#### User Management
Create a User

Endpoint: POST /user/create

Request Body:

```json
{
  "name": "XYZ",
  "email": "xyz@example.com"
}
```
Get User by ID

Endpoint: GET /user/{id}

#### Adding Questions 
* Add a question: POST /questions
Request Body 
```json
{
    "text": "What is the capital of France?",
    "options": ["Berlin", "Madrid", "Rome", "Paris"],
    "correctAnswer": "Paris"
}
```


#### Quiz Operations
* Start a Quiz
Endpoint: POST /quiz/take/{userId}


* Submit an Answer
Endpoint: POST /quiz/submit/{userId}

Request Body:

```json
{
  "questionId": 1,
  "answer": "Paris"
}
```
* End Quiz
Endpoint: POST /quiz/end/{userId}


#### Performance Tracking
* Get User Performance
Endpoint: GET /dashboard/{userId}

## Conclusion
This Quiz App allows users to take quizzes, track their performance, and compete based on their quiz results. With a simple setup process and well-defined APIs, you can easily integrate this application into your system.
