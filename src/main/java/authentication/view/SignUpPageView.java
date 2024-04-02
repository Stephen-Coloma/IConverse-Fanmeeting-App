package authentication.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpPageView {

    @FXML
    private Button homeButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox termsAndServicesCheckBox;
    public void createAccountButtonEntered(){
        createAccountButton.setStyle("-fx-background-color: #c7a97f;");
    }
    public void createAccountButtonExited(){
        createAccountButton.setStyle("-fx-background-color:  #A38157;");
    }

    public void homePageButtonEntered(){
        homeButton.setStyle("-fx-background-color: #8d5a47;");
    }

    public void homePageButtonExited(){
        homeButton.setStyle("-fx-background-color:  #66382a;");
    }

    public TextField getFullNameTextField() {
        return fullNameTextField;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public TextField getAddressTextField() {
        return addressTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public CheckBox getTermsAndServicesCheckBox() {
        return termsAndServicesCheckBox;
    }
}
