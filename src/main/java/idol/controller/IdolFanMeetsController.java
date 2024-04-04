package idol.controller;

import idol.model.IdolFanMeetsModel;
import idol.view.IdolFanMeetsView;

public class IdolFanMeetsController {
    private IdolFanMeetsView view;
    private IdolFanMeetsModel model;

    public IdolFanMeetsController(IdolFanMeetsView view, IdolFanMeetsModel model){
        this.view = view;
        this.model = model;

        //set up action for buttons
        view.setUpActionCreateFanmeetButton(event -> {
            //todo: @leonard about sa calendar
        });

        view.setUpActionFinishedFanmeetsButton(event -> {
            //todo: populate the scrollpane/flowpane
        });

        view.setUpActionUpcomingFanmeetsButton(event -> {
            //todo: populate scrollpane/flowpane
        });
    }
}
