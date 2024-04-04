package fan.controller;

import fan.model.BookingsModel;
import fan.view.BookingsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
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
    } // end of setUpBTEvents
} // end of Bookings class
