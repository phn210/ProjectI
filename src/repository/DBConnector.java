package repository;

import java.sql.*;

public class DBConnector {
    public static Connection connection;
    private static String connectionURL = "jdbc:sqlserver://localhost;database=ProjectI";
    private static String user = "sa";
    private static String password = "123456";

    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection(connectionURL, user, password);
            if(connection!=null){
                System.out.println("Connect successful");
            }
        }
        catch(SQLException e){
            System.out.println("Some errors occurred!");
            e.printStackTrace();
        }
        return connection;
    }
}