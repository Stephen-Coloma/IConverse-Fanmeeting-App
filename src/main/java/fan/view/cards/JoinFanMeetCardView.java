package fan.view.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class JoinFanMeetCardView {

    public JoinFanMeetCardView() {}

    @FXML
    private Text meetIDLB, dateLB, timeLB, durationLB, idolNameLB;

    @FXML
    private Button joinBT;

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

    public Button getJoinBT() {
        return joinBT;
    }
} // end of JoinFanMeetCardView class
