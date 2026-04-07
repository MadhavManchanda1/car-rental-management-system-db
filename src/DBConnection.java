
/**
 * Write a description of class DBConnection here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    public static Connection connect() {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:car_rental.db");
            System.out.println("Connected to database.");
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        return conn;
    }

    public static void createTables(Connection conn) {
        try {
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS Customer (customer_id INTEGER PRIMARY KEY, name TEXT, phone TEXT, email TEXT, driver_license_no TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Car (car_id INTEGER PRIMARY KEY, brand TEXT, model TEXT, year INTEGER, daily_rate REAL, availability_status TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Reservation (reservation_id INTEGER PRIMARY KEY, customer_id INTEGER, car_id INTEGER, start_date TEXT, end_date TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Rental (rental_id INTEGER PRIMARY KEY, reservation_id INTEGER, pickup_date TEXT, return_date TEXT, total_cost REAL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS Payment (payment_id INTEGER PRIMARY KEY, rental_id INTEGER, payment_date TEXT, amount REAL, payment_method TEXT)");

            System.out.println("Tables created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}