package jdbc;

import shared.Fanmeet;

import java.sql.*;
import java.util.List;

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

    public List<Fanmeet> loadFinishedFanmeets(){
        return null;
    }

    /**TODO: MARIUSSSSSS THIS MAINM METHOD WILL BE YOUR TESTING METHOD*/
    public static void main(String[] args) {

    }
}
