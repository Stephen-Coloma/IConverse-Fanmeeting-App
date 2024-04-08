package fan.view.popup;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FanVirtualMeetingView {

    @FXML
    private Text headingLB, meetIDLB, timeRemainingLB;

    public FanVirtualMeetingView() {}

    public Text getHeadingLB() {
        return headingLB;
    }

    public Text getMeetIDLB() {
        return meetIDLB;
    }

    public Text getTimeRemainingLB() {
        return timeRemainingLB;
    }
} // end of FanVirtualMeetingView class
