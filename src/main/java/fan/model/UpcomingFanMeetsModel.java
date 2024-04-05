package fan.model;

import jdbc.FanJDBC;
import shared.Fanmeet;
import shared.User;

import java.util.List;

public class UpcomingFanMeetsModel {
    private int userID;

    public UpcomingFanMeetsModel(int userID) {
        this.userID = userID;
    }

    public User getIdol() {
        return FanJDBC.getIdol(userID);
    } // end of getIdol

    public List<Fanmeet> getFanMeetList() {
        return FanJDBC.getFanMeetList(userID);
    } // end of getFanMeetList
} // end of UpcomingFanMeetsModel class
