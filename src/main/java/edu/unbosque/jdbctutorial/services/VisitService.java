package edu.unbosque.jdbctutorial.services;
import edu.unbosque.jdbctutorial.dtos.UserApp;
import edu.unbosque.jdbctutorial.dtos.Visit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitService {
    // Objects for handling connection
    private Connection conn;

    public VisitService(Connection conn) {
        this.conn = conn;
    }

    public void consulVisit(String pet_id) {
        List<Visit> visits = new ArrayList<Visit>();

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // HACES LA CONSULTA
            stmt = conn.createStatement();
            String sql = "SELECT * FROM \"Visit\" WHERE pet_id = '"+pet_id+"'";
            ResultSet rs = stmt.executeQuery(sql);

            //LEES CADA FILA
            while(rs.next()) {
                // Extracting row values by column name
                String visit_id = rs.getString("visit_id");
                String created_at = rs.getString("created_at");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String vet_id = rs.getString("vet_id");
                String pet = rs.getString("pet_id");

                visits.add(new Visit(visit_id, created_at, type, description, vet_id, pet));
            }

            System.out.println("   ");
            for (Visit visit : visits) {
                System.out.println("Visit ID: "+visit.getVisit_id());
                System.out.println("Date Created: "+visit.getCreated_at());
                System.out.println("Type: "+visit.getType());
                System.out.println("Description: "+visit.getDescription());
                System.out.println("Vet ID: "+visit.getVet_id());
                System.out.println("Pet: "+visit.getPet_id());

            }

            // Closing resources
            rs.close();
            stmt.close();

        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
