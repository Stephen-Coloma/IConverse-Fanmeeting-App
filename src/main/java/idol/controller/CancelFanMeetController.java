package idol.controller;

import idol.model.CancelConfirmationModel;
import idol.view.CancelConfirmationView;
import javafx.stage.Stage;
import jdbc.IdolJDBC;

public class CancelFanMeetController {
    private CancelConfirmationView view;
    private CancelConfirmationModel model;

    public CancelFanMeetController(CancelConfirmationView view, CancelConfirmationModel model){
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

    public CancelConfirmationModel getModel() {
        return model;
    }

    public void setModel(CancelConfirmationModel model) {
        this.model = model;
    }

    public CancelConfirmationView getView() {
        return view;
    }

    public void setView(CancelConfirmationView view) {
        this.view = view;
    }
}
