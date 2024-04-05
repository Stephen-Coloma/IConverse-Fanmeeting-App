package idol.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class FeedbackPopupView {

    @FXML
    private Label dateLabel;

    @FXML
    private FlowPane flowpane;

    @FXML
    private ScrollPane scrollpane;

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public FlowPane getFlowpane() {
        return flowpane;
    }

    public void setFlowpane(FlowPane flowpane) {
        this.flowpane = flowpane;
    }

    public ScrollPane getScrollpane() {
        return scrollpane;
    }

    public void setScrollpane(ScrollPane scrollpane) {
        this.scrollpane = scrollpane;
    }
}
