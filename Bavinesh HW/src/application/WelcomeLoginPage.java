package application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;

/**
 * Displays a landing screen after a user successfully logs in.
 * Depending on the user's role, it provides different navigation options.
 */
public class WelcomeLoginPage {

    private final DatabaseHelper dbHelper;

    public WelcomeLoginPage(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void show(Stage stage, User currentUser) {
        // Main container setup
        VBox container = new VBox(15);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Greeting label
        Label greeting = new Label("Hello, " + currentUser.getUserName() + "!");
        greeting.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Button to proceed based on the user's role
        Button proceedBtn = new Button("Continue");
        proceedBtn.setOnAction(event -> {
            String userRole = currentUser.getRole();
            System.out.println("User Role: " + userRole);

            if ("admin".equals(userRole)) {
                new AdminHomePage().show(stage);
            } else {
                new QuestionsPage().show(stage);
            }
        });

        // Button to exit the application
        Button exitBtn = new Button("Quit");
        exitBtn.setOnAction(event -> {
            dbHelper.closeConnection();
            Platform.exit();
        });

        // If the user is an admin, provide an option to generate an invitation code
        if ("admin".equals(currentUser.getRole())) {
            Button generateCodeBtn = new Button("Generate Invitation Code");
            generateCodeBtn.setOnAction(event -> {
                new InvitationPage().show(dbHelper, stage);
            });
            container.getChildren().addAll(greeting, proceedBtn, generateCodeBtn, exitBtn);
        } else {
            container.getChildren().addAll(greeting, proceedBtn, exitBtn);
        }

        // Scene setup
        Scene welcomeScene = new Scene(container, 800, 400);
        stage.setScene(welcomeScene);
        stage.setTitle("Welcome Page");
    }
}
