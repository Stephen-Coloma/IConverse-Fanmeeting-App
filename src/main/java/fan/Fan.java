package fan;

import fan.controller.MainPage;
import fan.model.MainPageModel;
import fan.view.MainPageView;

import java.io.IOException;

public class Fan {

    public Fan() {}

    public void start() {
        try {
            MainPage mainPage = new MainPage(new MainPageModel(), new MainPageView());
            mainPage.init();
        } catch (IOException ioException) {
            ioException.getCause().printStackTrace();
        }
    } // end of start
} // end of Fan class
