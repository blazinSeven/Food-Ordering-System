package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Create by Yinsheng Dong (yid164)
 * This class is used to add an user, this should be before add customer and restaurant
 **/
public class AddUser {
    /**
     * New connection
     **/
    public GoConnection connect = new GoConnection();

    /**
     * this is for user_status
     */
    public String st = null;

    /**
     * this is for username
     */
    public String un = null;

    /**
     * public message
     */
    public String message = "";

    /**
     * The function is use for inserting an user to the database
     * @param username username that user created
     * @param userPassword password that user created
     * @param status if they are restaurant or not
     * @Pre the username has not been used
     */
    public void addUser(String username, String userPassword, String status)
    {
        connect.connect();
        if(connect.coon != null)
        {

            try {
                // check if the username has been used or not
                String checkQuery = "select username from users where username = ?";
                PreparedStatement checkStmt = connect.coon.prepareStatement(checkQuery);
                checkStmt.setString(1,username);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next())
                {
                    message = "This username has been used";
                    System.out.println(message);
                    connect.coon.close();
                }


                else
                {
                    // if not used username, then insert them to database
                    String insertQuery = "insert into users (username, passwords, user_status) values(?,?,?)";
                    PreparedStatement ppStmt = connect.coon.prepareStatement(insertQuery);
                    un = username;
                    st = status;
                    ppStmt.setString(1, username);
                    ppStmt.setString(2, userPassword);
                    ppStmt.setString(3, status);
                    int affected = ppStmt.executeUpdate();
                    if (affected > 0) {
                        message = "You have successfully signed up";
                        System.out.println(message);
                        connect.coon.close();
                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * main function for testing
     * @param arg
     */
    public static void main (String arg[])
    {
        AddUser addUser = new AddUser();
        addUser.connect.connect();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the username: \n");
        String username = in.nextLine();
        System.out.println("Please enter the password: \n");
        String password = in.nextLine();
        System.out.println("Please enter your status: \n");
        String status = in.nextLine();
        addUser.addUser(username,password,status);

    }


}
