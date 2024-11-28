# QuizApp
Quiz App is a web application that allows users to take quizzes, track their performance, and compete with others based on their quiz results. The app provides detailed feedback on quiz results, including the number of attempts, correct answers, and total score.

Tech Stack
Java 17+
Spring Boot for the backend
Maven as the build tool
H2 Database for in-memory storage (can be replaced with other databases)
Spring Data JPA for database interaction
Postman for API testing
Features
User Registration: Users can create their profiles.
Take a Quiz: Users can take random quizzes and submit answers.
Timer: Keeps track of how long users take to complete the quiz.
Performance Tracking: View detailed quiz performance, including attempts, correct answers, and score.
Getting Started
Prerequisites
To run the project on your local system, ensure you have the following installed:

Java 17+ (For building and running the Spring Boot application)
Maven (For dependency management and building the project)
Postman (For testing the APIs)
Steps to Run the Application Locally
Clone the repository: First, clone the repository to your local machine:

bash
Copy code
git clone https://github.com/your-username/quiz-app.git
Navigate to the project directory:

bash
Copy code
cd quiz-app
Build the project: Run the following Maven command to build the project:

bash
Copy code
mvn clean install
Run the Spring Boot application: Start the application with Maven:

bash
Copy code
mvn spring-boot:run
The app will start running on http://localhost:8080.

Access the H2 Console (optional): The H2 in-memory database is used by default. You can access it by navigating to http://localhost:8080/h2-console and connecting using the default JDBC URL: jdbc:h2:mem:testdb, with the username sa and no password.

API Endpoints
Here are the main API endpoints implemented in this app:

User Management
Create a User

Endpoint: POST /user/create
Description: Creates a new user.
Request Body:
json
Copy code
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
Get User by ID

Endpoint: GET /user/{id}
Description: Fetches user details by ID.
Example Request: GET /user/1
Quiz Operations
Start a Quiz

Endpoint: POST /quiz/take/{userId}
Description: Fetches a random question for the user.
Example Request: POST /quiz/take/1
Submit an Answer

Endpoint: POST /quiz/submit/{userId}
Description: Submit an answer to a question.
Request Body:
json
Copy code
{
    "questionId": 1,
    "answer": "Paris"
}
End Quiz

Endpoint: POST /quiz/end/{userId}
Description: Marks the quiz as completed and displays performance data.
Performance Tracking
Get User Performance
Endpoint: GET /dashboard/{userId}
Description: Fetches the quiz performance for a user.
Example Request: GET /dashboard/1
Testing the API with Postman
Here’s how you can test the APIs using Postman:

Create a User:

Method: POST
URL: http://localhost:8080/user/create
Body (raw JSON):
json
Copy code
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
Take a Quiz:

Method: POST
URL: http://localhost:8080/quiz/take/1 (replace 1 with the user ID).
Response: A random question will be returned.
Submit an Answer:

Method: POST
URL: http://localhost:8080/quiz/submit/1 (replace 1 with the user ID).
Body (raw JSON):
json
Copy code
{
    "questionId": 1,
    "answer": "Paris"
}
End the Quiz:

Method: POST
URL: http://localhost:8080/quiz/end/1
Response: Performance data will be returned for the user.
Get User Performance:

Method: GET
URL: http://localhost:8080/dashboard/1 (replace 1 with the user ID).
Response: Displays total attempts, correct answers, and score.
Conclusion
This Quiz App allows users to take quizzes, track their performance, and compete based on their quiz results. With a simple setup process and well-defined APIs, you can easily integrate this application into your system. Enjoy using and extending this app!
