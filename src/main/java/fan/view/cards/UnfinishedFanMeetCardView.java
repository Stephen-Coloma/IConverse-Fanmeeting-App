package fan.view.cards;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UnfinishedFanMeetCardView {
    @FXML
    private Text meetIDLB, dateLB, timeLB, durationLB, idolNameLB, timestampLB;
    @FXML
    private Button cancelBT;

    @FXML
    public void initialize() {
        setUpButtonTransition();
    }

    private void setUpButtonTransition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), cancelBT);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), cancelBT);

        fadeIn.setFromValue(1);
        fadeIn.setToValue(0.8);

        fadeOut.setFromValue(0.8);
        fadeOut.setToValue(1);

        cancelBT.setOnMouseEntered(event -> {
            fadeOut.stop();
            fadeIn.play();
        });

        cancelBT.setOnMouseExited(event -> {
            fadeIn.stop();
            fadeOut.play();
        });
    } // end of setUpButtonTransition

    public Text getMeetIDLB() {
        return meetIDLB;
    }

    public Text getDateLB() {
        return dateLB;
    }

    public Text getTimeLB() {
        return timeLB;
    }

    public Text getDurationLB() {
        return durationLB;
    }

    public Text getIdolNameLB() {
        return idolNameLB;
    }

    public Text getTimestampLB() {
        return timestampLB;
    }

    public Button getCancelBT() {
        return cancelBT;
    }
} // end of UnfinishedFanMeetCardView class
