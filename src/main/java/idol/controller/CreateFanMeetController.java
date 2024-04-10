package idol.controller;

import idol.Idol;
import idol.model.CreateFanMeetModel;
import idol.view.CreateFanMeetView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shared.Fanmeet;
import shared.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

public class CreateFanMeetController {
    private final CreateFanMeetModel model;
    private CreateFanMeetView view;
    private Stage popupStage;

    public CreateFanMeetController(CreateFanMeetModel model, CreateFanMeetView view) {
        this.model = model;
        this.view = view;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/Idol/CreateNewFanMeetView.fxml"));

        Scene createFanMeet = new Scene(loader.load());

        view = loader.getController();

        setUpAddFanMeetBT();

        popupStage = new Stage();
        popupStage.setScene(createFanMeet);
        popupStage.setFullScreen(false);
        popupStage.setResizable(false);
        popupStage.setTitle("Create Fan Meet");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();
    } // end of init

    private void setUpAddFanMeetBT() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("[H:mm][h:mm a]")
                .toFormatter(Locale.ENGLISH);

        view.getAddFanMeetBT().setOnAction(event -> {
            LocalDate dateToday = LocalDate.now(), chosenDate;
            LocalTime startTime, endTime;
            double pricePerMinute;

            if (view.getChosenDate().getValue() == null ||
                    view.getStartTimeTF().getText().isEmpty() ||
                    view.getEndTimeTF().getText().isEmpty() ||
                    view.getPricePerMinuteTF().getText().isEmpty()) {
                view.getNoticeLB().setText("Please provide all necessary details");
                return;
            }

            // parse the date and time provided by the idol or admin
            try {
                chosenDate = view.getChosenDate().getValue();
                startTime = LocalTime.parse(view.getStartTimeTF().getText(), formatter);
                endTime = LocalTime.parse(view.getEndTimeTF().getText(), formatter);
                pricePerMinute = Double.parseDouble(view.getPricePerMinuteTF().getText());
            } catch (Exception exception) {
                view.getNoticeLB().setText("Ensure that all details provided are correct");
                return;
            }

            // check if startTime is before the endTime
            if (startTime.isAfter(endTime)) {
                view.getNoticeLB().setText("The start time should be before the end time");
                return;
            }

            // check if chosen date is in the future
            if (chosenDate.isBefore(dateToday)) {
                view.getNoticeLB().setText("The chosen date has already elapsed");
                return;
            }

            // create Fanmeet object
            User idol = new User(Idol.USER_ID, null, null, null, null, null, null, null, null);
            Fanmeet fanmeet = new Fanmeet(0, idol, chosenDate, startTime, endTime, pricePerMinute, "Unfinished");

            // check for conflicts with existing fan meets
            if (checkForConflicts(fanmeet)) {
                view.getNoticeLB().setText("The time of the fanmeet overlaps an existing fanmeet");
                return;
            }

            // add the new fanmeet to the DB if all details provided are correct and that there are no conflicts with other schedules
            model.addFanMeet(fanmeet);
            popupStage.close();
        });
    } // end of setUpAddFanMeetBT

    private boolean checkForConflicts(Fanmeet fanmeet) {
        List<Fanmeet> fanmeetList = model.getFanMeetList(Idol.USER_ID);

        return fanmeetList.stream().anyMatch(existingFanMeet -> {
            // check if the dates are the same
           boolean sameDate = existingFanMeet.getDate().isEqual(fanmeet.getDate());

           // check if there is an overlap in the set startTime and endTime
           boolean overlappingTimes =
                   (fanmeet.getStartTime().isAfter(existingFanMeet.getStartTime()) &&
                           fanmeet.getStartTime().isBefore(existingFanMeet.getEndTime())) ||
                           (fanmeet.getEndTime().isAfter(existingFanMeet.getStartTime()) &&
                                   fanmeet.getEndTime().isBefore(existingFanMeet.getEndTime()));

           // check if the startTime and endTime is the same with the startTime and endTime of the existing fanmeet
           boolean sameStartOrEndTimes =
                   fanmeet.getStartTime().equals(existingFanMeet.getStartTime()) ||
                           fanmeet.getEndTime().equals(existingFanMeet.getEndTime()) ||
                           fanmeet.getStartTime().equals(existingFanMeet.getEndTime()) ||
                           fanmeet.getEndTime().equals(existingFanMeet.getStartTime());

           return sameDate && (overlappingTimes || sameStartOrEndTimes);
        });
    } // end of checkForConflicts
} // end of CreateFanMeetController class
