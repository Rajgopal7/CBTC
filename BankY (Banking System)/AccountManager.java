import java.sql.*;
import java.util.Scanner;

public class AccountManager {
    private Connection con;
    private Scanner sc;
    AccountManager(Connection connection, Scanner scanner){
        this.con = connection;
        this.sc = scanner;
    }

    public void credit_money(long account_number)throws SQLException {
        sc.nextLine();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = sc.nextLine();

        try {
            con.setAutoCommit(false);
            if(account_number != 0) {
                PreparedStatement pt = con.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? and security_pin = ? ");
                pt.setLong(1, account_number);
                pt.setString(2, security_pin);
                ResultSet rt = pt.executeQuery();

                if (rt.next()) 
                {
                    String credit_query = "UPDATE Accounts SET balance = balance + ? WHERE account_number = ?";
                    PreparedStatement pt1 = con.prepareStatement(credit_query);
                    pt1.setDouble(1, amount);
                    pt1.setLong(2, account_number);
                    int rowsAffected = pt1.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Rs."+amount+" credited Successfully");
                        con.commit();
                        con.setAutoCommit(true);
                        return;
                    } else {
                        System.out.println("Transaction Failed!");
                        con.rollback();
                        con.setAutoCommit(true);
                    }
                }else{
                    System.out.println("Invalid Security Pin!");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        con.setAutoCommit(true);
    }

    public void debit_money(long account_number) throws SQLException {
        sc.nextLine();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = sc.nextLine();
        try {
            con.setAutoCommit(false);
            if(account_number!=0) {
                PreparedStatement pt = con.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? and security_pin = ? ");
                pt.setLong(1, account_number);
                pt.setString(2, security_pin);
                ResultSet rt = pt.executeQuery();

                if (rt.next()) {
                    double current_balance = rt.getDouble("balance");
                    if (amount<=current_balance){
                        String debit_query = "UPDATE Accounts SET balance = balance - ? WHERE account_number = ?";
                        PreparedStatement pt1 = con.prepareStatement(debit_query);
                        pt1.setDouble(1, amount);
                        pt1.setLong(2, account_number);
                        int rowsAffected = pt1.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Rs."+amount+" debited Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction Failed!");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Pin!");
                }
            }
        }catch (SQLException e){
                e.printStackTrace();
            }
        con.setAutoCommit(true);
    }

    public void transfer_money(long sender_account_number) throws SQLException {
        sc.nextLine();
        System.out.print("Enter Receiver Account Number: ");
        long receiver_account_number = sc.nextLong();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = sc.nextLine();
        try{
            con.setAutoCommit(false);
            if(sender_account_number!=0 && receiver_account_number!=0){
                PreparedStatement pt = con.prepareStatement("SELECT * FROM Accounts WHERE account_number = ? AND security_pin = ? ");
                pt.setLong(1, sender_account_number);
                pt.setString(2, security_pin);
                ResultSet rt = pt.executeQuery();

                if (rt.next()) {
                    double current_balance = rt.getDouble("balance");
                    if (amount<=current_balance){

                        // Write debit and credit queries
                        String debit_query = "UPDATE Accounts SET balance = balance - ? WHERE account_number = ?";
                        String credit_query = "UPDATE Accounts SET balance = balance + ? WHERE account_number = ?";

                        // Debit and Credit prepared Statements
                        PreparedStatement creditpt = con.prepareStatement(credit_query);
                        PreparedStatement debitpt = con.prepareStatement(debit_query);

                        // Set Values for debit and credit prepared statements
                        creditpt.setDouble(1, amount);
                        creditpt.setLong(2, receiver_account_number);
                        debitpt.setDouble(1, amount);
                        debitpt.setLong(2, sender_account_number);
                        int rowsAffected1 = debitpt.executeUpdate();
                        int rowsAffected2 = creditpt.executeUpdate();
                        if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                            System.out.println("Transaction Successful!");
                            System.out.println("Rs."+amount+" Transferred Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction Failed");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Security Pin!");
                }
            }else{
                System.out.println("Invalid account number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        con.setAutoCommit(true);
    }

    public void getBalance(long account_number){
        sc.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = sc.nextLine();
        try{
            PreparedStatement pt = con.prepareStatement("SELECT balance FROM Accounts WHERE account_number = ? AND security_pin = ?");
            pt.setLong(1, account_number);
            pt.setString(2, security_pin);
            ResultSet rt = pt.executeQuery();
            if(rt.next()){
                double balance = rt.getDouble("balance");
                System.out.println("Balance: "+balance);
            }else{
                System.out.println("Invalid Pin!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
