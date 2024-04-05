package fan.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class UpcomingFanMeetsView {

    @FXML
    private Pane idolDetailsPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private ImageView backBT;

    public UpcomingFanMeetsView() {}

    public Pane getIdolDetailsPane() {
        return idolDetailsPane;
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public ImageView getBackBT() {
        return backBT;
    }
} // end of UpcomingFanMeetsView class
