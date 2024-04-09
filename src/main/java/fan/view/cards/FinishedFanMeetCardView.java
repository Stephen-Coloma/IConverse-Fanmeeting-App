package fan.view.cards;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FinishedFanMeetCardView {

    @FXML
    private Text meetIDLB, dateLB, timeLB, durationLB, idolNameLB, timestampLB, priceLB, cancelLabel;

    public FinishedFanMeetCardView() {}

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

    public Text getPriceLB() {
        return priceLB;
    }

    public Text getCancelLabel() {
        return cancelLabel;
    }
} // end of FinishedFanMeetCardView class
