package jdbc;

import shared.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthenticationJDBC {
    private static Connection connection;
    private static String query;
    private static Statement statement;
    private static ResultSet resultSet;
    /**This method checks the signs up the user to the database*/
    public static void login(User userLogin ) throws Exception{
        //todo: implement the methods
    }

    /**This method checks the login of the user to the database*/
    public static void signUp(User userSignUp) throws Exception{
        //todo: implement the method
    }
}
