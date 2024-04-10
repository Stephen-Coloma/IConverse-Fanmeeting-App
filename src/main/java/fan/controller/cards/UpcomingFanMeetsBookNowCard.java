package fan.controller.cards;

import fan.controller.popup.BookingFanMeets;
import fan.model.BookingFanMeetsModel;
import fan.view.cards.UpcomingFanMeetsBookNowView;
import fan.view.popup.BookingFanMeetsView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;
import shared.Fanmeet;
import shared.User;

import java.io.IOException;

public class UpcomingFanMeetsBookNowCard {

    public static Node createUpcomingFanMeetsBookNowCard(Fanmeet fanmeet, User idol) {
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

        addBTTransition(view.getBookNowBT());

        // add event for booking
        view.getBookNowBT().setOnAction(event -> {
            try {
                BookingFanMeets bookingFanMeets = new BookingFanMeets(new BookingFanMeetsModel(idol, fanmeet), new BookingFanMeetsView());
                bookingFanMeets.init();
            } catch (IOException ioException) {
                ioException.getCause().printStackTrace();
            }
        });

        return upcomingFMBNCard;
    } // end of createUpcomingFanMeetsBookNowCard

    private static void addBTTransition(Button button) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), button);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(150), button);

        fadeIn.setFromValue(1);
        fadeOut.setFromValue(0.7);

        fadeIn.setToValue(0.7);
        fadeOut.setToValue(1);

        button.setOnMouseEntered(event -> {
            fadeOut.stop();
            fadeIn.play();
        });

        button.setOnMouseExited(event -> {
            fadeIn.stop();
            fadeOut.play();
        });
    } // end of addBTTransition
} // end of UpcomingFanMeetsBookNowCard class
