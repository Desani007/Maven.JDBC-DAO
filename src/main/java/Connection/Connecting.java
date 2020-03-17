package Connection;


import daos.CarDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecting {


    private static String USERNAME = "root";
    private static String PASSWORD = "Kasper";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/Car";


    public static Connection getDatabaseConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {

            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection conn= null;
        try {
            conn = getDatabaseConnection();
            if (conn != null) {
                System.out.println("Connected to the database");
            } else

                System.out.println("An error occurred. Maybe user/password is invalid");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        CarDaoImpl dao = new CarDaoImpl();
        dao.create();
}

}
