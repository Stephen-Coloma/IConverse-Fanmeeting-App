package idol.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FeedbackCardView {
    @FXML
    private Label fanUsernameLabel;

    @FXML
    private Label feedbackLabel;

    @FXML
    private ImageView imageView;

    public Label getFanUsernameLabel() {
        return fanUsernameLabel;
    }

    public void setFanUsernameLabel(Label fanUsernameLabel) {
        this.fanUsernameLabel = fanUsernameLabel;
    }

    public Label getFeedbackLabel() {
        return feedbackLabel;
    }

    public void setFeedbackLabel(Label feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
