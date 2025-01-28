import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection con;
    private Scanner sc;

    public Accounts(Connection connection, Scanner scanner){
        this.con = connection;
        this.sc = scanner;

    }

    // For creating a new account of a customer.
    public long open_account(String email)
    {
        if(!account_exist(email)) 
        {
            String open_account_query = "INSERT INTO Accounts(account_number, full_name, email, balance, security_pin) VALUES(?, ?, ?, ?, ?)";
            sc.nextLine();
            System.out.print("Enter Full Name:- ");
            String full_name = sc.nextLine();
            System.out.print("Enter Initial Amount:- ");
            double balance = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter a 4-Digit Security Pin:- ");
            String security_pin = sc.nextLine();
            try {
                long account_number = generateAccountNumber();    // calls the method to have an unique acc_no.
                PreparedStatement pt = con.prepareStatement(open_account_query);
                pt.setLong(1, account_number);
                pt.setString(2, full_name);
                pt.setString(3, email);
                pt.setDouble(4, balance);
                pt.setString(5, security_pin);
                int rowsAffected = pt.executeUpdate();
                if (rowsAffected > 0) {
                    return account_number;
                } else {
                    throw new RuntimeException("Account Creation failed!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Account Already Exist");

    }

    // For fetching the account number corresponding to the email ID:- 
    public long getAccount_number(String email) {
        String query = "SELECT account_number from Accounts WHERE email = ?";
        try{
            PreparedStatement pt = con.prepareStatement(query);
            pt.setString(1, email);
            ResultSet rt = pt.executeQuery();
            if(rt.next()){
                return rt.getLong("account_number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Account Number Doesn't Exist!");
    }

    // For generating a Unique Account number for each user:- 
    private long generateAccountNumber() 
    {
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery("SELECT account_number from Accounts ORDER BY account_number DESC LIMIT 1");
            if (rt.next()) {
                long last_account_number = rt.getLong("account_number");
                return last_account_number+1;
            } else {
                return 10000100;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 10000100;
    }

    // Checks if the account exists in the database:- 
    // If the returned value is false then only a new account will be opened for the user.
    public boolean account_exist(String email){
        String query = "SELECT account_number from Accounts WHERE email = ?";
        try{
            PreparedStatement pt = con.prepareStatement(query);
            pt.setString(1, email);
            ResultSet rt = pt.executeQuery();
            if(rt.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}