package idol;

import authentication.controller.LoginPageController;
import authentication.model.LoginPageModel;
import idol.controller.MainMenuIdolController;
import idol.model.MainMenuIdolModel;
import idol.view.MainMenuIdolPageView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import shared.User;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PushbackReader;
import java.nio.file.Files;

public class Idol extends Application {

    public static int USERID;
    public static byte[] PROFILEPICTURE;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //todo: TEMPORARY IMAGE AND USERID
        File file = new File("src/main/resources/idol/images/profile-user.png");
        PROFILEPICTURE = Files.readAllBytes(file.toPath());
        USERID = 2;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/MainMenuIdolPage.fxml"));
        Parent root = loader.load();
        MainMenuIdolModel model = new MainMenuIdolModel(new User(USERID, PROFILEPICTURE)); //todo: LOGIN TO IDOL PAGE change to userID to the account being logged in change to whole user object
        //
        MainMenuIdolController mainMenuIdolController = new MainMenuIdolController(loader.getController(), model);
        stage.setTitle("IConverse");
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.show();
    }
}
