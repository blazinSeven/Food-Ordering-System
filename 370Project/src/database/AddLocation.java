package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Written by Yinsheng Dong (yid164)
 * This class is using to insert a location information for an user
 */
public class AddLocation {

    /**
     * public string message
     */
    public String message = null;

    /**
     * new connection
     */
    GoConnection connection = new GoConnection();

    /**
     * Add a new location function
     * @param user_id
     * @param house_num
     * @param street
     * @param city
     * @param province
     * @param post_code
     */
    public void addLocations(int user_id, int house_num, String street, String city, String province, String post_code)
    {

        connection.connect();
        if(connection.coon!=null)
        {
            try{
                // the query to check the user_id is correct or not
                String checkUserId = "select * from users where id = ?";
                PreparedStatement ppCheckStmt = connection.coon.prepareStatement(checkUserId);
                ppCheckStmt.setInt(1,user_id);
                ResultSet checkResult = ppCheckStmt.executeQuery();

                //if it does not, just closed
                if(!checkResult.next())
                {
                    message = "Can not find this user";
                    connection.coon.close();
                }

                //else
                else
                {
                    // the insert location query to insert data to sql database severs
                    String addLocation = "insert into locations(user_id,house_num,street,city,province,post_code) values (?,?,?,?,?,?)";
                    PreparedStatement ppstatement1 = connection.coon.prepareStatement(addLocation);
                    ppstatement1.setInt(1,user_id);
                    ppstatement1.setInt(2,house_num);
                    ppstatement1.setString(3,street);
                    ppstatement1.setString(4,city);
                    ppstatement1.setString(5,province);
                    ppstatement1.setString(6,post_code);
                    int check = ppstatement1.executeUpdate();
                    // if it is stored, saved
                    if(check>0)
                    {
                        System.out.println("the location information has been saved");
                    }

                    System.out.println("Good");
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                e.getErrorCode();
            }
        }
        else {
            System.out.println("No connection");
        }
    }

    /**
     * testing function
     * @param args
     */
    public static void main(String args[])
    {
        AddLocation addLocation = new AddLocation();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the user id: ");
        int id = in.nextInt();
        System.out.println("Please enter the house number: ");
        int house_num = in.nextInt();

        Scanner in1 = new Scanner(System.in);
        System.out.println("Please enter the street name : ");
        String street_name = in1.nextLine();
        System.out.println("Please enter the city: ");
        String city = in1.nextLine();
        System.out.println("Please enter the province: ");
        String province = in1.nextLine();
        System.out.println("Please enter the postal code: ");
        String post_code = in1.nextLine();
        addLocation.addLocations(id,house_num,street_name,city,province,post_code);
    }
}
