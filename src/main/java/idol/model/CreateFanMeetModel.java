package idol.model;

import jdbc.IdolJDBC;
import shared.Fanmeet;

import java.sql.SQLException;
import java.util.List;

public class CreateFanMeetModel {

    public CreateFanMeetModel() {}

    public List<Fanmeet> getFanMeetList(int idolID) {
        try {
            return IdolJDBC.loadUnFinishedFanmeets(idolID);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    } // end of getFanMeetList

    public void addFanMeet(Fanmeet fanmeet) {
        try {
            IdolJDBC.addFanmeetToDB(fanmeet);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    } // end of addFanMeet
} // end of CreateFanMeetModel class
