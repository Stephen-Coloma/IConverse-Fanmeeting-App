package fan.controller;

import fan.Fan;
import fan.controller.cards.JoinFanMeetCard;
import fan.model.JoinFanMeetsModel;
import fan.view.JoinFanMeetsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import shared.Booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JoinFanMeets {
    public static Parent JOIN_FAN_MEETS_VIEW;
    private JoinFanMeetsModel model;
    private JoinFanMeetsView view;

    public JoinFanMeets(JoinFanMeetsModel model, JoinFanMeetsView view) {
        this.model = model;
        this.view = view;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/JoinFanmeets.fxml")); // obtain the fxml loader

        JOIN_FAN_MEETS_VIEW = loader.load(); // establish the view

        view = loader.getController(); // assign the fxml controller to the view

        // add the external css to the scene
        JOIN_FAN_MEETS_VIEW.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/fan/join-fan-meets-view.css")).toExternalForm());

    } // end of init

    public void loadBookedFanMeets() {
        view.getFlowPane().getChildren().clear();
        List<Booking> bookings = model.getBookedFanMeetList(Fan.USER_ID);
        List<Node> joinFanMeetCards = new ArrayList<>();

        for (Booking booking : bookings) {
            joinFanMeetCards.add(JoinFanMeetCard.createJoinFanMeetCard(booking));
        }

        view.getFlowPane().getChildren().addAll(joinFanMeetCards);
    } // end of displayAvailableFanMeets
} // end of JoinFanMeets class
