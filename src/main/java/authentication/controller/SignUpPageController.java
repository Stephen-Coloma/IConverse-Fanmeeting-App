package authentication.controller;

import authentication.model.LoginPageModel;
import authentication.model.SignUpModel;
import authentication.view.SignUpPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class SignUpPageController {
    private SignUpPageView view;
    private SignUpModel model;

    private FXMLLoader loader;
    private Parent root;

    public SignUpPageController(SignUpPageView view, SignUpModel model) {
        this.view = view;
        this.model = model;

        view.getChooseImageButton().setOnAction(event -> {
            try {
                // Create a Button to open the file chooser
                Stage stage = new Stage();
                // Show file chooser dialog
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Image File");
                File selectedFile = fileChooser.showOpenDialog(stage);

                // Load and display the selected image
                if (selectedFile != null) {
                    Image image = new Image(selectedFile.toURI().toString());
                    byte[] imageData = getImageData(selectedFile);
                    model.setProfilePicture(imageData);

                    //setting the label
                    String imageName = selectedFile.getName();
                    view.getImageNameLabel().setText("image: " + imageName);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //setting up action for create an account
        view.setActionCreateFanAccountButton(event->{
            //get the details from the UI
            String name = view.getFullNameTextField().getText();
            String username = view.getUserNameTextField().getText();
            String email = view.getEmailTextField().getText();
            String password = view.getPasswordField().getText();
            String bio = view.getBioTextField().getText();

            model.setName(name);
            model.setUsername(username);
            model.setEmail(email);
            model.setPassword(password);
            model.setUserType("Fan");
            model.setBio(bio);

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
            String bio = view.getBioTextField().getText();

            model.setName(name);
            model.setUsername(username);
            model.setEmail(email);
            model.setPassword(password);
            model.setUserType("Idol");
            model.setBio(bio);

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


    private byte[] getImageData(File file) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (FileInputStream fis = new FileInputStream(file)) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        return output.toByteArray();
    }

}
