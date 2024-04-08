package fan.controller.cards;

import fan.controller.popup.FanVirtualMeeting;
import fan.model.FanVirtualMeetingModel;
import fan.view.cards.JoinFanMeetCardView;
import fan.view.popup.FanVirtualMeetingView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import shared.Booking;

import java.io.IOException;

public class JoinFanMeetCard {

    public static Node createJoinFanMeetCard(Booking booking) {
        FXMLLoader loader = new FXMLLoader(JoinFanMeetCard.class.getResource("/fxmls/fan/JoinFanMeetsCard.fxml"));

        Node joinFanMeetCard = null;
        try {
            joinFanMeetCard = loader.load();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }

        JoinFanMeetCardView view = loader.getController();

        view.getMeetIDLB().setText(booking.getFanMeetID().getFanMeetID() + "");
        view.getDateLB().setText(booking.getFanMeetID().getDate().toString());
        view.getTimeLB().setText(booking.getStartTime().toString());
        view.getDurationLB().setText(booking.getDuration() + "");
        view.getIdolNameLB().setText(booking.getFanMeetID().getIdolName().getName());

        view.getJoinBT().setOnAction(event -> {
            // TODO: add the function to load the video call
            try {
                FanVirtualMeeting virtualMeeting = new FanVirtualMeeting(new FanVirtualMeetingModel(booking), new FanVirtualMeetingView());
                virtualMeeting.init();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        return joinFanMeetCard;
    } // end of createJoinFanMeetCard


} // end of JoinFanMeetCard class
