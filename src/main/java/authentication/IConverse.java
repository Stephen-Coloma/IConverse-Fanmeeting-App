package authentication;

import authentication.controller.LoginPageController;
import authentication.model.LoginPageModel;
import authentication.view.LoginPageView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IConverse extends Application {
    public static Stage STAGE;
    public FXMLLoader loader;
    public Parent root;
    private LoginPageController loginPageController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            STAGE = stage;

            loader = new FXMLLoader(getClass().getResource("/fxmls/authentication/LoginPage.fxml"));
            root = loader.load();

            loginPageController  = new LoginPageController(loader.getController(), new LoginPageModel());
            STAGE.setTitle("IConverse");
            Scene scene = new Scene(root);
            STAGE.setResizable(false);
            STAGE.setFullScreen(false);
            STAGE.setScene(scene);
            STAGE.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
