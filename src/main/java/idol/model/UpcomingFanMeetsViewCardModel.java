package idol.model;

import shared.Fanmeet;

public class UpcomingFanMeetsViewCardModel {
    private Fanmeet fanmeet;
    public UpcomingFanMeetsViewCardModel(Fanmeet fanmeet){
        this.fanmeet = fanmeet;
    }

    public Fanmeet getFanmeet() {
        return fanmeet;
    }

    public void setFanmeet(Fanmeet fanmeet) {
        this.fanmeet = fanmeet;
    }
}
