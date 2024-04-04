package idol.model;

import shared.Fanmeet;

import java.util.List;

public class IdolFanMeetsModel {
    private List<Fanmeet> finishedFanMeets;
    private List<Fanmeet> upcomingFanMeets;

    public List<Fanmeet> loadFinishedFanMeets(){
        //todo: marius
        return null;
    }

    public List<Fanmeet> loadUnfinishedFanMeets(){
        //todo: marius
        return null;
    }


    public List<Fanmeet> getFinishedFanMeets() {
        return finishedFanMeets;
    }

    public void setFinishedFanMeets(List<Fanmeet> finishedFanMeets) {
        this.finishedFanMeets = finishedFanMeets;
    }

    public List<Fanmeet> getUpcomingFanMeets() {
        return upcomingFanMeets;
    }

    public void setUpcomingFanMeets(List<Fanmeet> upcomingFanMeets) {
        this.upcomingFanMeets = upcomingFanMeets;
    }
}
