/**
 * Created by Josh on October 22nd
 */

package database;
import Entities.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Scanner;

public class AddDishes {
    GoConnection connection = new GoConnection();
    //final Food newFood;
    public AddUser addFood = null;
    public String message = null;

    /**
     public AddDishes(Food newItem) {
     this.newFood = newItem;
     }

     **/

    public void setDishInfo(int menus_id, String dish_name, float dish_price) {
        connection.connect();
        if (connection.coon != null)
        {
            try {
                //These lines setup SQL queries & check handle problematic cases using SQL API
                String addQuery = "insert into dishes (menus_id, dish_name, dish_prices) values (?,?,?)";
                String getUserId = "select menu_id from menus where menu_id = ?";
                PreparedStatement stmt1 = connection.coon.prepareStatement(getUserId);
                stmt1.setInt(1,menus_id);
                ResultSet results = stmt1.executeQuery();
                if(results.next())
                {
                    PreparedStatement ppStmt = connection.coon.prepareStatement(addQuery);
                    ppStmt.setInt(1, menus_id);
                    ppStmt.setString(2, dish_name);
                    ppStmt.setFloat(3, dish_price);
                    int affected = ppStmt.executeUpdate();
                    if (affected > 0) {
                        message = "The dish information has been saved!";
                        System.out.println(message);
                        connection.coon.close();
                    }
                }
                else
                {
                    System.out.println("There no menu id to this");
                    connection.coon.close();
                }




                //Parsing food price into a string for query.
                //Double stringPrice = newFood.getPrice();

                //Parsing food preptime into a string for query
                //String prepString = newFood.getPrepTime().toString();

                //Uses properties of the food object as arguments passed to the SQL query


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }


    //Using this for testing.
    public static void main(String arg[])
    {


        System.out.println("enter the menu id");
        Scanner in = new Scanner(System.in);
        int menu_id = in.nextInt();

        Scanner in1 = new Scanner(System.in);
        System.out.println("Enter the dish name: ");
        String name = in1.nextLine();

        System.out.println("Enter the dish price: ");
        float price = in1.nextFloat();


        AddDishes addDishes = new AddDishes();
        addDishes.setDishInfo(menu_id,name,price);


        //AddUser addDish = new AddUser();


        //Scanner in = new Scanner(System.in);

            /*Get Menu id --- Will probably fiddle with this later, it's not good using it like this because it doesn't
             act securely  and relies on users not being bad. */
        /**
         System.out.println("Please enter the dishes' menu_id, it MUST BE AN INTEGER: \n");
         int menu_id = in.nextInt();

         //Get Dish name
         System.out.println("Please enter the dishes' name: \n");
         String dishName = in.nextLine();

         //Get Dish Price
         System.out.println("Please enter the dishes' price: \n");
         Double dishPrice = in.nextDouble();

         //Get Dish prep time
         System.out.println("Please enter the dishes' prep time in minutes: \n");
         Integer dishTime = in.nextInt();

         /*Get Dish id -- This might be a bad way to do it, can change it later to something like "rand" to generate it
         That way a stupid user won't mess it up */
        /**
         System.out.println("Please enter the dishes' ID: \n");
         int dishID = in.nextInt();

         Food newFood = new Food(dishName, dishPrice, Duration.ofMinutes(dishTime), dishID);

         // These lines setup the database and add the user defined food to it.
         AddDishes addDishes  = new AddDishes(newFood);
         addDishes.connection.connect();
         addDishes.addFood = addDish;
         addDishes.setDishInfo(menu_id);
         **/


    /*
    //
    //        AddUser addUser = new AddUser();
    //        AddCustomers addCustomers = new AddCustomers();
    //        addUser.connect.connect();
    //        addCustomers.connect.connect();
    //        addCustomers.addUser = addUser;
    //        Scanner in = new Scanner(System.in);
            System.out.println("Please enter the username: \n");
            String username = in.nextLine();
            System.out.println("Please enter the password: \n");
            String password = in.nextLine();
            System.out.println("Please enter your status: \n");
            String status = in.nextLine();
            addUser.addUser(username,password,status):
            //       System.out.println("Please enter your first name: \n");

    //        String firstName = in.nextLine();
    //        System.out.println("Please enter your last name: \n");
    //        String lastName = in.nextLine();
    //        System.out.println("Please enter your phone number: \n");
    //        String phoneNum = in.nextLine();
    //        System.out.println("Please enter your e-mail address: \n");
    //        String email = in.nextLine();
    //        System.out.println("Please enter your pref food: \n");
    //        String prefFood = in.nextLine();
    //        addCustomers.setCustomerInfo(firstName,lastName,phoneNum,email,prefFood);
    */

    }
}