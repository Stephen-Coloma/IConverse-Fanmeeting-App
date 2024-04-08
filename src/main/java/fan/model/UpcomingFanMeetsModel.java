package fan.model;

import jdbc.FanJDBC;
import shared.Fanmeet;
import shared.User;

import java.util.List;

public class UpcomingFanMeetsModel {
    private int idolID;

    public UpcomingFanMeetsModel(int idolID) {
        this.idolID = idolID;
    }

    public User getIdol() {
        return FanJDBC.getIdol(idolID);
    } // end of getIdol

    public List<Fanmeet> getFanMeetList() {
        return FanJDBC.getFanMeetList(idolID);
    } // end of getFanMeetList
} // end of UpcomingFanMeetsModel class
