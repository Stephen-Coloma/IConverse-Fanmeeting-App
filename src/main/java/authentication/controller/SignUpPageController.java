package authentication.controller;

import authentication.model.SignUpModel;
import authentication.view.SignUpPageView;

public class SignUpPageController {
    private SignUpPageView view;
    private SignUpModel model;

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
                //todo: if it reaches here, load the login page
            } catch (Exception e) {
                //todo: catch where the account cannot be created due to duplicates from username
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
                //todo: if it reaches here, load the login page
            } catch (Exception e) {
                //todo: catch where the account cannot be created due to duplicates from username
                view.getNoticeLabel().setText(e.getMessage());
                view.getNoticeLabel().setVisible(true);
            }

        });

        //setting up action for create an account
        view.setActionBackButton(event->{
            //todo: load the login page
        });

    }
}
