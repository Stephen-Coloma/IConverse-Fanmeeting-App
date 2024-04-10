package fan.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class FanMenuView {

    @FXML
    private FlowPane flowPane;
    @FXML
    private TextField searchBar;

    public FanMenuView() {}

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public TextField getSearchBar() {
        return searchBar;
    }
} // end of FanMenuView class
