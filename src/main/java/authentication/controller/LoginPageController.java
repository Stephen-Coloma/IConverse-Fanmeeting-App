package authentication.controller;

import authentication.IConverse;
import authentication.model.LoginPageModel;
import authentication.model.SignUpModel;
import authentication.view.LoginPageView;
import fan.Fan;
import idol.Idol;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginPageController {
    private LoginPageView view;
    private LoginPageModel model;
    private FXMLLoader loader;
    private Parent root;

    public LoginPageController(LoginPageView view, LoginPageModel model) {
        this.view = view;
        this.model = model;

        //setting up the button action for login page
        this.view.setActionSignUpButton(event -> {
            //load the view before getting its controller
            try {
                loader = new FXMLLoader(getClass().getResource("/fxmls/authentication/SignupPage.fxml"));
                root = loader.load();

                new SignUpPageController(loader.getController(), new SignUpModel());

                Scene scene = new Scene(root);

                IConverse.STAGE.setScene(scene);
            }catch (IOException e){
                e.printStackTrace();
            }
        });

        this.view.setActionLoginButton(event ->{
            String username = view.getUsernameTextField().getText();
            String password = view.getPasswordField().getText();

            try {
                if (username.isEmpty() || password.isEmpty()) { throw new Exception("Please fill the details required"); }

                model.setUsername(username);
                model.setPassword(password);

                boolean isFan = model.login();

                if (isFan) {
                    Fan fan = new Fan();
                    fan.start();
                } else {
                    Idol idol = new Idol();
                    idol.start();
                }
            }catch (Exception e){
                view.getNoticeLabel().setText(e.getMessage());
                view.getNoticeLabel().setVisible(true);
            }
        });
    }
}
