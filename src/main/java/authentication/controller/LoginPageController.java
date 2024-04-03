package authentication.controller;

import authentication.model.LoginPageModel;
import authentication.view.LoginPageView;
import org.controlsfx.control.action.Action;
import shared.User;

public class LoginPageController {
    private LoginPageView view;
    private LoginPageModel model;

    public LoginPageController(LoginPageView view, LoginPageModel model) {
        this.view = view;
        this.model = model;

        //setting up the button action for login page
        this.view.setActionSignUpButton(event -> {
            //todo: load the sign up button
        });

        this.view.setActionLoginButton( event ->{
            String username = view.getUsernameTextField().getText();
            String password = view.getPasswordField().getText();


            model.setUsername(username);
            model.setPassword(password);

            try {
                model.login();
                //todo: if it reaches here, load the main menu for which account it is
            }catch (Exception e){
                view.getNoticeLabel().setText(e.getMessage());
                view.getNoticeLabel().setVisible(true);
            }
        });
    }
}
