package fan.controller.cards;

import fan.controller.popup.CancelBooking;
import fan.model.CancelBookingModel;
import fan.view.cards.FinishedFanMeetCardView;
import fan.view.cards.UnfinishedFanMeetCardView;
import fan.view.popup.CancelBookingView;
import idol.model.CancelConfirmationModel;
import idol.view.CancelConfirmationView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

        if (booking.getStatus() == null && booking.getFanMeetID().getStatus() == null){ //idol and fan cancel both
            view.getCancelLabel().setText("Cancelled by: " + booking.getFanMeetID().getIdolName().getName() + " and " + "You");
            view.getCancelLabel().setVisible(true);
        } else if (booking.getStatus() == null) {
            view.getCancelLabel().setText("Cancelled by: " + "You");
            view.getCancelLabel().setVisible(true);
        }else if (booking.getFanMeetID().getStatus() == null){
            view.getCancelLabel().setText("Cancelled by: " + booking.getFanMeetID().getIdolName().getName());
            view.getCancelLabel().setVisible(true);
        }

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

        //loading the confirmation
        view.getCancelBT().setOnAction(event -> {
            try {
                CancelBooking cancelBooking = new CancelBooking(new CancelBookingModel(booking), new CancelBookingView());
                cancelBooking.init();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return unfinishedFMCard;
    } // end of createUnfinishedFMCard
} // end of FanMeetCard class
