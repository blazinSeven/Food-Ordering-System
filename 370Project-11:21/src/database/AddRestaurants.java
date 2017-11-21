package database;

import java.sql.*;
import java.util.Scanner;


/**
 * Written by Yinsheng Dong (yid164)
 * This class is used to add an restaurant information
 */
public class AddRestaurants {
    /**
     * connection of database
     */
    GoConnection connect = new GoConnection();

    /**
     * when a restaurant info is added, we have to add the user first
     */
    public AddUser addUser = null;

    /**
     * String message
     */
    public String message = null;

    /**
     * The setRestaurant information function, to insert teh params
     * @param restName restaurant name
     * @param licenseId licensed id
     * @param openTime open time
     * @param closeTime close time
     * @param phone_num restaurant phone number
     * @param emailAddress restaurant address
     */
    public void setRestaurantInfo(String restName, String licenseId, Time openTime, Time closeTime, String phone_num, String emailAddress)
    {
        // connect first
        connect.connect();
        if (connect.coon != null)
        {
            try{
                // getting user id from data, then insert them to the database
                String getUserId = "select id from users where username = '" + addUser.un +"'";
                String addQuery = "insert into restaurants (user_id, restaurant_name, license_id, open_time, close_time,phone_num, e_mail_address) values (?,?,?,?,?,?,?)";
                Statement stmt = connect.coon.createStatement();
                ResultSet results = stmt.executeQuery(getUserId);
                results.next();
                int userId = results.getInt(1);
                PreparedStatement ppStmt = connect.coon.prepareStatement(addQuery);
                ppStmt.setInt(1,userId);
                ppStmt.setString(2,restName);
                ppStmt.setString(3,licenseId);
                ppStmt.setTime(4,openTime);
                ppStmt.setTime(5,closeTime);
                ppStmt.setString(6,phone_num);
                ppStmt.setString(7,emailAddress);
                int affected = ppStmt.executeUpdate();
                if(affected > 0)
                {
                    message = "The restaurant information has been saved";
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
     * main function for testing
     * @param arg
     */
    public static void main(String arg[])
    {
        AddUser addUser = new AddUser();
        AddRestaurants addRestaurants = new AddRestaurants();
        addUser.connect.connect();
        addRestaurants.connect.connect();
        addRestaurants.addUser = addUser;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the username: \n");
        String username = in.nextLine();
        System.out.println("Please enter the password: \n");
        String password = in.nextLine();
        System.out.println("Please enter your status: \n");
        String status = in.nextLine();
        addUser.addUser(username,password,status);
        System.out.println("Please enter restaurant name: \n");
        String restName = in.nextLine();
        System.out.println("Please enter restaurant licence id: \n");
        String licenceId = in.nextLine();
        System.out.println("Please enter restaurant open time (HH:MM:SS): \n");
        String time1 = in.nextLine();
        Time startTime = Time.valueOf(time1);
        System.out.println("Please enter restaurant close time (HH:MM:SS): \n");
        String time2 = in.nextLine();
        Time closeTime = Time.valueOf(time2);

        System.out.println("Please enter the restaurant phone number: \n");
        String phoneNum = in.nextLine();
        System.out.println("Please enter your e-mail address: \n");
        String email = in.nextLine();

        addRestaurants.setRestaurantInfo(restName,licenceId,startTime,closeTime,phoneNum,email);
    }

}
