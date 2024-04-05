package idol.controller;

import idol.model.FinishedFanMeetsViewCardModel;
import idol.model.IdolFanMeetsModel;
import idol.view.FinishedFanMeetsViewCardView;
import idol.view.IdolFanMeetsView;
import idol.view.MainMenuIdolPageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainMenuIdolController {
    private MainMenuIdolPageView view;

    private FXMLLoader loader;
    private Parent root;

    public MainMenuIdolController(MainMenuIdolPageView view) {
        this.view = view;
        loadContent("/fxmls/idol/IdolFanMeetsView.fxml");

        //set up button actions
        this.view.setUpActionFanmeetsButton(event -> {
            loadContent("/fxmls/idol/IdolFanMeetsView.fxml");
        });

        this.view.setUpActionBookingsButton(event -> {
            StackPane stackPane = view.getStackPane();
            stackPane.getChildren().clear();
        });

        this.view.setUpActionProfileButton(event -> {
            StackPane stackPane = view.getStackPane();
            stackPane.getChildren().clear();
        });

        this.view.setUpActionSettingsButton(event -> {
            StackPane stackPane = view.getStackPane();
            stackPane.getChildren().clear();
        });
    }

    public void loadContent(String fxmlPath){
        StackPane stackPane = view.getStackPane();

        // Load another FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Node anotherFXMLRoot = loader.load();

            IdolFanMeetsModel idolFanMeetsModel = new IdolFanMeetsModel();
            IdolFanMeetsView idolFanMeetsView = loader.getController();
            IdolFanMeetsController idolFanMeetsController = new IdolFanMeetsController(idolFanMeetsView, idolFanMeetsModel);

            // Add the root node of the loaded FXML to the StackPane
            stackPane.getChildren().add(anotherFXMLRoot);


        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }


}
