package idol.controller;

import idol.model.FinishedFanMeetsViewCardModel;
import idol.view.FinishedFanMeetsViewCardView;

public class FinishedFanMeetsViewCardController {
    private FinishedFanMeetsViewCardView view;
    private FinishedFanMeetsViewCardModel model;

    public FinishedFanMeetsViewCardController(FinishedFanMeetsViewCardView view, FinishedFanMeetsViewCardModel model) {
        this.view = view;
        this.model = model;

        //set up action for button
        this.view.setUpActionViewDetailsButton(event -> {
            //todo: pop ups for view details
        });

        this.view.setUpActionViewFeedbacksButton(event -> {
            //todo: pop ups for feedbacks
        });
    }

    public void setData() {
        view.getDateLabel().setText(model.getFanmeet().getDate().toString());
        view.getTimeLabel().setText(model.getFanmeet().getStartTime() + " - " + model.getFanmeet().getEndTime());
    }
}
