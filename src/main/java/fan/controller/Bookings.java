package fan.controller;

import fan.Fan;
import fan.controller.cards.FanMeetCard;
import fan.model.BookingsModel;
import fan.view.BookingsView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import jdbc.FanJDBC;
import shared.Booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bookings {
    public static Parent BOOKINGS_VIEW;
    private BookingsModel model;
    private BookingsView view;

    public Bookings(BookingsModel model, BookingsView view) {
        this.model = model;
        this.view = view;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/BookingsUI.fxml"));  // obtain the fxml loader

        BOOKINGS_VIEW = loader.load(); // establish the view

        view = loader.getController(); // assign the fxml controller to the view

        // add the external css to the scene
        BOOKINGS_VIEW.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/fan/bookings-view.css")).toExternalForm());

        // set up all button events
        setUpBTEvents();
    } // end of init

    public void setUpBTEvents() {
        Platform.runLater(() -> {
            view.getFinishedFMBT().setOnAction(event -> {
                loadBookingFlowPane(Fan.USER_ID, true);
            });
            view.getUnfinishedFMBT().setOnAction(event -> {
                loadBookingFlowPane(Fan.USER_ID, false);
            });
        });
    } // end of setUpBTEvents

    public void loadBookingFlowPane(int userID, boolean finishedBookings) {
        view.getFlowPane().getChildren().clear();

        List<Booking> bookings = model.getBookingList(userID);
        List<Node> finFMCards = new ArrayList<>();
        List<Node> unFinFMCards = new ArrayList<>();

        for (Booking booking : bookings) {
            boolean status = booking.getStatus().equalsIgnoreCase("finished");

            if (status) {
                finFMCards.add(FanMeetCard.createFinishedFMCard(booking));
            } else {
                unFinFMCards.add(FanMeetCard.createUnfinishedFMCard(booking));
            }
        }

        if (finishedBookings) {
            view.getFlowPane().getChildren().addAll(finFMCards);
        } else {
            view.getFlowPane().getChildren().addAll(unFinFMCards);
        }
    } // end of loadBookingFlowPane
} // end of Bookings class
