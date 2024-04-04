package idol.model;

import idol.controller.FinishedFanMeetsViewCardController;
import shared.Fanmeet;

import javax.xml.transform.sax.SAXResult;

public class FinishedFanMeetsViewCardModel {

    private Fanmeet fanmeet;
    public FinishedFanMeetsViewCardModel(Fanmeet fanmeet){
        this.fanmeet = fanmeet;
    }

    public Fanmeet getFanmeet() {
        return fanmeet;
    }

    public void setFanmeet(Fanmeet fanmeet) {
        this.fanmeet = fanmeet;
    }
}
