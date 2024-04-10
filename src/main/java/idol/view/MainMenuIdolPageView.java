package idol.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainMenuIdolPageView {

    @FXML
    private Button bookingsButton;

    @FXML
    private Button fanmeetsButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Button profileButton;

    @FXML
    private Button settingsButton;

    @FXML
    private StackPane stackPane;

    //set up actions for buttons
    public void setUpActionProfileButton(EventHandler<ActionEvent> event){
        profileButton.setOnAction(event);
    }

    public void setUpActionBookingsButton(EventHandler<ActionEvent> event){
        bookingsButton.setOnAction(event);
    }

    public void setUpActionSettingsButton(EventHandler<ActionEvent> event){
        settingsButton.setOnAction(event);
    }

    public void setUpActionFanmeetsButton(EventHandler<ActionEvent> event){
        fanmeetsButton.setOnAction(event);
    }

    public Button getBookingsButton() {
        return bookingsButton;
    }

    public void setBookingsButton(Button bookingsButton) {
        this.bookingsButton = bookingsButton;
    }

    public Button getFanmeetsButton() {
        return fanmeetsButton;
    }

    public void setFanmeetsButton(Button fanmeetsButton) {
        this.fanmeetsButton = fanmeetsButton;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Button getProfileButton() {
        return profileButton;
    }

    public void setProfileButton(Button profileButton) {
        this.profileButton = profileButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }
}
