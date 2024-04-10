package fan.view;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

public class BookingsView {
    @FXML
    private FlowPane flowPane;
    @FXML
    private Button finishedFMBT, unfinishedFMBT;

    public BookingsView () {}

    @FXML
    public void initialize() {
        setBTTransition(finishedFMBT);
        setBTTransition(unfinishedFMBT);
    } // end of initialize

    private void setBTTransition(Button button) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), button);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), button);

        fadeIn.setFromValue(1);
        fadeIn.setToValue(0.6);

        fadeOut.setFromValue(0.6);
        fadeOut.setToValue(1);

        button.setOnMouseEntered(event -> {
            fadeOut.stop();
            fadeIn.play();
        });

        button.setOnMouseExited(event -> {
            fadeIn.stop();
            fadeOut.play();
        });
    } // end of setBTTransitions

    public FlowPane getFlowPane() {
        return flowPane;
    }

    public Button getFinishedFMBT() {
        return finishedFMBT;
    }

    public Button getUnfinishedFMBT() {
        return unfinishedFMBT;
    }
} // end of BookingsView class
