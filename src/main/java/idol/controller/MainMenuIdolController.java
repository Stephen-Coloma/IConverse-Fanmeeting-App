package idol.controller;

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
            //todo: optional
        });

        this.view.setUpActionProfileButton(event -> {
            //todo: optional
        });

        this.view.setUpActionSettingsButton(event -> {
            //todo: optional
        });
    }

    public void loadContent(String fxmlPath){
        StackPane stackPane = view.getStackPane();

        // Load another FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Node anotherFXMLRoot = loader.load();
            // Add the root node of the loaded FXML to the StackPane
            stackPane.getChildren().add(anotherFXMLRoot);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }


}
