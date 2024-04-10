package idol.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateFanMeetView {

    @FXML
    private Label noticeLB;
    @FXML
    private DatePicker chosenDate;
    @FXML
    private TextField startTimeTF, endTimeTF, pricePerMinuteTF;
    @FXML
    private Button addFanMeetBT;

    public CreateFanMeetView() {}

    public Label getNoticeLB() {
        return noticeLB;
    }

    public DatePicker getChosenDate() {
        return chosenDate;
    }

    public TextField getStartTimeTF() {
        return startTimeTF;
    }

    public TextField getEndTimeTF() {
        return endTimeTF;
    }

    public TextField getPricePerMinuteTF() {
        return pricePerMinuteTF;
    }

    public Button getAddFanMeetBT() {
        return addFanMeetBT;
    }
} // end of CreateFanMeetView class
