package idol.controller;

import idol.model.CancelConfirmationModel;
import idol.model.EditFanMeetIdolModel;
import idol.model.UpcomingFanMeetsViewCardModel;
import idol.view.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import jdbc.IdolJDBC;
import shared.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class UpcomingFanMeetsViewCardController {
    private UpcomingFanMeetsViewCardView view;
    private UpcomingFanMeetsViewCardModel model;

    public UpcomingFanMeetsViewCardController(UpcomingFanMeetsViewCardView view, UpcomingFanMeetsViewCardModel model) {
        this.view = view;
        this.model = model;

        //set up action for List of booked fans button
        this.view.setUpActionListOfBookFansButton(event -> {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/ListOfBookedFansPopupView.fxml"));
               Parent root = loader.load();

               ListOfBookedFansPopupViewView listOfBookedFansPopupViewView = loader.getController();
               // I did not create MVC already for FanMeetDetailsIdolView
               HashMap<String, User> userTimeStamp = IdolJDBC.loadBookedFans(model.getFanmeet().getFanMeetID());
               populateListOfBookedFans(userTimeStamp, listOfBookedFansPopupViewView);

               Scene scene = new Scene(root);

               Stage stage = new Stage();

               stage.setScene(scene);
               stage.setTitle("Booked Fans");
               stage.showAndWait();
           }catch (Exception e){
               e.printStackTrace();
           }
        });

        this.view.setUpActionEditDetailsButton(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/EditFanMeetIdol.fxml"));
                Parent root = loader.load();

                EditFanMeetIdolView editFanMeetIdolView = loader.getController();
                EditFanMeetIdolModel editFanMeetIdolModel = new EditFanMeetIdolModel(model.getFanmeet());
                EditFanMeetIdolController editFanMeetIdolController = new EditFanMeetIdolController(editFanMeetIdolView, editFanMeetIdolModel);
                editFanMeetIdolController.setData();


                Scene scene = new Scene(root);

                Stage stage = new Stage();

                stage.setScene(scene);
                stage.setTitle("Booked Fans");
                stage.showAndWait();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        this.view.setUpActionDeleteButton(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/Idol/CancelFanMeet.fxml"));
                Parent root = loader.load();

                CancelConfirmationView cancelConfirmationView = loader.getController();
                CancelConfirmationModel cancelConfirmationModel = new CancelConfirmationModel(model.getFanmeet());
                CancelFanMeetController cancelFanMeetController = new CancelFanMeetController(cancelConfirmationView, cancelConfirmationModel);

                Scene scene = new Scene(root);

                Stage stage = new Stage();

                stage.setScene(scene);
                stage.setTitle("Booked Fans");
                stage.showAndWait();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void populateListOfBookedFans(HashMap<String, User> userTimeStamp, ListOfBookedFansPopupViewView listOfBookedFansPopupViewView) {
        ScrollPane scrollPane = listOfBookedFansPopupViewView.getScrollPane();
        FlowPane flowPane = (FlowPane) scrollPane.getContent();
        flowPane.getChildren().clear();

        for (String timeStamp: userTimeStamp.keySet()) {
            // Load another FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/ListOfBookedFansCard.fxml"));

            try {
                Node anotherFXMLRoot = loader.load();
                ListOfBookedFansCardView listOfBookedFansCardView = loader.getController();

                //setting up the data for the feedback list
                listOfBookedFansCardView.getTimestampLabel().setText(timeStamp);

                User user = userTimeStamp.get(timeStamp);
                listOfBookedFansCardView.getFanNameLabel().setText(user.getName());
                listOfBookedFansCardView.getFanUsernameLabel().setText(user.getUsername());
                listOfBookedFansCardView.getFanEmailLabel().setText(user.getEmail());

                ByteArrayInputStream imageBytes = new ByteArrayInputStream(user.getProfilePicture());
                Image userImage = new Image(imageBytes);
                listOfBookedFansCardView.getImageView().setImage(userImage);

                // Add the root node of the loaded FXML to the FlowPane
                flowPane.getChildren().add(anotherFXMLRoot);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception appropriately
            }
        }
    }

    public UpcomingFanMeetsViewCardView getView() {
        return view;
    }

    public void setView(UpcomingFanMeetsViewCardView view) {
        this.view = view;
    }

    public UpcomingFanMeetsViewCardModel getModel() {
        return model;
    }

    public void setModel(UpcomingFanMeetsViewCardModel model) {
        this.model = model;
    }

    public void setData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        view.getDateLabel().setText("Date: " + model.getFanmeet().getDate().format(formatter));
        view.getTimeLabel().setText("Time: " + model.getFanmeet().getStartTime() + " - " + model.getFanmeet().getEndTime());
    }


}
