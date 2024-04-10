package fan.controller;

import fan.controller.cards.UpcomingFanMeetsBookNowCard;
import fan.model.UpcomingFanMeetsModel;
import fan.view.UpcomingFanMeetsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import shared.Fanmeet;
import shared.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UpcomingFanMeets {
    public static Parent UPCOMING_FAN_MEETS_VIEW;
    private UpcomingFanMeetsModel model;
    private UpcomingFanMeetsView view;
    private StackPane stackPane;
    private User idol;

    public UpcomingFanMeets(UpcomingFanMeetsModel model, UpcomingFanMeetsView view, StackPane stackPane) {
        this.model = model;
        this.view = view;
        this.stackPane = stackPane;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/UpcomingFanMeetsPage.fxml")); // obtain the fxml loader

        UPCOMING_FAN_MEETS_VIEW = loader.load(); // establish the view

        view = loader.getController(); // assign the fxml controller to the view

        // add the external css to the scene
        UPCOMING_FAN_MEETS_VIEW.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/fan/upcoming-fan-meets-view.css")).toExternalForm());

        // set up the details
        setUpIdolDetailsPane();
        setUpUpcomingFanMeets();
    } // end of init

    private void setUpIdolDetailsPane() {
        idol = model.getIdol();

        view.getIdolNameLB().setText(idol.getName());
        view.getBioLB().setText(idol.getBio());
        view.getProfilePicture().setImage(new Image(new ByteArrayInputStream(idol.getProfilePicture())));
    } // end of setUpIdolDetailsPane

    private void setUpUpcomingFanMeets() {
        List<Fanmeet> fanmeetList = model.getFanMeetList();
        List<Node> upcomingFMBNCard = new ArrayList<>();

        for (Fanmeet fanmeet : fanmeetList) {
            Node node = UpcomingFanMeetsBookNowCard.createUpcomingFanMeetsBookNowCard(fanmeet, idol);
            upcomingFMBNCard.add(node);
        }

        view.getFlowPane().getChildren().addAll(upcomingFMBNCard);
    } // end of setUpUpcomingFanMeets

    public ImageView getBackBT() {
        return view.getBackBT();
    }
} // end of UpcomingFanMeets class
