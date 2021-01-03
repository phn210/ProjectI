package repository;

import java.sql.*;

public class DBConnector {
    static Connection connection;
    private static String connectionURL = "jdbc:sqlserver://THINKPAD;database=ProjectI;";
    private static String user = "sa";
    private static String password = "";

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(connectionURL, user, password);
        }
        catch(SQLException e){
            System.out.println("Some errors occurred!");
            e.printStackTrace();
        }
        return connection;
    }
}