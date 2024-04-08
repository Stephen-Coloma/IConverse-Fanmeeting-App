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
                    // fan user
                    User user = new User(userID, null, null, null, null, null, null, null, null);

                    // idol user
                    int idolID = resultSet.getInt("IdolName");
                    String name = resultSet.getString("Name");
                    User idol = new User(idolID, null, name, null, null, null, null, null, null);

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

                    Booking booking = new Booking(bookingID, user, fanmeet, timeStamp, startTime2, duration, price);
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
        String sql = "INSERT INTO bookings (userID, fanMeetID, timeStamp, startTime, duration, price) " +
                "VALUES (?, ?, ?, ?, ?, ?) ";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1 , booking.getUserID().getUserID());
            stmt.setInt(2, booking.getFanMeetID().getFanMeetID());
            stmt.setObject(3, booking.getTimeStamp());
            stmt.setTime(4, new Time(booking.getStartTime().getHour(), booking.getStartTime().getMinute(), booking.getStartTime().getSecond()));
            stmt.setInt(5, booking.getDuration());
            stmt.setDouble(6, booking.getDuration());
            if(stmt.executeUpdate() > 0) {
                System.out.println("Booking creation successful");
            } else {
                System.out.println("Booking creation failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // end of addBookingToDB

    public static List<Booking> getOngoingFanMeets(int userID) {
        List<Booking> bookingList = new ArrayList<>();

        String sql = "SELECT b.BookingID, b.UserID, b.FanMeetID, b.StartTime, b.Duration, " +
                "f.FanMeetID, f.IdolName, f.Date, f.Status, " +
                "u.UserID, u.Name " +
                "FROM bookings b JOIN fanmeets f ON b.FanMeetID = f.FanMeetID " +
                "JOIN users u ON f.IdolName = u.UserID " +
                "WHERE b.UserID = ? AND f.Status LIKE 'Unfinished'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userID);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    // user object for the fan
                    User user = new User(userID, null, null, null, null, null, null, null, null);

                    // user object for the idol
                    int idolID = resultSet.getInt("IdolName");
                    String idolName = resultSet.getString("Name");
                    User idol = new User(idolID, null, idolName, null, null, null, null, null, null);

                    // fanmeet object
                    int fanMeetID = resultSet.getInt("FanMeetID");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    Fanmeet fanmeet = new Fanmeet(fanMeetID, idol, date, null, null, 0, null);

                    // booking object
                    int bookingID = resultSet.getInt("BookingID");
                    LocalTime startTime = resultSet.getTime("StartTime").toLocalTime();
                    int duration = resultSet.getInt("Duration");
                    Booking booking = new Booking(bookingID, user, fanmeet, null, startTime, duration, 0);

                    bookingList.add(booking);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getCause().printStackTrace();
        }
        return bookingList;
    } // end of getOngoingFanMeets
} // end of FanJDBC class
