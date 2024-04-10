package fan.controller.cards;

import fan.view.cards.IdolCardView;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;
import shared.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class IdolCard {

    public static Node createIdolCard(User user) {
        FXMLLoader loader = new FXMLLoader(IdolCard.class.getResource("/fxmls/fan/IdolCards.fxml"));

        Node idolCard = null;
        try {
        idolCard = loader.load();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }

        IdolCardView view = loader.getController();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(user.getProfilePicture());
        view.getProfilePicture().setImage(new Image(inputStream));
        view.getIdolName().setText(user.getName());

        setUpScaleTransition(idolCard);

        return idolCard;
    } // end of createIdolCard

    private static void setUpScaleTransition(Node node) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), node);
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), node);

        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleOut.setFromX(1.05);
        scaleOut.setFromY(1.05);

        scaleIn.setToX(1.05);
        scaleIn.setToY(1.05);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        node.setOnMouseEntered(event -> {
            scaleOut.stop();
            scaleIn.play();
        });

        node.setOnMouseExited(event -> {
            scaleIn.stop();
            scaleOut.play();
        });
    }
} // end of IdolCard
