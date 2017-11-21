package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Written by Yinsheng Dong (yid164)
 * This function is using for insert a customer and set a customer information to the database
 */
public class AddCustomers {

    /**
     * new connection
     */
    GoConnection connect = new GoConnection();

    /**
     * need first add an user then, add the customer
     */
    public AddUser addUser = null;

    /**
     * String message for public
     */
    public String message = null;

    /**
     * The function to add an customer's information to the database
     * @param firstName
     * @param lastName
     * @param phoneNum
     * @param eMail
     * @param prefFood
     */
    public int userId =1024;
    public void setCustomerInfo(String firstName, String lastName, String phoneNum, String eMail, String prefFood)
    {
        /**
         * connection
         */
        connect.connect();
        if(connect.coon != null)
        {
            try{
                // the query to insert customer's info to the data
                // but we need to check if the user_id is stored or not
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
                // If it is executed, then the message to "saved"
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

    /**
     * Testing function
     * @param arg
     */
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
