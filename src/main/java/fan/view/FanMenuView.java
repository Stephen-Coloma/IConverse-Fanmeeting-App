package fan.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class FanMenuView {

    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField searchBar;

    public FlowPane getFlowPane() {
        return flowPane;
    }
} // end of FanMenuView class
