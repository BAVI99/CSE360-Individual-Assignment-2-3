CSE360 Individual Assignment 2
Overview
This repository contains the source code for the CSE360 Individual Assignment 2. The project is a JavaFX application that demonstrates functionalities for secure account creation and login, as well as content management including question and answer management. The system supports different roles (admin and regular user) with role-specific features.

Features
Account Setup and Login:
Secure registration with username, password, and invitation code validation.
Real-time password evaluation (ensuring the presence of uppercase, lowercase, numeric, and special characters, and a minimum length of 8 characters) using a password evaluator.
Username validation through a Finite State Machine (FSM) to ensure the proper format.
Role-Based Navigation:
Admin users have access to additional functionalities such as generating invitation codes.
Regular users are directed to content management features including question and answer handling.
Content Management:
Add, edit, and delete questions.
Add, edit, and delete answers associated with questions.
User Interface:
Intuitive navigation between the setup, login, and home pages.
Personalized welcome screens based on user roles.
Project Structure
pgsql
Copy
CSE360-Individual-Assignment-2/
├── src/
│   ├── application/
│   │   ├── AdminHomePage.java
│   │   ├── AdminSetupPage.java
│   │   ├── AnswersPage.java
│   │   ├── Answer.java
│   │   ├── FirstPage.java
│   │   ├── InvitationPage.java
│   │   ├── PasswordEvaluator.java
│   │   ├── Question.java
│   │   ├── QuestionsPage.java
│   │   ├── SetupAccountPage.java
│   │   ├── SetupLoginSelectionPage.java
│   │   ├── StartCSE360.java
│   │   ├── User.java
│   │   ├── UserHomePage.java
│   │   ├── UserLoginPage.java
│   │   ├── UserNameRecognizer.java
│   │   └── WelcomeLoginPage.java
│   └── databasePart1/
│       ├── DatabaseHelper.java
│       └── (other database-related classes)
├── UML_Diagrams/
│   ├── UML_ClassDiagram.pdf
│   └── UML_SequenceDiagram.pdf
├── HW2_Screencast.mp4
└── README.md
Requirements
Java Development Kit (JDK) 8 or higher
JavaFX SDK (if not bundled with your JDK)
A compatible IDE (e.g., Eclipse, IntelliJ IDEA, or NetBeans)
Installation and Setup
Clone the repository:
bash
Copy
git clone https://github.com/BAVI99/CSE360-Individual-Assignment-2.git
Open the project in your preferred IDE.
Configure JavaFX:
Ensure that JavaFX libraries are referenced correctly in your project settings.
Database Setup:
Configure the database connection in the DatabaseHelper class located in the databasePart1 package if necessary.
Running the Application
Execute the StartCSE360.java class to launch the application.
The application will determine whether to prompt for an admin setup (if the database is empty) or display the login/setup selection page.
Follow the on-screen instructions to either create an account or log in.
Test Cases
A comprehensive list of test cases has been developed to ensure the application meets its requirements:

Account Setup (Valid/Invalid Data):
Tests for proper validation of usernames, passwords, and invitation codes.
Login (Valid/Invalid Credentials):
Tests for correct authentication and error handling.
Content Management:
Tests for adding, editing, and deleting questions and answers.
Role-Based Navigation:
Tests to verify that admin and regular users are directed to the correct interfaces.
Real-Time Password Feedback:
Tests to ensure that password criteria are evaluated and displayed correctly as the user types.
For detailed steps on each test case, please refer to the test cases documentation provided with the assignment.

UML Diagrams
UML Class Diagram:
Provides an overview of the system architecture including all major classes and their relationships.
Download: UML_ClassDiagram.pdf
UML Sequence Diagram:
Illustrates the interactions between objects for key processes such as account creation and login.
Download: UML_SequenceDiagram.pdf
HW2 Screencast
A screencast demonstrating the application’s functionality is provided:

HW2 Screencast: HW2_Screencast.mp4
Additional Information
Source Code:
All the source code required to build and run the project is included in the src directory.
Contributing:
Contributions or suggestions are welcome. Feel free to fork the repository and submit pull requests.
Issues:
For any questions or issues, please open an issue in the GitHub repository.
