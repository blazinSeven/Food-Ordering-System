package database;
/**
 * Create by Yinsheng Dong Nov 8 2017
 *
 */

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddOrders {
    private GoConnection connection = new GoConnection();
    public String message = "";

    /**
     * First need to add order info and then use the order line
     * @param customer_id
     * @param restaurant_id
     * @param gst
     * @param pst
     */
    public void addOrderInfo(int customer_id, int restaurant_id, float gst, float pst)
    {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String addOrderInfo = "INSERT INTO orders(customer_id,restaurant_id,pst_tax,gst_tax, order_date, order_time) VALUES (?,?,?,?,?,?)";
                PreparedStatement ppStmt = connection.coon.prepareStatement(addOrderInfo);
                ppStmt.setInt(1,customer_id);
                ppStmt.setInt(2,restaurant_id);
                ppStmt.setFloat(3,gst);
                ppStmt.setFloat(4,pst);
                ppStmt.setDate(5,Date.valueOf(date));
                ppStmt.setTime(6,Time.valueOf(time));
                ppStmt.executeQuery();
                if(ppStmt.executeUpdate()>0)
                {
                    message = "add order information";
                    connection.coon.close();
                }
                else
                {
                    message = "Did not saved";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                e.fillInStackTrace();
            }
        }

    }

    /**
     * update the price and discount when the order line complete, and set up the pp-time from restaurants
     * @param orderId
     * @param ppTime
     */
    public void updateOrderInfo(int orderId, Time ppTime)
    {
        connection.connect();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        if(connection.coon!=null)
        {
            try{
                String search_Query="SELECT price_total, discount_total from order_lines where order_id = ?";
                PreparedStatement searchSubtotal = connection.coon.prepareStatement(search_Query);
                float sub_total = 0;
                float discount_total=0;

                searchSubtotal.setInt(1,orderId);
                ResultSet resultSet1 = searchSubtotal.executeQuery();
                while(resultSet1.next())
                {
                    sub_total+= resultSet1.getFloat(1);
                    discount_total+=resultSet1.getFloat(2);
                }
                System.out.println(discount_total);
                String update_Query = "UPDATE orders set subtotal = ?, total_discount = ?, order_date = ?, order_time = ?," +
                        "estimate_preparation_time = ? WHERE id = "+orderId;
                PreparedStatement goUpdate = connection.coon.prepareStatement(update_Query);
                goUpdate.setFloat(1,sub_total);
                goUpdate.setFloat(2,discount_total);
                goUpdate.setDate(3,Date.valueOf(date));
                goUpdate.setTime(4,Time.valueOf(time));
                goUpdate.setTime(5,ppTime);
                int value = goUpdate.executeUpdate();
                if(value>0)
                {
                    message = "The order information has been updated";
                    connection.coon.close();
                }
                else {
                    message = "The order information has no changed";
                    connection.coon.close();
                }


            }catch (SQLException e)
            {
                e.fillInStackTrace();
            }
        }
    }

    /**
     * Testing function
     * @param arg
     */
    public static void main(String [] arg)
    {
        AddOrders addOrders = new AddOrders();
        Time A = Time.valueOf("00:20:00");
        addOrders.updateOrderInfo(1,A);
        System.out.println(addOrders.message);
    }
}
