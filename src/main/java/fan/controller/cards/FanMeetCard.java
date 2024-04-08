package fan.controller.cards;

import fan.view.cards.FinishedFanMeetCardView;
import fan.view.cards.UnfinishedFanMeetCardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import shared.Booking;

import java.io.IOException;

public class FanMeetCard {

    public static Node createFinishedFMCard(Booking booking) {
        FXMLLoader loader = new FXMLLoader(FanMeetCard.class.getResource("/fxmls/fan/FinishedFanMeetsCard.fxml"));

        Node finishedFMCard = null;
        try {
            finishedFMCard = loader.load();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }

        FinishedFanMeetCardView view = loader.getController();

        view.getMeetIDLB().setText(booking.getFanMeetID().getFanMeetID() + "");
        view.getDateLB().setText(booking.getFanMeetID().getDate().toString());
        view.getTimeLB().setText(booking.getStartTime().toString());
        view.getDurationLB().setText(booking.getDuration() + "");
        view.getIdolNameLB().setText(booking.getFanMeetID().getIdolName().getName());
        view.getTimestampLB().setText("Booked on " + booking.getTimeStamp().toLocalDate() + " " + booking.getTimeStamp().toLocalTime());
        view.getPriceLB().setText(booking.getPrice() + "");

        return finishedFMCard;
    } // end of createFinishedFMCard

    public static Node createUnfinishedFMCard(Booking booking) {
        FXMLLoader loader = new FXMLLoader(FanMeetCard.class.getResource("/fxmls/fan/UnfinishedFanMeetsCard.fxml"));

        Node unfinishedFMCard = null;
        try {
            unfinishedFMCard = loader.load();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }

        UnfinishedFanMeetCardView view = loader.getController();

        view.getMeetIDLB().setText(booking.getFanMeetID().getFanMeetID() + "");
        view.getDateLB().setText(booking.getFanMeetID().getDate().toString());
        view.getTimeLB().setText(booking.getStartTime().toString());
        view.getDurationLB().setText(booking.getDuration() + "");
        view.getIdolNameLB().setText(booking.getFanMeetID().getIdolName().getName());
        view.getTimestampLB().setText("Booked on " + booking.getTimeStamp().toLocalDate() + " " + booking.getTimeStamp().toLocalTime());

//        view.getCancelBT().setOnAction(event -> {}); TODO: add the cancel button event when clicked

        return unfinishedFMCard;
    } // end of createUnfinishedFMCard
} // end of FanMeetCard class
