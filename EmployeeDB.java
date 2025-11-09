import java.sql.*;
import java.util.Scanner;

public class EmployeeDB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Step 1: Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/company", "root", "Trushant@2001");

            Statement st = con.createStatement();

            // Insert Record
            System.out.println("Inserting record...");
            st.executeUpdate("INSERT INTO Employee VALUES (105, 'Kiran', 'MH', 55000)");
            System.out.println("Record Inserted Successfully\n");

            // Retrieve by SSN
            System.out.print("Enter SSN to retrieve record: ");
            int ssn = sc.nextInt();
            ResultSet rs = st.executeQuery("SELECT * FROM Employee WHERE SSN = " + ssn);
            while (rs.next()) {
                System.out.println("SSN: " + rs.getInt(1) + ", Name: " + rs.getString(2) +
                        ", State: " + rs.getString(3) + ", Salary: " + rs.getDouble(4));
            }

            // Update State MH → TN
            st.executeUpdate("UPDATE Employee SET State='TN' WHERE State='MH'");
            System.out.println("\nState Updated Successfully\n");

            // Delete employees from Gujrat
            st.executeUpdate("DELETE FROM Employee WHERE State='Gujrat'");
            System.out.println("Employees from Gujrat Deleted Successfully\n");

            // Close Connection
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();
    }
}


// Compile (with correct jar name)
// javac -cp .:"mysql-connector-j-9.5.0.jar" EmployeeDB.java

// (base) trushantramdasjadhav@Trushants-MacBook-Air DBMS-Final % java -cp .:"mysql-connector-j-9.5.0.jar" EmployeeDB 

// Inserting record...
// Record Inserted Successfully

// Enter SSN to retrieve record: 101
// SSN: 101, Name: Rahul, State: TN, Salary: 45000.0

// State Updated Successfully

// Employees from Gujrat Deleted Successfully

// (base) trushantramdasjadhav@Trushants-MacBook-Air DBMS-Final % 
