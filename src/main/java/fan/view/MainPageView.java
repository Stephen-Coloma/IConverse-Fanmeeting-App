package fan.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainPageView {
    @FXML
    private StackPane stackPane;
    @FXML
    private Button profileBT, bookingsBT, browseIdolsBT, fanmeetsBT;
    @FXML
    private ImageView profilePicture;

    public StackPane getStackPane() {
        return stackPane;
    }

    public ImageView getProfilePicture() {
        return profilePicture;
    }

    public Button getProfileBT() {
        return profileBT;
    }
    public Button getBookingsBT() {
        return bookingsBT;
    }

    public Button getBrowseIdolsBT() {
        return browseIdolsBT;
    }

    public Button getFanmeetsBT() {
        return fanmeetsBT;
    }
} // end of MainPageView class
