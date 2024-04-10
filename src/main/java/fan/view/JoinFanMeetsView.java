package fan.view;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class JoinFanMeetsView {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;

    public JoinFanMeetsView() {}

    public FlowPane getFlowPane() {
        return flowPane;
    }
} // end of JoinFanMeetsView class
