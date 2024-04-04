package idol;

import authentication.controller.LoginPageController;
import authentication.model.LoginPageModel;
import idol.controller.MainMenuIdolController;
import idol.view.MainMenuIdolPageView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Idol extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/MainMenuIdolPage.fxml"));
        Parent root = loader.load();

        MainMenuIdolController mainMenuIdolController = new MainMenuIdolController(loader.getController());
        stage.setTitle("IConverse");
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }
}
