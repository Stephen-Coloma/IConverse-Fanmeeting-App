package fan.model;

import jdbc.FanJDBC;
import shared.Booking;

import java.util.List;

public class BookingsModel {

    public BookingsModel() {}

    public List<Booking> getBookingList(int userID) {
        return FanJDBC.getBookingList(userID);
    }
} // end of BookingsModel class
