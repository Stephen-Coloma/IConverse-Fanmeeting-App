package idol.controller;

import idol.model.FinishedFanMeetsViewCardModel;
import idol.model.IdolFanMeetsModel;
import idol.view.FinishedFanMeetsViewCardView;
import idol.view.IdolFanMeetsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import jdbc.IdolJDBC;
import shared.Fanmeet;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

public class IdolFanMeetsController {
    private IdolFanMeetsView view;
    private IdolFanMeetsModel model;
    private FXMLLoader loader;
    private Parent root;

    public IdolFanMeetsController(IdolFanMeetsView view, IdolFanMeetsModel model) {
        this.view = view;
        this.model = model;

        //set up action for buttons
        view.setUpActionCreateFanmeetButton(event -> {
            //todo: @leonard about sa calendar
        });

        view.setUpActionFinishedFanmeetsButton(event -> {
            //todo: populate the scrollpane/flowpane
           try {
               List<Fanmeet> finishedFanmeets = model.loadFinishedFanMeets();
               populateScrollPaneWithFinishedFanmeet(finishedFanmeets);
           }catch (Exception e){
               e.printStackTrace();
           }

        });

        view.setUpActionUpcomingFanmeetsButton(event -> {
            try {
                List<Fanmeet> finishedFanmeets = model.loadUnfinishedFanMeets();

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void populateScrollPaneWithFinishedFanmeet(List<Fanmeet> fanmeetsList){
        ScrollPane scrollPane = view.getScrollPane();
        FlowPane flowPane = (FlowPane) scrollPane.getContent();
        flowPane.getChildren().clear();

        for (Fanmeet fanmeet: fanmeetsList) {
            // Load another FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/FinishedFanMeetsViewCard.fxml"));

            try {
                Node anotherFXMLRoot = loader.load();
                FinishedFanMeetsViewCardModel finishedFanMeetsViewCardModel = new FinishedFanMeetsViewCardModel(fanmeet);
                FinishedFanMeetsViewCardView finishedFanMeetsViewCardView = loader.getController();
                FinishedFanMeetsViewCardController finishedFanMeetsViewCardController = new FinishedFanMeetsViewCardController(finishedFanMeetsViewCardView, finishedFanMeetsViewCardModel);
                finishedFanMeetsViewCardController.setData();
                // Add the root node of the loaded FXML to the FlowPane
                flowPane.getChildren().add(anotherFXMLRoot);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception appropriately
            }
        }
    }
}
