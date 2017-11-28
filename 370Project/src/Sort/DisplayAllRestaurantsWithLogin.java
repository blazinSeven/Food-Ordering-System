package Sort;

import database.GoConnection;
import google.GetDistance;

import java.sql.*;
import java.util.ArrayList;

/**
 * Create by Yinsheng Dong Nov 27
 */
public class DisplayAllRestaurantsWithLogin {

    private ArrayList<SortInfo> sortInfoes;
    public String message = "";
    private float distance = 0;
    private int rest_id = 0;
    private String rest_name = "";
    private String rest_address = "";
    private Time rest_time = null;
    private float rest_rate = 0;
    private float rest_avg_price = 0;

    public ArrayList<SortInfo> getAllRestaurants() {
        return sortInfoes;
    }

    private void displayAllRestaurant(int customer_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        sortInfoes = new ArrayList<>();
        if(connection.coon!=null)
        {
            try{
                String query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city,l.province, r.waiting_time, rv.stars, r.avg_price\n" +
                        "from restaurants r\n" +
                        "left join locations l on l.user_id = r.user_id\n" +
                        "left join reviews rv on rv.restaurant_id = r.id\n" +
                        "WHERE l.city = (SELECT lc.city from locations lc, customers c where lc.user_id = (SELECT c.user_id where c.id = ?))\n" +
                        "AND l.province = (SELECT lc.province from locations lc, customers c where lc.user_id = (SELECT c.user_id where c.id = ?))";
                PreparedStatement statement = connection.coon.prepareStatement(query);
                statement.setInt(1,customer_id);
                statement.setInt(2,customer_id);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next())
                {
                    rest_id = resultSet.getInt(1);
                    rest_name = resultSet.getString(2);
                    rest_address = resultSet.getInt(3)+" "+
                                   resultSet.getString(4)+" "+
                                   resultSet.getString(5)+" "+
                                   resultSet.getString(6);
                    rest_time = resultSet.getTime(7);
                    rest_rate = resultSet.getFloat(8);
                    rest_avg_price = resultSet.getFloat(9);
                    SortInfo sortInfo = new SortInfo(rest_id,rest_name,rest_address,rest_time,rest_rate,rest_avg_price);
                    sortInfoes.add(sortInfo);

                }
                GetDistance getDistance = new GetDistance();
                String getCustomerAddress = "";
                String query1= "select l.house_num, l.street, l.city, l.province from locations l, customers c\n" +
                        "where l.user_id = (select c.user_id where c.id = ?)";
                PreparedStatement ppsmt = connection.coon.prepareStatement(query1);
                ppsmt.setInt(1,customer_id);
                ResultSet resultSet1 = ppsmt.executeQuery();
                if(resultSet1.next())
                {
                    getCustomerAddress = resultSet1.getInt(1)+resultSet1.getString(2)+
                            resultSet1.getString(3)+resultSet1.getString(4);
                }
                else
                {
                    message = "not found this customer";
                }
                connection.coon.close();

                for(int i = 0; i<sortInfoes.size(); i++)
                {
                    distance = getDistance.getFloatDistance(getCustomerAddress,sortInfoes.get(i).getRest_address());
                    sortInfoes.get(i).setDistance(distance);
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }

    public DisplayAllRestaurantsWithLogin(int customer_id)
    {
        displayAllRestaurant(customer_id);
    }

    public static void main(String arg[])
    {
        DisplayAllRestaurantsWithLogin displayAllRestaurantsWithLogin = new DisplayAllRestaurantsWithLogin(1);
        System.out.println(displayAllRestaurantsWithLogin.getAllRestaurants());
    }
}
