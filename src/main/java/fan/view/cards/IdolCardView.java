package fan.view.cards;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class IdolCardView {
    @FXML
    private Pane pane;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Text idolName;

    @FXML
    public void initialize() {
        setUpDropShadow();
    }

    private void setUpDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setOffsetY(5);
        pane.setEffect(dropShadow);
    } // end of setUpDropShadow

    public ImageView getProfilePicture() {
        return profilePicture;
    }

    public Text getIdolName() {
        return idolName;
    }
} // end of IdolCard class
