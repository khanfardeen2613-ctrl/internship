import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class selectquery {public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/internship_day1";
    String user = "postgres";
    String password = "Admin123";

    try {
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected!");

        String sql = "SELECT * FROM department";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                    rs.getInt("dept_id") + " | " +
                            rs.getString("dept_name") + " | " +
                            rs.getString("hod_name")
            );
        }

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

