package idol.controller;

import idol.model.DeleteFanMeetModel;
import idol.view.DeleteFanMeetView;
import javafx.stage.Stage;
import jdbc.IdolJDBC;

public class DeleteFanMeetController {
    private DeleteFanMeetView view;
    private DeleteFanMeetModel model;

    public DeleteFanMeetController(DeleteFanMeetView view, DeleteFanMeetModel model){
        this.view = view;
        this.model = model;

        this.view.setUpActionYesButton(event -> {
            //todo: delete into the database
            IdolJDBC.deleteFanmeet(model.getFanmeet());
            Stage stage = (Stage) view.getNoButton().getScene().getWindow();
            stage.close();
        });

        view.setUpActionNoButton(event -> {
            Stage stage = (Stage) view.getNoButton().getScene().getWindow();
            stage.close();
        });
    }

    public DeleteFanMeetModel getModel() {
        return model;
    }

    public void setModel(DeleteFanMeetModel model) {
        this.model = model;
    }

    public DeleteFanMeetView getView() {
        return view;
    }

    public void setView(DeleteFanMeetView view) {
        this.view = view;
    }
}
