package idol;

import authentication.IConverse;
import authentication.controller.LoginPageController;
import authentication.model.LoginPageModel;
import fan.controller.MainPage;
import fan.model.MainPageModel;
import fan.view.MainPageView;
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
import java.io.IOException;
import java.io.PushbackReader;
import java.nio.file.Files;

public class Idol {

    public static int USER_ID;
    public static byte[] PROFILE_PICTURE;

    public Idol() {}

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/idol/MainMenuIdolPage.fxml"));
            Parent root = loader.load();
            MainMenuIdolModel model = new MainMenuIdolModel(new User(USER_ID, PROFILE_PICTURE)); //todo: LOGIN TO IDOL PAGE change to userID to the account being logged in change to whole user object
            //
            MainMenuIdolController mainMenuIdolController = new MainMenuIdolController(loader.getController(), model);
            IConverse.STAGE.setTitle("IConverse");
            Scene scene = new Scene(root);
            IConverse.STAGE.setResizable(false);
            IConverse.STAGE.setFullScreen(false);
            IConverse.STAGE.setScene(scene);
            IConverse.STAGE.show();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }
    } // end of start
    public void start(Stage stage) throws Exception {
        //todo: TEMPORARY IMAGE AND USERID
        File file = new File("src/main/resources/idol/images/profile-user.png");
        PROFILE_PICTURE = Files.readAllBytes(file.toPath());
        USER_ID = 2;
    }
}
