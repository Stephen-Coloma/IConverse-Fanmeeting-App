package fan.controller.popup;

import fan.model.CancelBookingModel;
import fan.view.popup.CancelBookingView;
import idol.model.CancelConfirmationModel;
import idol.view.CancelConfirmationView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdbc.FanJDBC;

import java.io.IOException;

public class CancelBooking {
    private CancelBookingModel model;
    private CancelBookingView view;
    private Stage popupStage;

    public CancelBooking(CancelBookingModel model, CancelBookingView view) {
        this.view = view;
        this.model = model;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/fan/CancelBooking.fxml"));

        Scene cancelBookingScene = new Scene(loader.load());

        view = loader.getController();

        setUpConfirmBT();

        popupStage = new Stage();
        popupStage.setScene(cancelBookingScene);
        popupStage.setFullScreen(false);
        popupStage.setResizable(false);
        popupStage.setTitle("Cancel Booking");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.show();
    } // end of init

    private void setUpConfirmBT() {
        view.getConfirmBT().setOnAction(event -> {
            // TODO: cancel the booking in the database
            model.cancelBooking();
            popupStage.close();
        });
    } // end of setUpConfirmBT

} // end of CancelBooking class
