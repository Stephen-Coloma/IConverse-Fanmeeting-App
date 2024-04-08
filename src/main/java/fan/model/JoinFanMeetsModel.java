package fan.model;

import jdbc.FanJDBC;
import shared.Booking;
import shared.Fanmeet;

import java.util.List;

public class JoinFanMeetsModel {

    public JoinFanMeetsModel() {}

    public List<Booking> getBookedFanMeetList(int userID) {
        return FanJDBC.getOngoingFanMeets(userID);
    } // end of getBookedFanMeetList
} // end of JoinFanMeetsModel class
