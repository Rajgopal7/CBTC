import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection con;
    private Scanner sc;

    public User(Connection connection, Scanner scanner){
        this.con = connection;
        this.sc = scanner;
    }

    // To register a new User into the Database.
    public void register()
    {
        sc.nextLine();                      // Consumes the empty line.
        System.out.print("Full Name:- ");
        String full_name = sc.nextLine();
        System.out.print("Email:- ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if(user_exist(email)) {
            System.out.println("User Already Exists for this Email Address!!");
            return;
        }
        String register_query = "INSERT INTO User(full_name, email, password) VALUES(?, ?, ?)";
        try {
            PreparedStatement pt = con.prepareStatement(register_query);
            pt.setString(1, full_name);
            pt.setString(2, email);
            pt.setString(3, password);
            int affectedRows = pt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registration Successfull!");
            } else {
                System.out.println("Registration Failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // To login into the Banking system.
    public String login()
    {
        sc.nextLine();
        System.out.print("Email:- ");
        String email = sc.nextLine();
        System.out.print("Password:- ");
        String password = sc.nextLine();
        String login_query = "SELECT * FROM User WHERE email = ? AND password = ?";
        try{
            PreparedStatement pt = con.prepareStatement(login_query);
            pt.setString(1, email);
            pt.setString(2, password);
            ResultSet rt = pt.executeQuery();
            if(rt.next())
            {
                return email;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean user_exist(String email)
    {
        String query = "SELECT * FROM user WHERE email = ?";
        try{
            PreparedStatement pt = con.prepareStatement(query);
            pt.setString(1, email);
            ResultSet rt = pt.executeQuery();
            if(rt.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}