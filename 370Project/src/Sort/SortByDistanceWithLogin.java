package Sort;

import database.GoConnection;
import google.GetDistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class SortByDistanceWithLogin {

    private ArrayList<SortInfo> sortInfoes;
    public String message = "";
    private float distance = 0;
    private int rest_id = 0;
    private String rest_name = "";
    private String rest_address = "";
    private Time rest_time = null;
    private float rest_rate = 0;
    private float rest_avg_price = 0;

    public ArrayList<SortInfo> getDistanceSort() {
        return sortInfoes;
    }


    public void sortByDistance(int customer_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        sortInfoes = new ArrayList<>();
        String getCustomerAddress = "";
        if(connection.coon!=null)
        {
            try {
                String query =
                        "select r.id, \n" +
                        "r.restaurant_name,\n" +
                        "l.house_num, \n" +
                        "l.street, \n" +
                        "l.city,\n" +
                        "l.province,\n" +
                        "r.waiting_time,\n" +
                        "rv.stars, \n" +
                        "r.avg_price\n"+
                        "from restaurants r\n" +
                        "LEFT JOIN locations l ON  r.user_id = l.user_id\n" +
                        "LEFT JOIN reviews rv ON  rv.restaurant_id = r.id \n";
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    rest_id = resultSet.getInt(1);
                    rest_name = resultSet.getString(2);
                    rest_address = resultSet.getInt(3)+" "+
                            resultSet.getString(4)+" "+
                            resultSet.getString(5)+ " "+
                            resultSet.getString(6);
                    rest_time = resultSet.getTime(7);
                    rest_rate = resultSet.getFloat(8);
                    rest_avg_price = resultSet.getFloat(9);
                    SortInfo sortInfo = new SortInfo(rest_id,
                            rest_name,
                            rest_address,
                            rest_time,
                            rest_rate,
                            rest_avg_price);
                    sortInfo.setRest_id(rest_id);
                    sortInfo.setRest_name(rest_name);
                    sortInfo.setRest_address(rest_address);
                    sortInfo.setRest_rate(rest_rate);
                    sortInfo.setRest_time(rest_time);
                    sortInfoes.add(sortInfo);

                }
                GetDistance getDistance = new GetDistance();
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
                Collections.sort(sortInfoes, SortInfo.distanceComparator);


            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }

    public SortByDistanceWithLogin(int customer_id)
    {
        sortByDistance(customer_id);
    }

    public static void main(String arg[])
    {
        SortByDistanceWithLogin sortByDistance = new SortByDistanceWithLogin(19);
        System.out.println(sortByDistance.getDistanceSort());
    }
}
