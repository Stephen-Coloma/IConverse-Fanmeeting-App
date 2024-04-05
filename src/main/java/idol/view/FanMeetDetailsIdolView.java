package idol.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FanMeetDetailsIdolView {

    @FXML
    private Label dateLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label pricePerMinuteLabel;

    @FXML
    private Label timeLabel;

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public Label getDurationLabel() {
        return durationLabel;
    }

    public void setDurationLabel(Label durationLabel) {
        this.durationLabel = durationLabel;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Label getPricePerMinuteLabel() {
        return pricePerMinuteLabel;
    }

    public void setPricePerMinuteLabel(Label pricePerMinuteLabel) {
        this.pricePerMinuteLabel = pricePerMinuteLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }
}
