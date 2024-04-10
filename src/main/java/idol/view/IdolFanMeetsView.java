package idol.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class IdolFanMeetsView {

    @FXML
    private Button createFanmeetButton;

    @FXML
    private Button finishedFanmeetsButton;

    @FXML
    private FlowPane flowPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button upcomingFanmeetsButton;

    @FXML
    private Label headingLabel;

    //setting up actions for buttons
    public void setUpActionCreateFanmeetButton(EventHandler<ActionEvent> event){
        createFanmeetButton.setOnAction(event);
    }

    public void setUpActionFinishedFanmeetsButton(EventHandler<ActionEvent> event){
        finishedFanmeetsButton.setOnAction(event);
    }

    public void setUpActionUpcomingFanmeetsButton(EventHandler<ActionEvent> event){
        upcomingFanmeetsButton.setOnAction(event);
    }

    public Button getCreateFanmeetButton() {
        return createFanmeetButton;
    }

    public void setCreateFanmeetButton(Button createFanmeetButton) {
        this.createFanmeetButton = createFanmeetButton;
    }

    public Button getFinishedFanmeetsButton() {
        return finishedFanmeetsButton;
    }

    public void setFinishedFanmeetsButton(Button finishedFanmeetsButton) {
        this.finishedFanmeetsButton = finishedFanmeetsButton;
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

    public Button getUpcomingFanmeetsButton() {
        return upcomingFanmeetsButton;
    }

    public void setUpcomingFanmeetsButton(Button upcomingFanmeetsButton) {
        this.upcomingFanmeetsButton = upcomingFanmeetsButton;
    }

    public Label getHeadingLabel() {
        return headingLabel;
    }

    public void setHeadingLabel(Label headingLabel) {
        this.headingLabel = headingLabel;
    }
}
