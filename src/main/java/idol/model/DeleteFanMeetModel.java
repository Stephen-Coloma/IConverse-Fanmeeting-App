package idol.model;

import shared.Fanmeet;

public class DeleteFanMeetModel {
    private Fanmeet fanmeet;

    public DeleteFanMeetModel(Fanmeet fanmeet) {
        this.fanmeet = fanmeet;
    }

    public Fanmeet getFanmeet() {
        return fanmeet;
    }

    public void setFanmeet(Fanmeet fanmeet) {
        this.fanmeet = fanmeet;
    }
}
