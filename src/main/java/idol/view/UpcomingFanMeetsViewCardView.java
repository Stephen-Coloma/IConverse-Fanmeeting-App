package idol.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UpcomingFanMeetsViewCardView {

    @FXML
    private Label dateLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editDetailsButton;

    @FXML
    private Button listOfBookFansButton;

    //setting up actions for buttons
    public void setUpActionListOfBookFansButton(EventHandler<ActionEvent> event){
        this.listOfBookFansButton.setOnAction(event);
    }

    public void setUpActionEditDetailsButton(EventHandler<ActionEvent> event){
        this.editDetailsButton.setOnAction(event);
    }

    public void setUpActionDeleteButton(EventHandler<ActionEvent> event){
        this.deleteButton.setOnAction(event);
    }

    @FXML
    private Label timeLabel;

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Button getEditDetailsButton() {
        return editDetailsButton;
    }

    public void setEditDetailsButton(Button editDetailsButton) {
        this.editDetailsButton = editDetailsButton;
    }

    public Button getListOfBookFansButton() {
        return listOfBookFansButton;
    }

    public void setListOfBookFansButton(Button listOfBookFansButton) {
        this.listOfBookFansButton = listOfBookFansButton;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }
}
