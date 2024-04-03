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
    public static Stage stage;
    public FXMLLoader loader;
    public Parent root;
    private LoginPageController loginPageController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            loader = new FXMLLoader(getClass().getResource("/fxmls/LoginPage.fxml"));
            root = loader.getRoot();

            loginPageController  = new LoginPageController(loader.getController(), new LoginPageModel());
            Scene scene = new Scene(loader.load());
            stage.setTitle("IConverse");
            this.stage.setResizable(false);
            this.stage.setFullScreen(false);
            this.stage.setScene(scene);
            this.stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
