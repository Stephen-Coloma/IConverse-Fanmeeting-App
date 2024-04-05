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
    private static Connection connection;

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
                ResultSet resultSet = stmt.executeQuery(sql);
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
        // TODO: retrieve the idol object using the userID


        return null;
    } // end of getIdol

    public static List<Booking> getBookingList(int userID) {
        List<Booking> bookings = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT b.BookingID, b.UserID, b.FanMeetID, b.TimeStamp, b.StartTime, b.Duration, b.Price, ");
        sql.append("f.FanMeetID, f.IdolName, f.Date, f.StartTime, f.EndTime, f.PricePerMinute, f.Status, ");
        sql.append("u.UserID, u.Username, u.Name, u.Password, u.Bio, u.Email, u.UserType, u.Status, u.ProfilePicture ");
        sql.append("FROM bookings b JOIN fanmeets f ON b.FanMeetID = f.FanMeetID ");
        sql.append("JOIN users u ON f.IdolName = u.UserID ");
        sql.append("WHERE b.UserID = ?");
        // TODO: add a sort statement based on the b.Timestamp or maybe the f.Date

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            pstmt.setInt(1, userID);

            try (ResultSet resultSet = pstmt.executeQuery()) {

                while (resultSet.next()) {
                    // idol user
                    String username = resultSet.getString("Username");
                    String name = resultSet.getString("Name");
                    String password = resultSet.getString("Password");
                    String email = resultSet.getString("Email");
                    String userType = resultSet.getString("UserType");
                    String status = resultSet.getString("Status");
                    String bio = resultSet.getString("Bio");
                    byte[] profilePicture = resultSet.getBytes("ProfilePicture");

                    User idol = new User(userID, username, name, password, email, userType, status, profilePicture, bio);

                    // fanmeet
                    int fanMeetID = resultSet.getInt("FanMeetID");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    LocalTime startTime1 = resultSet.getTime("StartTime").toLocalTime();
                    LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();
                    double pricePerMinue = resultSet.getDouble("PricePerMinute");

                    Fanmeet fanmeet = new Fanmeet(fanMeetID, idol, date, startTime1, endTime, pricePerMinue, status);

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


    // TODO: obtain the fanmeet list of idol using the idolID
    public static List<Fanmeet> getFanMeetList(int idolID) {

        List<Fanmeet> fanmeetList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

         sql.append("f.FanMeetID, f.IdolName, f.Date, f.StartTime, f.EndTime, f.PricePerMinute, f.Status, ");
         sql.append("u.UserID, u.Username, u.Name, u.Password, u.Bio, u.Email, u.UserType, u.Status, u.ProfilePicture ");
         sql.append("FROM fanmeets f ");
         sql.append("JOIN users u ON f.idolName = u.UserID ");
         sql.append("WHERE f.idolName = ?");


        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            stmt.setInt(1,idolID);

            try(ResultSet resultSet = stmt.executeQuery(sql.toString())){

                while (resultSet.next()) {

                    String username = resultSet.getString("Username");
                    String name = resultSet.getString("Name");
                    String password = resultSet.getString("Password");
                    String email = resultSet.getString("Email");
                    String userType = resultSet.getString("UserType");
                    String status = resultSet.getString("Status");
                    String bio = resultSet.getString("Bio");
                    byte[] profilePicture = resultSet.getBytes("ProfilePicture");

                    User idol = new User(idolID, username, name, password, email, userType, status, profilePicture, bio);

                    int fanMeetID = resultSet.getInt("FanMeetID");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    LocalTime startTime = resultSet.getTime("StartTime").toLocalTime();
                    LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();
                    double pricePerMinute = resultSet.getDouble("PricePerMinute");

                    Fanmeet fanmeet = new Fanmeet(fanMeetID,idol,date,startTime,endTime,pricePerMinute,status);
                    fanmeetList.add(fanmeet);
                }
                return fanmeetList;
            }
        } catch (SQLException sqlException) {
            sqlException.getCause().printStackTrace();
        }
        return null;
    }
} // end of FanJDBC class
