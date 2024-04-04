package jdbc;

import fan.controller.Bookings;
import shared.Booking;
import shared.Fanmeet;
import shared.User;

import javax.xml.transform.Result;
import java.sql.*;
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

        String sql = "SELECT * FROM users WHERE UserType LIKE 'Idol';";

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

    public static List<Booking> getBookingList(int userID) {

        String sql;


        return null; // temporary
    }

    public static List<Fanmeet> getFanMeetList(int idolID) {
        // TODO: make a query to obtain the list of fanmeets of an idol using the idolID: JERWIN




        return null; // temporary, return the list of fan-meets of a specific idol instead
    }
} // end of FanJDBC class
