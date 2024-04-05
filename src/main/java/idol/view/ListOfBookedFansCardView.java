package idol.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ListOfBookedFansCardView {

    @FXML
    private Label fanEmailLabel;

    @FXML
    private Label fanNameLabel;

    @FXML
    private Label fanUsernameLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label timestampLabel;

    public Label getFanEmailLabel() {
        return fanEmailLabel;
    }

    public void setFanEmailLabel(Label fanEmailLabel) {
        this.fanEmailLabel = fanEmailLabel;
    }

    public Label getFanNameLabel() {
        return fanNameLabel;
    }

    public void setFanNameLabel(Label fanNameLabel) {
        this.fanNameLabel = fanNameLabel;
    }

    public Label getFanUsernameLabel() {
        return fanUsernameLabel;
    }

    public void setFanUsernameLabel(Label fanUsernameLabel) {
        this.fanUsernameLabel = fanUsernameLabel;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Label getTimestampLabel() {
        return timestampLabel;
    }

    public void setTimestampLabel(Label timestampLabel) {
        this.timestampLabel = timestampLabel;
    }
}
