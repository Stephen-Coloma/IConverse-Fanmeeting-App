package authentication.controller;

import authentication.model.LoginPageModel;
import authentication.model.SignUpModel;
import authentication.view.SignUpPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.util.Objects;

public class SignUpPageController {
    private SignUpPageView view;
    private SignUpModel model;

    private FXMLLoader loader;
    private Parent root;

    public SignUpPageController(SignUpPageView view, SignUpModel model) {
        this.view = view;
        this.model = model;

        //setting up action for create an account
        view.setActionCreateFanAccountButton(event->{
            //get the details from the UI
            String name = view.getFullNameTextField().getText();
            String username = view.getUserNameTextField().getText();
            String email = view.getEmailTextField().getText();
            String password = view.getPasswordField().getText();

            model.setName(name);
            model.setUsername(username);
            model.setEmail(email);
            model.setPassword(password);
            model.setUserType("Fan");

            try {
                model.signUp();
                loadLoginPage(event);
            } catch (Exception e) {
                view.getNoticeLabel().setText(e.getMessage());
                view.getNoticeLabel().setVisible(true);
            }

        });

        //setting up action for create an account
        view.setActionCreateIdolAccountButton(event->{
            //get the details from the UI
            String name = view.getFullNameTextField().getText();
            String username = view.getUserNameTextField().getText();
            String email = view.getEmailTextField().getText();
            String password = view.getPasswordField().getText();

            model.setName(name);
            model.setUsername(username);
            model.setEmail(email);
            model.setPassword(password);
            model.setUserType("Idol");

            try {
                model.signUp();
                loadLoginPage(event);
            } catch (Exception e) {
                view.getNoticeLabel().setText(e.getMessage());
                view.getNoticeLabel().setVisible(true);
            }

        });

        //setting up action for create an account
        view.setActionBackButton(event->{
            loadLoginPage(event);
        });

    }

    public void loadLoginPage(ActionEvent event){
        try {
            loader = new FXMLLoader(getClass().getResource("/fxmls/authentication/LoginPage.fxml"));
            root = loader.load();
            root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/landing-page.css")).toExternalForm());

            new LoginPageController(loader.getController(), new LoginPageModel());

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
