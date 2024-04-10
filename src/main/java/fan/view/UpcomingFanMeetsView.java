package fan.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpcomingFanMeetsView {

    @FXML
    private FlowPane flowPane;
    @FXML
    private ImageView profilePicture, backBT;
    @FXML
    private Text idolNameLB, bioLB;

    public UpcomingFanMeetsView() {}

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public Text getIdolNameLB() {
        return idolNameLB;
    }

    public Text getBioLB() {
        return bioLB;
    }

    public ImageView getProfilePicture() {
        return profilePicture;
    }

    public ImageView getBackBT() {
        return backBT;
    }
} // end of UpcomingFanMeetsView class
