
/**
 * Write a description of class MainApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.*;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Connection conn = DBConnection.connect();

        if (conn == null) return;

        DBConnection.createTables(conn);

        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Phone: ");
                    String phone = sc.next();
                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    System.out.print("Enter License No: ");
                    String license = sc.next();

                    PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)"
                    );

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, phone);
                    ps.setString(4, email);
                    ps.setString(5, license);

                    ps.executeUpdate();
                    System.out.println("Customer added.");
                }
                else if (choice == 2) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");

                    System.out.println("\nCustomers:");
                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("customer_id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getString("phone") + " | " +
                            rs.getString("email") + " | " +
                            rs.getString("driver_license_no")
                        );
                    }
                }
                else if (choice == 3) {
                    System.out.print("Enter Customer ID to update: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new name: ");
                    String newName = sc.next();
                    System.out.print("Enter new email: ");
                    String newEmail = sc.next();

                    PreparedStatement ps = conn.prepareStatement(
                        "UPDATE Customer SET name = ?, email = ? WHERE customer_id = ?"
                    );

                    ps.setString(1, newName);
                    ps.setString(2, newEmail);
                    ps.setInt(3, id);

                    ps.executeUpdate();
                    System.out.println("Customer updated.");
                }
                else if (choice == 4) {
                    System.out.print("Enter Customer ID to delete: ");
                    int id = sc.nextInt();

                    PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM Customer WHERE customer_id = ?"
                    );

                    ps.setInt(1, id);
                    ps.executeUpdate();
                    System.out.println("Customer deleted.");
                }
                else if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);}
        }
    }  