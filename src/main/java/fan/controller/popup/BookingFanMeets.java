package fan.controller.popup;

import fan.model.BookingFanMeetsModel;
import fan.view.popup.BookingFanMeetsView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shared.Booking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class BookingFanMeets {
    private BookingFanMeetsModel model;
    private BookingFanMeetsView view;
    private Timer dateDebounceTimer = new Timer(), durationDebounceTimer = new Timer();
    private boolean validTime, validDuration;
    private DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[H:mm][h:mm a]")
            .toFormatter(Locale.ENGLISH);
    private LocalTime chosenTime, chosenEndTime;

    public BookingFanMeets(BookingFanMeetsModel model, BookingFanMeetsView view) {
        this.model = model;
        this.view = view;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/BookingFanmeets.fxml"));

        Scene fanMeetBookingScene = new Scene(loader.load());
        fanMeetBookingScene.getRoot().requestFocus();

        view = loader.getController();

        setUpComponents();
        setUpTFListeners();

        Stage popupStage = new Stage();
        popupStage.setScene(fanMeetBookingScene);
        popupStage.setFullScreen(false);
        popupStage.setResizable(false);
        popupStage.setTitle("Booking");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();
    } // end of init

    private void setUpComponents() {
        view.getProfilePicture().setImage(new Image(new ByteArrayInputStream(model.getIdol().getProfilePicture())));
        view.getHeadingLB().setText(model.getIdol().getName() + "'s Fan Meet");
        view.getDateLB().setText(model.getFanmeet().getDate().toString());
        view.getPriceLB().setText("P0");
    } // end of setUpComponents

    private void setUpTFListeners() {
        view.getTimeTF().textProperty().addListener((observable, oldValue, newValue) -> {
            dateDebounceTimer.cancel();
            dateDebounceTimer = new Timer();

            dateDebounceTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        chosenTime = LocalTime.parse(newValue, formatter);
                        LocalTime startTime = model.getFanmeet().getStartTime();
                        LocalTime endTime = model.getFanmeet().getEndTime();

                        if ((chosenTime.equals(startTime) || chosenTime.isAfter(startTime)) && chosenTime.isBefore(endTime)) {
                            System.out.println("Valid time");
                            validTime = true;

                            // compute the price
                            computePrice();
                        } else {
                            System.out.println("Invalid Time");
                            validTime = false;
                        }
                    } catch (DateTimeParseException e) { validTime = false; }
                }
            }, 500);
        });

        view.getDurationTF().textProperty().addListener((observable, oldValue, newValue) -> {
            // TODO: add a listener for the duration textfield
            durationDebounceTimer.cancel();
            durationDebounceTimer = new Timer();

            durationDebounceTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO: add the logic for checking if the duration is valid

                    try {
                        if (chosenTime != null) {
                            chosenEndTime = chosenTime.plusMinutes(Integer.parseInt(newValue));

                            LocalTime startTime = model.getFanmeet().getStartTime();
                            LocalTime endTime = model.getFanmeet().getEndTime();

                            if ((chosenEndTime.equals(startTime) || chosenEndTime.isAfter(startTime)) && (chosenEndTime.isBefore(endTime) || chosenEndTime.equals(endTime))) {
                                System.out.println("Valid Duration");
                                validDuration = true;

                                // compute the price
                                computePrice();
                            } else {
                                System.out.println("Invalid Duration");
                                validDuration = false;
                            }
                        }
                    } catch (NumberFormatException e) { validDuration = false; }
                }
            }, 500);
        });
    } // end of setUpTFListeners

    public void computePrice() {
        // TODO: compute the price, first check if the time and duration placed are valid

        if (validTime && validDuration) {
            System.out.println("computation succeeded");
        } else {
            System.out.println("computation failed, please check the time and duration");
        }
    }
} // end of BookingFanMeets class
