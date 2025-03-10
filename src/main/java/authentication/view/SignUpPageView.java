package authentication.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignUpPageView {

    @FXML
    private Button backButton;

    @FXML
    private Button createFanAccount;

    @FXML
    private Button createIdolAccount;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private Label noticeLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameTextField;
    @FXML
    private Button chooseImageButton;
    @FXML
    private Label imageNameLabel;

    @FXML
    private TextField bioTextField;

    public void setActionCreateFanAccountButton(EventHandler<ActionEvent> event){
        this.createFanAccount.setOnAction(event);
    }

    public void setActionCreateIdolAccountButton(EventHandler<ActionEvent> event){
        this.createIdolAccount.setOnAction(event);
    }

    public void setActionBackButton(EventHandler<ActionEvent> event){
        this.backButton.setOnAction(event);
    }

    @FXML
    void createAccountButtonEntered(MouseEvent event) {

    }

    @FXML
    void createAccountButtonExited(MouseEvent event) {

    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public Button getCreateFanAccount() {
        return createFanAccount;
    }

    public void setCreateFanAccount(Button createFanAccount) {
        this.createFanAccount = createFanAccount;
    }

    public Button getCreateIdolAccount() {
        return createIdolAccount;
    }

    public void setCreateIdolAccount(Button createIdolAccount) {
        this.createIdolAccount = createIdolAccount;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public TextField getFullNameTextField() {
        return fullNameTextField;
    }

    public void setFullNameTextField(TextField fullNameTextField) {
        this.fullNameTextField = fullNameTextField;
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

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public void setUserNameTextField(TextField userNameTextField) {
        this.userNameTextField = userNameTextField;
    }

    public Button getChooseImageButton() {
        return chooseImageButton;
    }

    public void setChooseImageButton(Button chooseImageButton) {
        this.chooseImageButton = chooseImageButton;
    }

    public Label getImageNameLabel() {
        return imageNameLabel;
    }

    public void setImageNameLabel(Label imageNameLabel) {
        this.imageNameLabel = imageNameLabel;
    }

    public TextField getBioTextField() {
        return bioTextField;
    }

    public void setBioTextField(TextField bioTextField) {
        this.bioTextField = bioTextField;
    }
}
