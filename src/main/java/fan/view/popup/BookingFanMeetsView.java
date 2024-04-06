package fan.view.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BookingFanMeetsView {

    @FXML
    private ImageView profilePicture;
    @FXML
    private Label headingLB, dateLB, priceLB;
    @FXML
    private TextField timeTF, durationTF;
    @FXML
    private Button confirmBT;

    public ImageView getProfilePicture() {
        return profilePicture;
    }

    public Label getHeadingLB() {
        return headingLB;
    }

    public Label getDateLB() {
        return dateLB;
    }

    public Label getPriceLB() {
        return priceLB;
    }

    public TextField getTimeTF() {
        return timeTF;
    }

    public TextField getDurationTF() {
        return durationTF;
    }

    public Button getConfirmBT() {
        return confirmBT;
    }
} // end of BookingFanMeetsView class
