package idol.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class ListOfBookedFansPopupViewView {

    @FXML
    private Label dateLabel;

    @FXML
    private FlowPane flowPane;

    @FXML
    private ScrollPane scrollPane;

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public void setFlowPane(FlowPane flowPane) {
        this.flowPane = flowPane;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}
