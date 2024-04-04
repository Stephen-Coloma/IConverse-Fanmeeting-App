package idol.model;

import jdbc.IdolJDBC;
import shared.Fanmeet;

import java.util.List;

public class IdolFanMeetsModel {
    private List<Fanmeet> finishedFanMeets;
    private List<Fanmeet> upcomingFanMeets;

    public List<Fanmeet> loadFinishedFanMeets() throws Exception{
        finishedFanMeets = IdolJDBC.loadFinishedFanmeets(1);
        if (finishedFanMeets != null){
            return loadUnfinishedFanMeets();
        }else {
            throw new Exception("An error occurred");
        }
    }

    public List<Fanmeet> loadUnfinishedFanMeets() throws Exception{
        upcomingFanMeets = IdolJDBC.loadFinishedFanmeets(1);
        if (upcomingFanMeets != null){
            return loadUnfinishedFanMeets();
        }else {
            throw new Exception("An error occurred");
        }
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
