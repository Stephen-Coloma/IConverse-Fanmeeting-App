package idol.controller;

import idol.controller.FanMeetDetailsIdolView;
import idol.model.FinishedFanMeetsViewCardModel;
import idol.view.FeedbackCardView;
import idol.view.FeedbackPopupView;
import idol.view.FinishedFanMeetsViewCardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import jdbc.IdolJDBC;
import shared.Fanmeet;
import shared.Feedback;

import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FinishedFanMeetsViewCardController {
    private FinishedFanMeetsViewCardView view;
    private FinishedFanMeetsViewCardModel model;

    public FinishedFanMeetsViewCardController(FinishedFanMeetsViewCardView view, FinishedFanMeetsViewCardModel model) {
        this.view = view;
        this.model = model;

        //set up action for button
        this.view.setUpActionViewDetailsButton(event -> {
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/FanMeetDetailsIdol.fxml"));
               Parent root = loader.load();

               // I did not create MVC already for FanMeetDetailsIdolView
               FanMeetDetailsIdolView fanMeetDetailsIdolView = loader.getController();

               //setting up the image
               File file = new File("src/main/resources/idol/images/profile-user.png");
               byte[] imageBytes = Files.readAllBytes(file.toPath());
               ByteArrayInputStream byteArray = new ByteArrayInputStream(imageBytes);
               Image userImage = new Image(byteArray);

               fanMeetDetailsIdolView.getImageView().setImage(userImage);
               fanMeetDetailsIdolView.getDateLabel().setText(model.getFanmeet().getDate().toString());
               fanMeetDetailsIdolView.getPricePerMinuteLabel().setText("P " + model.getFanmeet().getPricePerMinute() + "0");

               //logic to calculate the duration
               Duration duration = Duration.between(model.getFanmeet().getStartTime(), model.getFanmeet().getEndTime());
               // Get the total duration in minutes
               long totalMinutes = duration.toMinutes();
               // Store the total duration in minutes as a string
               String durationString = String.valueOf(totalMinutes);

               fanMeetDetailsIdolView.getDurationLabel().setText(durationString);


               Scene scene = new Scene(root);

               Stage stage = new Stage();

               stage.setScene(scene);
               stage.setTitle("Fanmeet Details");
               stage.showAndWait();
           }catch (Exception e){
               e.printStackTrace();
           }
        });

        //set up action for feedbacks button
        this.view.setUpActionViewFeedbacksButton(event -> {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/FeedbackPopupView.fxml"));
              Parent root = loader.load();

              FeedbackPopupView feedbackPopupView = loader.getController();
              //did not create really the MVC for FeedbackPopupView class
              List<Feedback> feedbackList = IdolJDBC.loadFeedbacks(model.getFanmeet().getFanMeetID());
              populateScrollPaneWithFeedbacks(feedbackList, feedbackPopupView);

              Scene scene = new Scene(root);

              Stage stage = new Stage();

              stage.setScene(scene);
              stage.setTitle("Fanmeet Feedbacks");
              stage.showAndWait();
          }catch (Exception e){
              e.printStackTrace();
          }
        });
    }

    public void setData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        view.getDateLabel().setText("Date: " + model.getFanmeet().getDate().format(formatter));
        view.getTimeLabel().setText("Time: " + model.getFanmeet().getStartTime() + " - " + model.getFanmeet().getEndTime());
    }

    private void populateScrollPaneWithFeedbacks(List<Feedback> feedbackList, FeedbackPopupView feedbackPopupView){
        //initial setting the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        feedbackPopupView.getDateLabel().setText(model.getFanmeet().getDate().format(formatter) + " Fanmeet Feedbacks");

        ScrollPane scrollPane = feedbackPopupView.getScrollpane();
        FlowPane flowPane = (FlowPane) scrollPane.getContent();
        flowPane.getChildren().clear();

        for (Feedback feedback: feedbackList) {
            // Load another FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/FeedbackCard.fxml"));

            try {
                Node anotherFXMLRoot = loader.load();
                FeedbackCardView feedbackCardView = loader.getController();

                //setting up the data for the feedback list
                feedbackCardView.getFeedbackLabel().setText(feedback.getFeedback());
                feedbackCardView.getFanUsernameLabel().setText(feedback.getUsersID().getUsername());

                ByteArrayInputStream imageBytes = new ByteArrayInputStream(feedback.getUsersID().getProfilePicture());
                Image userImage = new Image(imageBytes);
                feedbackCardView.getImageView().setImage(userImage);

                // Add the root node of the loaded FXML to the FlowPane
                flowPane.getChildren().add(anotherFXMLRoot);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception appropriately
            }
        }
    }
}
