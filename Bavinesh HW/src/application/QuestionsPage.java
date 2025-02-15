package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX application for handling question management.
 */
public class QuestionsPage {

    // Holds a list of questions
    private final List<Question> questionList = new ArrayList<>();
    private int questionId = 1;
    private final ListView<Question> listView = new ListView<>();

    /**
     * Displays this page in the specified Stage.
     *
     * @param stage the main JavaFX stage used to show the UI
     */
    public void show(Stage stage) {
        VBox container = new VBox(10);
        container.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label header = new Label("Question Manager");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField inputField = new TextField();
        inputField.setPromptText("Type your question here...");
        inputField.setMaxWidth(300);

        Button addBtn = new Button("Add");
        Button editBtn = new Button("Edit");
        Button deleteBtn = new Button("Delete");
        Button answersBtn = new Button("Manage Answers");

        // Button Event Handlers
        addBtn.setOnAction(event -> addQuestion(inputField));
        editBtn.setOnAction(event -> editQuestion());
        deleteBtn.setOnAction(event -> deleteQuestion());
        answersBtn.setOnAction(event -> manageAnswers(stage));

        container.getChildren().addAll(
                header,
                inputField,
                addBtn,
                editBtn,
                deleteBtn,
                listView,
                answersBtn
        );

        Scene scene = new Scene(container, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Question Management");
        stage.show();
    }

    /**
     * Adds a new question.
     */
    private void addQuestion(TextField inputField) {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            Question question = new Question(questionId++, text);
            questionList.add(question);
            listView.getItems().setAll(questionList);
            inputField.clear();
        } else {
            showAlert("Error", "Question cannot be blank!");
        }
    }

    /**
     * Edits the selected question.
     */
    private void editQuestion() {
        Question selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TextInputDialog dialog = new TextInputDialog(selected.getText());
            dialog.setTitle("Edit Question");
            dialog.setHeaderText("Modify your question:");
            dialog.setContentText("New text:");
            dialog.showAndWait().ifPresent(newText -> {
                if (!newText.trim().isEmpty()) {
                    selected.setText(newText.trim());
                    listView.refresh();
                } else {
                    showAlert("Error", "Question cannot be empty!");
                }
            });
        } else {
            showAlert("Error", "Please select a question to edit!");
        }
    }

    /**
     * Deletes the selected question.
     */
    private void deleteQuestion() {
        Question selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete this question?",
                    ButtonType.YES, ButtonType.NO
            );
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    questionList.remove(selected);
                    listView.getItems().setAll(questionList);
                }
            });
        } else {
            showAlert("Error", "No question selected to delete!");
        }
    }

    /**
     * Navigates to AnswersPage for the existing list of questions.
     */
    private void manageAnswers(Stage stage) {
        if (!questionList.isEmpty()) {
            new AnswersPage().display(stage, questionList);
        } else {
            showAlert("Error", "No questions available! Add some first.");
        }
    }

    /**
     * Helper method to show an Alert dialog.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Returns all questions.
     */
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questionList);
    }
}
