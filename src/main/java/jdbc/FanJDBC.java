package jdbc;

import fan.controller.Bookings;
import shared.Booking;
import shared.Fanmeet;
import shared.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**This class will be used to create methods that accesses the database solely intended for Idols*/
public class FanJDBC {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/leonardos"; // editable
    private static final String USER = "LeonardosAdmin"; // editable
    private static final String PASSWORD = "password"; // editable
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static List<User> getIdolList() {
        List<User> idolList = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE UserType LIKE 'Idol'";

        try (
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String name = resultSet.getString("Name");
                String password = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                String userType = resultSet.getString("UserType");
                String status = resultSet.getString("Status");
                String bio = resultSet.getString("Bio");
                byte[] profilePicture = resultSet.getBytes("ProfilePicture");

                User user = new User(userID, username, name, password, email, userType, status, profilePicture, bio);
                idolList.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.getCause().printStackTrace();
        }
        return idolList;
    } // end of getIdolList

    public static User getIdol(int userID) {
        // SQL query
        String sql = "SELECT * FROM users WHERE UserID = ? AND UserType LIKE 'Idol'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userID);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {

                    int id = resultSet.getInt("UserID");
                    String username = resultSet.getString("Username");
                    String name = resultSet.getString("Name");
                    String password = resultSet.getString("Password");
                    String email = resultSet.getString("Email");
                    String userType = resultSet.getString("UserType");
                    String status = resultSet.getString("Status");
                    String bio = resultSet.getString("Bio");
                    byte[] profilePicture = resultSet.getBytes("ProfilePicture");

                    return new User(id, username, name, password, email, userType, status, profilePicture, bio);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    } // end of getIdol

    public static List<Booking> getBookingList(int userID) {
        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT b.BookingID, b.UserID, b.FanMeetID, b.TimeStamp, b.StartTime, b.Duration, b.Price, " +
                "f.FanMeetID, f.IdolName, f.Date, f.Status, " +
                "u.UserID, u.Name " +
                "FROM bookings b JOIN fanmeets f ON b.FanMeetID = f.FanMeetID " +
                "JOIN users u ON f.IdolName = u.UserID " +
                "WHERE b.UserID = ? " +
                "ORDER BY f.Date";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userID);

            try (ResultSet resultSet = pstmt.executeQuery()) {

                while (resultSet.next()) {
                    // idol user
                    String name = resultSet.getString("Name");

                    User idol = new User(userID, null, name, null, null, null, null, null, null);

                    // fanmeet
                    int fanMeetID = resultSet.getInt("FanMeetID");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    String status = resultSet.getString("Status");

                    Fanmeet fanmeet = new Fanmeet(fanMeetID, idol, date, null, null, 0.0, status);

                    // booking
                    int bookingID = resultSet.getInt("BookingID");
                    LocalDateTime timeStamp = resultSet.getTimestamp("TimeStamp").toLocalDateTime();
                    LocalTime startTime2 = resultSet.getTime("StartTime").toLocalTime();
                    int duration = resultSet.getInt("Duration");
                    double price = resultSet.getDouble("Price");

                    Booking booking = new Booking(bookingID, idol, fanmeet, timeStamp, startTime2, duration, price);
                    bookings.add(booking);
                }
                return bookings;
            } catch (SQLException sqlException) {
                sqlException.getCause().printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.getCause().printStackTrace();
        }
        return null;
    } // end of getBookingList

    public static List<Fanmeet> getFanMeetList(int idolID) {

        List<Fanmeet> fanmeetList = new ArrayList<>();

        String sql = "SELECT f.FanMeetID, f.IdolName, f.Date, f.StartTime, f.EndTime, f.PricePerMinute, f.Status, " +
                "u.UserID, u.Name, u.Bio, u.ProfilePicture " +
                "FROM fanmeets f JOIN users u ON f.IdolName = u.UserID " +
                "WHERE f.IdolName = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,idolID);

            try(ResultSet resultSet = stmt.executeQuery()){

                while (resultSet.next()) {

                    String name = resultSet.getString("Name");
                    String bio = resultSet.getString("Bio");
                    byte[] profilePicture = resultSet.getBytes("ProfilePicture");

                    User idol = new User(idolID, null,  name, null, null, null, null, profilePicture, bio);

                    int fanMeetID = resultSet.getInt("FanMeetID");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    LocalTime startTime = resultSet.getTime("StartTime").toLocalTime();
                    LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();
                    double pricePerMinute = resultSet.getDouble("PricePerMinute");
                    String status = resultSet.getString("Status");

                    Fanmeet fanmeet = new Fanmeet(fanMeetID,idol,date,startTime,endTime,pricePerMinute,status);
                    fanmeetList.add(fanmeet);
                }
                return fanmeetList;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    } // end of getFanMeetList

    public static void addBookingToDB(Booking booking) {
        // TODO: add the booking of the user to the database.

        String sql = "INSERT INTO bookings (userID, fanMeetID, timeStamp, startTime, duration, price) " +
        "VALUES ('?', '?', '?', '?', '?', '?') ";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1 , booking.getUserID().getUserID());
            stmt.setInt(2, booking.getFanMeetID().getFanMeetID());
            stmt.setObject(3, booking.getTimeStamp());
            stmt.setTime(4, new Time(booking.getStartTime().getHour(), booking.getStartTime().getMinute(), booking.getStartTime().getSecond()));
            stmt.setInt(5, booking.getDuration());
            stmt.setDouble(6, booking.getDuration());
            if(stmt.executeUpdate() < 0) {
                System.out.println("Update Failed");
            } else {
                System.out.println("Update Successful");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    } // end of addBookingToDB
} // end of FanJDBC class
