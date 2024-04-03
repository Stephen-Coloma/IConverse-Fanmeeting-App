package authentication.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginPageView {

    @FXML
    public TextField usernameTextField;
    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label noticeLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    void homePageButtonEntered(MouseEvent event) {

    }

    @FXML
    void homePageButtonExited(MouseEvent event) {

    }

    @FXML
    void loginPageButtonEntered(MouseEvent event) {

    }

    @FXML
    void loginPageButtonExited(MouseEvent event) {

    }


    /**This method implements action for login button*/
    public void setActionLoginButton(EventHandler<ActionEvent> event){
        this.loginButton.setOnAction(event);
    }

    /**This method implements action for sign up button*/
    public void setActionSignUpButton(EventHandler<ActionEvent> event){
        this.signUpButton.setOnAction(event);
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Label getNoticeLabel() {
        return noticeLabel;
    }

    public void setNoticeLabel(Label noticeLabel) {
        this.noticeLabel = noticeLabel;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Button getSignupButton() {
        return signUpButton;
    }

    public void setSignupButton(Button signupButton) {
        this.signUpButton = signupButton;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(TextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }
}
