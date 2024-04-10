package idol.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FinishedFanMeetsViewCardView {

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Button viewDetailsButton;

    @FXML
    private Button viewFeedbacksButton;


    //set up actions for button
    public void setUpActionViewDetailsButton(EventHandler<ActionEvent> event){
        viewDetailsButton.setOnAction(event);
    }

    public void setUpActionViewFeedbacksButton(EventHandler<ActionEvent> event){
        viewFeedbacksButton.setOnAction(event);
    }


    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Button getViewDetailsButton() {
        return viewDetailsButton;
    }

    public void setViewDetailsButton(Button viewDetailsButton) {
        this.viewDetailsButton = viewDetailsButton;
    }

    public Button getViewFeedbacksButton() {
        return viewFeedbacksButton;
    }

    public void setViewFeedbacksButton(Button viewFeedbacksButton) {
        this.viewFeedbacksButton = viewFeedbacksButton;
    }
}
