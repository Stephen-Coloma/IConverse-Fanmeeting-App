package fan.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FanLandingPageView {

    // Main layout components
    @FXML
    private ImageView mainProfileImage;
    @FXML
    private Button profileButton, bookingsButton, settingsButton, fanmeetsButton;
    @FXML
    private TextField searchTextField;

    // Idol profile components (assuming these are parts of the UI that get updated)
    @FXML
    private ImageView idolProfileImage;
    @FXML
    private Text idolNameText;

    // Initialize method
    @FXML
    public void initialize() {
        // You can initialize stuff here, e.g., set the default selected section
        showUserProfile(); // Assuming this method shows the user's profile by default
    }

    // Event handlers
    @FXML
    private void handleProfileButtonAction() {
        showUserProfile();
    }

    @FXML
    private void handleBookingsButtonAction() {
        showUserBookings();
    }

    @FXML
    private void handleSettingsButtonAction() {
        showUserSettings();
    }

    @FXML
    private void handleFanmeetsButtonAction() {
        showUserFanmeets();
    }

    @FXML
    private void handleSearchAction() {
        String searchTerm = searchTextField.getText().trim();
        if (!searchTerm.isEmpty()) {
            searchIdol(searchTerm);
        }
    }

    // Methods for handling navigation and search
    private void showUserProfile() {
        // Logic to display the user's profile
        idolNameText.setText("User Profile");
        // Update idolProfileImage accordingly
    }

    private void showUserBookings() {
        // Logic to display the user's bookings
        idolNameText.setText("User Bookings");
        // Update content accordingly
    }

    private void showUserSettings() {
        // Logic to display user settings
        idolNameText.setText("User Settings");
        // Update content accordingly
    }

    private void showUserFanmeets() {
        // Logic to display user fanmeets
        idolNameText.setText("User Fanmeets");
        // Update content accordingly
    }

    private void searchIdol(String name) {
        // Logic to perform search and display results
        idolNameText.setText("Search Results for: " + name);
        // Assume you update the view to show search results
    }

}
