import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/internship_day1";
        String user = "postgres";
        String password = "Admin123";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL successfully!");

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
 }
}
}
