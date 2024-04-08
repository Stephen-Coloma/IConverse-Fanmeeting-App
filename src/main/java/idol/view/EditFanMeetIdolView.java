package idol.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditFanMeetIdolView {

    @FXML
    private TextField editDateTextField;

    @FXML
    private TextField editStartTimeTextField;

    @FXML
    private Label finalTimeLabel;

    @FXML
    private Label noticeLabel;

    @FXML
    private Button saveChangesButton;

    //set up action for save button
    public void setUpActionSaveChangesButton(EventHandler<ActionEvent> event){
        this.saveChangesButton.setOnAction(event);
    }

    public TextField getEditDateTextField() {
        return editDateTextField;
    }

    public void setEditDateTextField(TextField editDateTextField) {
        this.editDateTextField = editDateTextField;
    }

    public TextField getEditStartTimeTextField() {
        return editStartTimeTextField;
    }

    public void setEditStartTimeTextField(TextField editStartTimeTextField) {
        this.editStartTimeTextField = editStartTimeTextField;
    }

    public Label getFinalTimeLabel() {
        return finalTimeLabel;
    }

    public void setFinalTimeLabel(Label finalTimeLabel) {
        this.finalTimeLabel = finalTimeLabel;
    }

    public Button getSaveChangesButton() {
        return saveChangesButton;
    }

    public void setSaveChangesButton(Button saveChangesButton) {
        this.saveChangesButton = saveChangesButton;
    }

    public Label getNoticeLabel() {
        return noticeLabel;
    }

    public void setNoticeLabel(Label noticeLabel) {
        this.noticeLabel = noticeLabel;
    }
}
