package idol.controller;

import idol.model.EditFanMeetIdolModel;
import idol.view.EditFanMeetIdolView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditFanMeetIdolController {
    private EditFanMeetIdolView  view;
    private EditFanMeetIdolModel model;

    public EditFanMeetIdolController(EditFanMeetIdolView view, EditFanMeetIdolModel model) {
        this.view = view;
        this.model = model;

        //set up action for save button
        view.setUpActionSaveChangesButton(event -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

                LocalTime originalStartTime = model.getFanmeet().getStartTime();
                LocalTime editedStart    = LocalTime.parse(view.getEditStartTimeTextField().getText(), formatter);
                LocalDate originalDate = model.getFanmeet().getDate();
                LocalDate editedDate = LocalDate.parse(view.getEditDateTextField().getText());

                // Compare LocalTime objects
                int comparisonTime = originalStartTime.compareTo(editedStart);
                int comparisonDate = originalDate.compareTo(editedDate);

                // Output the comparison result
                if (comparisonDate < 0) {
                    //accept all time because date is ahead already
                    //todo: save to jdbc
                    System.out.println(editedDate);
                    System.out.println(editedStart);
                } else if (comparisonDate == 0){
                    if (comparisonTime < 0){
                        System.out.println("goods");
                        //todo: save to jdbc
                        System.out.println(editedDate);
                        System.out.println(editedStart);
                    }else {
                        view.getNoticeLabel().setText("Only edit start time ahead of original start time");
                        view.getNoticeLabel().setVisible(true);
                        setData(); //resetting the data
                    }
                } else{
                    view.getNoticeLabel().setText("Only edit date ahead of original date");
                    view.getNoticeLabel().setVisible(true);
                    setData(); //resetting the data
                }
            }catch (DateTimeParseException e){
                view.getNoticeLabel().setText("Date or Time format is invalid");
                view.getNoticeLabel().setVisible(true);
                setData();//resetting the data
            }
        });
    }

    public void setData() {
        //setting initially the data fpr tne pop up view
        view.getEditDateTextField().setText(model.getFanmeet().getDate().toString());

        String formattedStartTime = model.getFanmeet().getFormattedStartTime().toString();
        String formattedEndTime = model.getFanmeet().getFormattedEndTime();
        String formattedTime = formattedStartTime + " - " + formattedEndTime;

        view.getFinalTimeLabel().setText(formattedTime);

        view.getEditStartTimeTextField().setText(formattedStartTime);
    }

    public EditFanMeetIdolView getView() {
        return view;
    }

    public void setView(EditFanMeetIdolView view) {
        this.view = view;
    }

    public EditFanMeetIdolModel getModel() {
        return model;
    }

    public void setModel(EditFanMeetIdolModel model) {
        this.model = model;
    }
}
