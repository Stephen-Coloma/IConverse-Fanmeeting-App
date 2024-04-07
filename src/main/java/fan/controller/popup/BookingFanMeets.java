package fan.controller.popup;

import fan.Fan;
import fan.model.BookingFanMeetsModel;
import fan.view.popup.BookingFanMeetsView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shared.Booking;
import shared.Fanmeet;
import shared.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class BookingFanMeets {
    private final BookingFanMeetsModel model;
    private BookingFanMeetsView view;
    private Timer dateDebounceTimer = new Timer(), durationDebounceTimer = new Timer();
    private boolean validTime, validDuration;
    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[H:mm][h:mm a]")
            .toFormatter(Locale.ENGLISH);
    private LocalTime chosenTime, chosenEndTime;
    private Timer timer = new Timer();

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
        setUpConfirmBT();

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

    private void setUpConfirmBT() {
        view.getConfirmBT().setOnAction(event -> {
            if (validTime && validDuration) {
                User user = new User(Fan.USER_ID, null, null, null, null, null, null, null, null);
                Fanmeet fanmeet = model.getFanmeet();
                LocalDateTime timestamp = LocalDateTime.now();
                int duration = Integer.parseInt(view.getDurationTF().getText());
                double price = Double.parseDouble(view.getPriceLB().getText().substring(1));

                Booking booking = new Booking(0, user, fanmeet, timestamp, chosenTime, duration, price);

                // TODO: use the model to add the booking to the DB
            } else {
                view.getNoticeLB().setText("Unable to book the fan meet, please check the time and duration");

                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        view.getNoticeLB().setText("");
                    }
                }, 5000);
            }
        });
    } // end of setUpConfirmBT

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
                            setPriceToZero();
                            System.out.println("Invalid Time");
                            validTime = false;
                        }
                    } catch (DateTimeParseException e) {
                        setPriceToZero();
                        validTime = false; }
                }
            }, 500);
        });

        view.getDurationTF().textProperty().addListener((observable, oldValue, newValue) -> {
            durationDebounceTimer.cancel();
            durationDebounceTimer = new Timer();

            durationDebounceTimer.schedule(new TimerTask() {
                @Override
                public void run() {
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
                                setPriceToZero();
                                System.out.println("Invalid Duration");
                                validDuration = false;
                            }
                        }
                    } catch (NumberFormatException e) {
                        setPriceToZero();
                        validDuration = false; }
                }
            }, 500);
        });
    } // end of setUpTFListeners

    public void computePrice() {
        if (validTime && validDuration) {
            double pricePerMinute = model.getFanmeet().getPricePerMinute();
            int duration = Integer.parseInt(view.getDurationTF().getText());

            double price = duration * pricePerMinute;

            Platform.runLater(() -> view.getPriceLB().setText("P" + price));
        } else {
            setPriceToZero();
        }
    } // end of computePrice

    public void setPriceToZero() {
        Platform.runLater(() -> view.getPriceLB().setText("P0"));
    } // end of setPriceToZero
} // end of BookingFanMeets class
