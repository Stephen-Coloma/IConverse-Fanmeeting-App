package jdbc;

import fan.Fan;
import shared.Fanmeet;
import shared.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**This class will be used to create methods that accesses the database solely intended for Idols*/
public class IdolJDBC {

    private static Connection connection;

    static {
        try {
            //changeable
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/leonardos", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String query;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static List<Fanmeet> loadFinishedFanmeets(int userID)throws Exception{
        List<Fanmeet> fanmeetList = new ArrayList<>();
        query= "SELECT FanmeetID, IdolName, Date, StartTime, EndTime, PricePerMinute, Status " +
                "from fanmeets " +
                "WHERE status = \"Finished\" " +
                "AND idolname = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        preparedStatement.setInt(1, userID);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Fanmeet fanmeet = new Fanmeet();

            fanmeet.setFanMeetID(resultSet.getInt("FanMeetID"));
            fanmeet.setDate(resultSet.getDate("Date").toLocalDate());
            fanmeet.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            fanmeet.setEndTime(resultSet.getTime("EndTime").toLocalTime());
            fanmeet.setPricePerMinute(resultSet.getDouble("PricePerMinute"));
            fanmeet.setStatus(resultSet.getString("Status"));

            fanmeetList.add(fanmeet);
        }
    return fanmeetList;
    }
    public static List<Fanmeet> loadUnFinishedFanmeets(int userID)throws Exception{
        List<Fanmeet> fanmeetList = new ArrayList<>();

        query= "SELECT FanmeetID, IdolName, Date, StartTime, EndTime, PricePerMinute, Status " +
                "from fanmeets " +
                "WHERE status = \"Unfinished\" " +
                "AND idolname = ?";


        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        preparedStatement.setInt(1, userID);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Fanmeet fanmeet = new Fanmeet();

            fanmeet.setFanMeetID(resultSet.getInt("FanMeetID"));
            fanmeet.setDate(resultSet.getDate("Date").toLocalDate());
            fanmeet.setStartTime(resultSet.getTime("StartTime").toLocalTime());
            fanmeet.setEndTime(resultSet.getTime("EndTime").toLocalTime());
            fanmeet.setPricePerMinute(resultSet.getDouble("PricePerMinute"));
            fanmeet.setStatus(resultSet.getString("Status"));

            fanmeetList.add(fanmeet);

        }
        return fanmeetList;
    }

    public static void main(String[] args) {
        try {
            List<Fanmeet> listOfUnFinishedFanmeets = IdolJDBC.loadUnFinishedFanmeets(2);
            for(Fanmeet fanmeet: listOfUnFinishedFanmeets) {
                    System.out.println("ID: " +fanmeet.getFanMeetID());
                    System.out.println("DATE: " +fanmeet.getDate());
                    System.out.println("STARTIME: "+fanmeet.getStartTime());
                    System.out.println("ENDTIME: "+fanmeet.getEndTime());
                    System.out.println("PRICE: " +fanmeet.getPricePerMinute());
                    System.out.println("STATUS: " +fanmeet.getStatus());
                    System.out.println("=========================\n");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
