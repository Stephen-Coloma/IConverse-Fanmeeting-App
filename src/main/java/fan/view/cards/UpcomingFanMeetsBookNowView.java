package fan.view.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class UpcomingFanMeetsBookNowView {

    @FXML
    private Text dateLB, timeLB, priceLB;
    @FXML
    private Button bookNowBT;

    public Text getDateLB() {
        return dateLB;
    }

    public Text getTimeLB() {
        return timeLB;
    }

    public Text getPriceLB() {
        return priceLB;
    }

    public Button getBookNowBT() {
        return bookNowBT;
    }
} // end of UpcomingFanMeetsBookNowView class
