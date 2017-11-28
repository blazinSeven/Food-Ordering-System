package Sort;

import database.GoConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import google.GetDistance;

/**
 * Create by Yinsheng Dong, Nov 27
 */
public class SortByPriceWithLogin {

    private ArrayList<SortInfo> sortInfos;
    public String message = "";
    private String name = "";
    private String address = "";
    private int id = 0;
    private float rate = 0;
    private Time wait_time = null;
    private float distance = 0;
    private float avg_price = 0;


    public ArrayList<SortInfo> getSortByPrice() {
        return sortInfos;
    }


    public void sortByPrice(int customer_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null) {
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
                        "r.avg_price \n"+
                        "from restaurants r\n" +
                        "LEFT JOIN locations l ON  r.user_id = l.user_id\n" +
                        "LEFT JOIN reviews rv ON  rv.restaurant_id = r.id";
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                sortInfos = new ArrayList<>();
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
                Collections.sort(sortInfos,SortInfo.priceComparator);

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }

        }
    }

    public SortByPriceWithLogin(int customer_id)
    {
        sortByPrice(customer_id);
    }


    public static void main(String arg[])
    {
        SortByPriceWithLogin sortByPrice = new SortByPriceWithLogin(19);
        System.out.println(sortByPrice.getSortByPrice());
    }

}
