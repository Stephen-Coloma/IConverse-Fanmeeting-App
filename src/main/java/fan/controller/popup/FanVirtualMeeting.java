package fan.controller.popup;

import fan.model.FanVirtualMeetingModel;
import fan.view.popup.FanVirtualMeetingView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdbc.FanJDBC;

import java.io.IOException;
import java.sql.Time;

public class FanVirtualMeeting {
    private FanVirtualMeetingModel model;
    private FanVirtualMeetingView view;
    private Stage popupStage;
    private int duration;

    public FanVirtualMeeting(FanVirtualMeetingModel model, FanVirtualMeetingView view) {
        this.model = model;
        this.view = view;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/FanVirtualMeeting.fxml"));

        Scene virtualMeeting = new Scene(loader.load());

        view = loader.getController();

        popupStage = new Stage();
        popupStage.setScene(virtualMeeting);
        popupStage.setFullScreen(false);
        popupStage.setResizable(false);
        popupStage.setTitle("Virtual Meet");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();

        setUpLabels();
        setUpTimer();
    } // end of init

    private void setUpLabels() {
        duration = model.getBooking().getDuration();

        view.getHeadingLB().setText(model.getBooking().getFanMeetID().getIdolName().getName() + "'s FAN MEETING");
        view.getMeetIDLB().setText(model.getBooking().getFanMeetID().getFanMeetID() + "");
        view.getTimeRemainingLB().setText("Time left: " + duration + " minutes");
    }

    private void setUpTimer() {
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            duration--;
            if (duration >= 0) {
                view.getTimeRemainingLB().setText("Time left: " + duration + " minutes");
            } else {
                // TODO: set the fanmeet status in the DB as finished
                FanJDBC.updateJoinedFanmeetStatus(model.getBooking().getBookingID());
                view.getTimeRemainingLB().setText("Virtual Meet has Ended");
                timeline.stop();
            }
        });

        timeline.getKeyFrames().add(keyFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    } // end of setUpTimer
} // end of FanVirtualMeeting class
