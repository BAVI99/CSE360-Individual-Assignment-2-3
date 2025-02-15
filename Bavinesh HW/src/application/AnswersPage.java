package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX UI for managing answers (CRUD operations).
 */
public class AnswersPage {
    private final List<Answer> answerCollection = new ArrayList<>();
    private int answerIndex = 1;
    private final ListView<Answer> answerListView = new ListView<>();

    public void display(Stage stage, List<Question> questions) {
        VBox container = new VBox(10);
        container.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label header = new Label("Answer Management");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<Question> questionSelector = new ComboBox<>();
        questionSelector.getItems().addAll(questions);
        questionSelector.setPromptText("Select a question");

        TextField answerField = new TextField();
        answerField.setPromptText("Enter answer...");
        answerField.setMaxWidth(300);

        Button addButton = new Button("Add");
        Button updateButton = new Button("Edit");
        Button removeButton = new Button("Delete");

        // Adding a new answer
        addButton.setOnAction(e -> {
            Question selectedQuestion = questionSelector.getValue();
            String inputText = answerField.getText().trim();
            if (selectedQuestion != null && !inputText.isEmpty()) {
                Answer newAnswer = new Answer(answerIndex++, selectedQuestion.getId(), inputText);
                answerCollection.add(newAnswer);
                answerListView.getItems().setAll(answerCollection);
                answerField.clear();
            } else {
                showError("Input Error", "Select a question and provide an answer.");
            }
        });

        // Editing an existing answer
        updateButton.setOnAction(e -> {
            Answer selectedAnswer = answerListView.getSelectionModel().getSelectedItem();
            if (selectedAnswer != null) {
                TextInputDialog editDialog = new TextInputDialog(selectedAnswer.getText());
                editDialog.setTitle("Edit Answer");
                editDialog.setHeaderText("Modify the selected answer:");
                editDialog.setContentText("New Answer:");
                
                editDialog.showAndWait().ifPresent(updatedText -> {
                    if (!updatedText.trim().isEmpty()) {
                        selectedAnswer.setText(updatedText.trim());
                        answerListView.refresh();
                    } else {
                        showError("Input Error", "Answer cannot be empty.");
                    }
                });
            } else {
                showError("Selection Error", "No answer selected.");
            }
        });

        // Removing an answer
        removeButton.setOnAction(e -> {
            Answer selectedAnswer = answerListView.getSelectionModel().getSelectedItem();
            if (selectedAnswer != null) {
                Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this answer?", 
                        ButtonType.YES, ButtonType.NO);
                confirmDialog.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        answerCollection.remove(selectedAnswer);
                        answerListView.getItems().setAll(answerCollection);
                    }
                });
            } else {
                showError("Selection Error", "No answer selected.");
            }
        });

        container.getChildren().addAll(header, questionSelector, answerField, addButton, updateButton, removeButton, answerListView);
        stage.setScene(new Scene(container, 600, 400));
        stage.setTitle("Answer Management");
        stage.show();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
