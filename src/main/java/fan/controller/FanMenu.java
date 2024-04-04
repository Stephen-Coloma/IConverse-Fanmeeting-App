package fan.controller;

import fan.controller.cards.IdolCard;
import fan.model.FanMenuModel;
import fan.view.FanMenuView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import jdbc.FanJDBC;
import shared.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FanMenu {
    public static Parent FAN_MENU_VIEW;
    private FanMenuModel model;
    private FanMenuView view;

    public FanMenu(FanMenuModel model, FanMenuView view) {
        this.model = model;
        this.view = view;
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

    } // end of setUpSearchBar

    public void loadFlowPaneCatalogue() {
        view.getFlowPane().getChildren().clear();

        List<User> idolList = model.getIdolList();
        List<Node> idolCards = new ArrayList<>();

        for (User idol : idolList) {
            idolCards.add(IdolCard.createIdolCard(idol));
        }

        view.getFlowPane().getChildren().addAll(idolCards);
    } // end of setUpFlowPaneItems
} // end of FanMenu class
