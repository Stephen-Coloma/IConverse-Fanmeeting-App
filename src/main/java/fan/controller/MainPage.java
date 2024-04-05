package fan.controller;

import authentication.IConverse;
import fan.model.BookingsModel;
import fan.model.FanMenuModel;
import fan.model.MainPageModel;
import fan.view.BookingsView;
import fan.view.FanMenuView;
import fan.view.MainPageView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPage {
    public static Scene MAIN_PAGE;
    private MainPageModel model;
    private MainPageView view;
    private Bookings bookings;
    private FanMenu fanMenu;

    public MainPage(MainPageModel model, MainPageView view) {
        this.model = model;
        this.view = view;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/FanMainPage.fxml")); // obtain the fxml loader

        MAIN_PAGE = new Scene(loader.load()); // establish the scene

        view = loader.getController(); // assign the fxml controller to the view

        // add the external css to the scene
        MAIN_PAGE.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/fan/main-page.css")).toExternalForm());

        IConverse.STAGE.setScene(MAIN_PAGE); // set the application stage to the main page

        setUpMenuViewControllers(); // instantiate all view controllers

        setUpMenuBTEvents(); // set up menu button events
    } // end of init

    private void setUpMenuViewControllers() throws IOException {
        bookings = new Bookings(new BookingsModel(), new BookingsView());
        fanMenu = new FanMenu(new FanMenuModel(), new FanMenuView(), view.getStackPane());

        bookings.init();
        fanMenu.init();
    } // setUpMenuViewControllers

    private void setUpMenuBTEvents() {
        Platform.runLater(() -> {
            view.getBookingsBT().setOnAction(event -> view.getStackPane().getChildren().setAll(Bookings.BOOKINGS_VIEW));
            view.getFanmeetsBT().setOnAction(event -> {
                view.getStackPane().getChildren().setAll(FanMenu.FAN_MENU_VIEW);
                fanMenu.loadFlowPaneCatalogue();
            });
        });
    } // end of setUpMenuBTEvents
} // end of MainPage class
