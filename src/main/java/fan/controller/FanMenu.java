package fan.controller;

import fan.controller.cards.IdolCard;
import fan.model.FanMenuModel;
import fan.model.UpcomingFanMeetsModel;
import fan.view.FanMenuView;
import fan.view.UpcomingFanMeetsView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import shared.User;

import java.io.IOException;
import java.util.*;

public class FanMenu {
    public static Parent FAN_MENU_VIEW;
    private static final long DEBOUNCE_DELAY = 500;
    private FanMenuModel model;
    private FanMenuView view;
    private StackPane stackPane;
    private Timer debounceTimer = new Timer();

    public FanMenu(FanMenuModel model, FanMenuView view, StackPane stackPane) {
        this.model = model;
        this.view = view;
        this.stackPane = stackPane;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/FanMenuUI.fxml")); // obtain the fxml loader

        FAN_MENU_VIEW = loader.load(); // establish the view

        view = loader.getController(); // assign the fxml controller to the view

        // add the external css to the scene
        FAN_MENU_VIEW.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/fan/fan-menu-view.css")).toExternalForm());

        // set up search bar
        setUpSearchBar();
    } // end of init

    private void setUpSearchBar() {
        view.getSearchBar().textProperty().addListener((observable, oldValue, newValue) -> filterIdols(newValue));
    } // end of setUpSearchBar

    public void loadFlowPaneCatalogue() {
        view.getFlowPane().getChildren().clear();

        List<User> idolList = model.getIdolList();
        List<Node> idolCards = new ArrayList<>();

        for (User idol : idolList) {
            Node node = IdolCard.createIdolCard(idol);
            idolCards.add(node);

            node.setOnMouseClicked(event -> {
                try {
                    UpcomingFanMeets upcomingFanMeets = new UpcomingFanMeets(new UpcomingFanMeetsModel(idol.getUserID()), new UpcomingFanMeetsView(), stackPane);
                    upcomingFanMeets.init();
                    stackPane.getChildren().setAll(UpcomingFanMeets.UPCOMING_FAN_MEETS_VIEW);

                    upcomingFanMeets.getBackBT().setOnMouseClicked(event1 -> {
                        stackPane.getChildren().setAll(FanMenu.FAN_MENU_VIEW);
                    });
                } catch (IOException ioException) {
                    ioException.getCause().printStackTrace();
                }
            });
        }

        view.getFlowPane().getChildren().addAll(idolCards);
    } // end of setUpFlowPaneItems

    private void filterIdols(String searchText) {
        debounceTimer.cancel();
        debounceTimer = new Timer();

        debounceTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    view.getFlowPane().getChildren().clear();

                    Task<Void> filterTask = new Task<>() {
                        @Override
                        protected Void call() {
                            List<Node> filteredIdols;

                            if (!searchText.isEmpty()) {
                                filteredIdols = model.getIdolList().stream()
                                        .filter(idol -> idol.getName().toLowerCase().contains(searchText.toLowerCase()))
                                        .map(IdolCard::createIdolCard)
                                        .toList();
                            } else {
                                filteredIdols = model.getIdolList().stream()
                                        .map(IdolCard::createIdolCard)
                                        .toList();
                            }

                            Platform.runLater(() -> view.getFlowPane().getChildren().setAll(filteredIdols));

                            return null;
                        }
                    };
                    Thread filterThread = new Thread(filterTask);
                    filterThread.setDaemon(true);
                    filterThread.start();
                });
            }
        }, DEBOUNCE_DELAY);
    } // end of filterIdols
} // end of FanMenu class
