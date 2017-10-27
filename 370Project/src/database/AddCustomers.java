package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddCustomers {
    GoConnection connect = new GoConnection();
    public AddUser addUser = null;
    public String message = null;
    public int userId =1024;
    public void setCustomerInfo(String firstName, String lastName, String phoneNum, String eMail, String prefFood)
    {
        connect.connect();
        if(connect.coon != null)
        {
            try{
                String addQuery = "insert into customers (user_id, customer_first_name, customer_last_name, phone_num, e_mail_address, pref_food) values (?,?,?,?,?,?)";
                String getUserId = "select id from users where username = '" + addUser.un +"'";
                Statement stmt = connect.coon.createStatement();
                ResultSet results = stmt.executeQuery(getUserId);
                results.next();
                userId = results.getInt(1);
                PreparedStatement ppStmt = connect.coon.prepareStatement(addQuery);
                ppStmt.setInt(1,userId);
                ppStmt.setString(2,firstName);
                ppStmt.setString(3,lastName);
                ppStmt.setString(4,phoneNum);
                ppStmt.setString(5,eMail);
                ppStmt.setString(6,prefFood);
                int affected = ppStmt.executeUpdate();
                if(affected > 0)
                {
                    message = "The customer information has been saved";
                    System.out.println(message);
                    connect.coon.close();
                }

            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String arg[])
    {
        AddUser addUser = new AddUser();
        AddCustomers addCustomers = new AddCustomers();
        addUser.connect.connect();
        addCustomers.connect.connect();
        addCustomers.addUser = addUser;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the username: \n");
        String username = in.nextLine();
        System.out.println("Please enter the password: \n");
        String password = in.nextLine();
        System.out.println("Please enter your status: \n");
        String status = in.nextLine();
        addUser.addUser(username,password,status);
        System.out.println("Please enter your first name: \n");
        String firstName = in.nextLine();
        System.out.println("Please enter your last name: \n");
        String lastName = in.nextLine();
        System.out.println("Please enter your phone number: \n");
        String phoneNum = in.nextLine();
        System.out.println("Please enter your e-mail address: \n");
        String email = in.nextLine();
        System.out.println("Please enter your pref food: \n");
        String prefFood = in.nextLine();
        addCustomers.setCustomerInfo(firstName,lastName,phoneNum,email,prefFood);


    }
}
