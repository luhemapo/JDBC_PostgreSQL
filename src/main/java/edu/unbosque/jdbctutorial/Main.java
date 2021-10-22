package edu.unbosque.jdbctutorial;

import java.util.*;
import java.sql.*;

import edu.unbosque.jdbctutorial.dtos.Owner;
import edu.unbosque.jdbctutorial.dtos.UserApp;
import edu.unbosque.jdbctutorial.services.*;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/PetsDB";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "1234";


    public Main(){

        // Objects for handling connection
        Connection conn = null;

        try {

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            menu(conn);


            //PetsService petsService = new PetsService(conn);
            //petsService.countBySpecies("perro");

            //OwnersService ownersService = new OwnersService(conn);
            //ownersService.updateOwner(new Owner(6698, null, "Pepito Perez"));

            boolean valid = conn.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            // Closing database connection
            conn.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch(ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public void menu(Connection conn){

        String continueMenu = "Y";
        System.out.println("-------------------------------------");
        System.out.println("      WELCOME TO PETS MANAGER");

        while (continueMenu.equalsIgnoreCase("y")) {
            System.out.println("-------------------------------------");
            System.out.println("              MENU");
            System.out.println("-------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1.Consult users by role.");
            System.out.println("2.Count veterinaries registered on the platform.");
            System.out.println("3.Consult visits logged by Pet's ID.");
            System.out.println("4.Register stolen pet case.");
            System.out.println("5.Exit.");
            Scanner sc = new Scanner(System.in);
            String option = sc.next();


            switch (option) {
                case "1":
                    System.out.println("What role would you like to search for?: ");
                    String rol = sc.next();
                    UsersService usersService = new UsersService(conn);
                    usersService.listUsers(rol.toLowerCase(Locale.ROOT));
                    break;


                case "2":
                    System.out.println("Registered veterinaries: ");
                    VetService vetService = new VetService(conn);
                    vetService.countVets();
                    break;

                case "3":
                    System.out.println("What  ID would you like to search for?   : ");
                    String pet_id = sc.next();
                    VisitService visitService = new VisitService(conn);
                    visitService.consulVisit(pet_id);
                    break;
                case "4":
                    System.out.println("What was yous pet's ID?");
                    String pet_id_case= sc.next();
                    System.out.println("What happend with your pet?");
                    sc.nextLine();
                    String description= sc.nextLine();
                    PetCaseService caseService = new PetCaseService(conn);
                    caseService.insertPetCase(pet_id_case, description);

                    break;


                case "5":
                    continueMenu = "n";
                    break;

                default:
                    System.out.println("Please tipe a valid menu number");
                    System.out.println("Would you like to perform another action? \nType Y/N");
                    continueMenu = sc.next();
            }

        }
        System.out.println("-------------------------------------");
        System.out.println("      Have a nice day =)");


    }


    public static void main(String[] args) {
        Main m = new Main();

    }
}
