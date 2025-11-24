import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskCRUD {

    public static void main(String[] args) {

        String baseUrl = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "Admin123";
        String tempDB = "tempdb";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");

            // ⿡ Connect to default postgres database
            conn = DriverManager.getConnection(baseUrl + "postgres", user, password);
            System.out.println("\n[1] Connected to postgres.");

            stmt = conn.createStatement();

            // ⿢ Create temporary database
            stmt.executeUpdate("CREATE DATABASE " + tempDB);
            System.out.println("[2] Temporary database '" + tempDB + "' created.");

            stmt.close();
            conn.close();

            // ⿣ Connect to new tempdb
            conn = DriverManager.getConnection(baseUrl + tempDB, user, password);
            System.out.println("[3] Connected to tempdb.");

            stmt = conn.createStatement();

            // ⿤ Create student1 table
            String createTable = "CREATE TABLE student1 (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "dept_name VARCHAR(50)" +
                    ");";

            stmt.executeUpdate(createTable);
            System.out.println("[4] Table 'student1' created.");

            // ⿥ Insert sample data (CORRECTED)
            stmt.executeUpdate("INSERT INTO student1 (id, name, dept_name) VALUES (1, 'Fardeen', 'BBA');");
            stmt.executeUpdate("INSERT INTO student1 (id, name, dept_name) VALUES (2, 'Arjun', 'BCA');");
            stmt.executeUpdate("INSERT INTO student1 (id, name, dept_name) VALUES (3, 'Sana', 'BCS');");
            System.out.println("[5] Inserted sample data.");

            // ⿦ Display data
            rs = stmt.executeQuery("SELECT * FROM student1");
            System.out.println("\nID | Name | Department");
            System.out.println("-------------------------");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("dept_name"));
            }

            // ⿧ Drop table
            stmt.executeUpdate("DROP TABLE student1;");
            System.out.println("\n[6] Table 'student1' deleted.");

            stmt.close();
            conn.close();

            // ⿨ Connect to postgres again and drop tempdb
            conn = DriverManager.getConnection(baseUrl + "postgres", user, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("DROP DATABASE " + tempDB);
            System.out.println("[7] Database '" + tempDB + "' deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Cleanup
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}

            System.out.println("\n[8] All JDBC Connections Closed.");
  }

}
}