package fan.controller.cards;

import fan.view.UpcomingFanMeetsView;
import fan.view.cards.UpcomingFanMeetsBookNowView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import shared.Fanmeet;

import java.io.IOException;

public class UpcomingFanMeetsBookNowCard {

    public static Node createUpcomingFanMeetsBookNowCard(Fanmeet fanmeet) {
        FXMLLoader loader = new FXMLLoader(UpcomingFanMeetsBookNowCard.class.getResource("/fxmls/fan/UpcomingFanMeetsBookNow.fxml"));

        Node upcomingFMBNCard = null;
        try {
            upcomingFMBNCard = loader.load();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }

        UpcomingFanMeetsBookNowView view = loader.getController();

        view.getDateLB().setText(fanmeet.getDate().toString());
        view.getPriceLB().setText("P " + fanmeet.getPricePerMinute());
        view.getTimeLB().setText(fanmeet.getStartTime() + " - " + fanmeet.getEndTime());

        return upcomingFMBNCard;
    }
} // end of UpcomingFanMeetsBookNowCard class
