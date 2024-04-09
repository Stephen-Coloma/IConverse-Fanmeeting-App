package fan.controller.cards;

import idol.model.CancelConfirmationModel;
import idol.view.CancelConfirmationView;
import javafx.stage.Stage;
import jdbc.FanJDBC;

public class CancelBookingController {
    private CancelConfirmationView view;
    public CancelConfirmationModel model;

    public CancelBookingController(CancelConfirmationView view, CancelConfirmationModel model) {
        this.view = view;
        this.model = model;

        view.setUpActionYesButton(event -> {
            //todo: cancel bookings into the database
            FanJDBC.cancelBooking(model.getBooking());
            Stage stage = (Stage) view.getYesButton().getScene().getWindow();
            stage.close();
        });

        view.setUpActionNoButton(event -> {
            Stage stage = (Stage) view.getNoButton().getScene().getWindow();
            stage.close();
        });
    }
}
