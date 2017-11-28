package Sort;

import database.GoConnection;
import google.GetDistance;


import java.sql.*;
import java.util.ArrayList;

/**
 * Yinsheng Dong Nov 21
 */

public class SortByWaitingTimeWithLogin
{
    private ArrayList<SortInfo> sortInfos;
    public String message = "";
    private String name = "";
    private String address = "";
    private int id = 0;
    private float rate = 0;
    private Time wait_time = null;
    private float distance = 0;
    private float avg_price = 0;

    public ArrayList<SortInfo> getSortWaitingTime() {
        return sortInfos;
    }

    public void sortByWaitingTime(int customer_id)
    {
        sortInfos = new ArrayList<>();
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try
            {
                String query = "select\n" +
                        "r.id AS rest_id,\n" +
                        "r.restaurant_name AS rest_name,\n" +
                        "l.house_num AS rest_house_num,\n" +
                        "l.street AS rest_street,\n" +
                        "l.city AS rest_city,\n" +
                        "l.province AS rest_province,\n"+
                        "r.waiting_time AS rest_wait_time,\n" +
                        "rv.stars AS rest_review,\n" +
                        "r.avg_price AS rest_avg_price\n"+
                        "from restaurants r\n" +
                        "left join locations l on r.user_id = l.user_id\n" +
                        "left join reviews rv on rv.restaurant_id = r.id\n" +
                        "GROUP BY rest_id,rest_name, rest_house_num, rest_street, rest_city, rest_province, rest_wait_time,\n" +
                        "rest_review, rest_avg_price\n" +
                        "ORDER BY r.waiting_time";
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    id = resultSet.getInt(1);
                    name = resultSet.getString(2);
                    address = resultSet.getInt(3)+" "+
                              resultSet.getString(4)+" "+
                              resultSet.getString(5)+ " "+
                              resultSet.getString(6);
                    wait_time = resultSet.getTime(7);
                    rate = resultSet.getFloat(8);
                    avg_price = resultSet.getFloat(9);
                    SortInfo sortInfo = new SortInfo(id,name,address,wait_time,rate,avg_price);
                    sortInfos.add(sortInfo);

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

                for(int i = 0; i<sortInfos.size(); i++)
                {
                    distance = getDistance.getFloatDistance(getCustomerAddress,sortInfos.get(i).getRest_address());
                    sortInfos.get(i).setDistance(distance);
                }

            }
            catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }


    }

    public SortByWaitingTimeWithLogin(int customer_id)
    {
        sortByWaitingTime(customer_id);
    }


    public static void main(String arg[])
    {
        SortByWaitingTimeWithLogin sortByWaitingTime = new SortByWaitingTimeWithLogin(19);
        System.out.println(sortByWaitingTime.getSortWaitingTime());

    }

}
