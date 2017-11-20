package database;
import Entities.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Scanner;
/**
 * Created by Kocur on 2017-10-25.
 */
public class AddOrderLines {

    GoConnection connection = new GoConnection();
    //final Food newFood;
    public AddUser addFood = null;
    public String message = null;
    //Orderlines have...id, order_id, quantity, price_per_one, price_total, discount_total

    public void setOrderLineInfo(int order_id, int dishes_id, int quantity, float pricePerUnit, float priceTotal, int discount_total)
    {
        //try and connect to our server, if the connection isn't null, procceed, else check exception from SQL api
        connection.connect();
        if (connection.coon != null) {
            try {
                //These lines setup SQL queries & check handle problematic cases using SQL API
                String addQuery = "INSERT INTO order_lines(order_id, dishes_id, quantity, price_per_one, price_total, discount_total";
                String getMenuID = "SELECT dishes_id FROM dishes WHERE dishes_id = ?";
                String getOrderID = "SELECT order_id FROM orders WHERE order_id = ?";
                PreparedStatement menStmnt = connection.coon.prepareStatement(getMenuID);
                PreparedStatement orderStmnt = connection.coon.prepareStatement(getOrderID);

                orderStmnt.setInt(1,order_id);
                menStmnt.setInt(1,dishes_id );

                ResultSet results1 = menStmnt.executeQuery();
                ResultSet results2 = orderStmnt.executeQuery();

                if(results1.next() && results2.next()) {
                    PreparedStatement ppStmt = connection.coon.prepareStatement(addQuery);
                    ppStmt.setInt(1, order_id);
                    ppStmt.setInt(2, dishes_id);
                    ppStmt.setInt(3, quantity);
                    ppStmt.setFloat(4, pricePerUnit);
                    ppStmt.setFloat(5, priceTotal);
                    ppStmt.setInt(6, discount_total);
                    int affected = ppStmt.executeUpdate();
                    if (affected > 0) {
                        message = "The dish information has been saved!";
                        System.out.println(message);
                        connection.coon.close();
                    }
                }
                else{
                        System.out.println("There is no order_line to add");
                        connection.coon.close();
                    }


                }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        //TODO: Add testing
    }
    



}
