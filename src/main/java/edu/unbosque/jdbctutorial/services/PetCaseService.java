package edu.unbosque.jdbctutorial.services;
import edu.unbosque.jdbctutorial.dtos.UserApp;
import edu.unbosque.jdbctutorial.dtos.Visit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetCaseService {
    // Objects for handling connection
    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void insertPetCase(String pet_id, String desc) {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Inserting case...");
            stmt = conn.createStatement();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String sql = "INSERT INTO \"PetCase\" (created_at, type, description, pet_id)  values ('"+ dtf.format(now)+"','Robo','"+desc+"','"+pet_id+"')";
            stmt.execute(sql);
            // Printing results
            System.out.println("Your stolen pet case was successfully saved. We are sorry :(\n");

            // Closing resources
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
