package idol.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CancelConfirmationView {

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;

    //set up action for confirmation buttons
    public void setUpActionYesButton(EventHandler<ActionEvent> event){
        yesButton.setOnAction(event);
    }

    public void setUpActionNoButton(EventHandler<ActionEvent> event){
        noButton.setOnAction(event);
    }

    public Button getNoButton() {
        return noButton;
    }

    public void setNoButton(Button noButton) {
        this.noButton = noButton;
    }

    public Button getYesButton() {
        return yesButton;
    }

    public void setYesButton(Button yesButton) {
        this.yesButton = yesButton;
    }
}
