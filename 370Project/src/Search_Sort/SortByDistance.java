package Search_Sort;

import database.GoConnection;
import database.NearbyRestaurant;
import google.GetDistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SortByDistance {
    ArrayList<String> SortByDistanceName;
    ArrayList<Integer> SortByDistanceId;
    ArrayList<String > SortByDistanceAddress;
    ArrayList<Float> SortByDistanceRate;

    public ArrayList<String> sortByDistanceName;
    public ArrayList<Integer> sortByDistanceId;
    public ArrayList<String > sortByDistanceAddress;
    public ArrayList<Float> sortByDistanceRate;

    NearbyRestaurant nearbyRestaurant = new NearbyRestaurant();
    ArrayList<DistanceItem> sort = new ArrayList<>();
    public String message = "";
    private String name = "";
    private String address = "";
    private int id = 0;
    private float rate = 0;
    boolean flag = true;

    public SortByDistance(int s_id){
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                SortByDistanceAddress = new ArrayList<>();
                SortByDistanceId = new ArrayList<>();
                SortByDistanceName = new ArrayList<>();
                SortByDistanceRate = new ArrayList<>();
                String query = "select r.id, r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars from restaurants r, locations l, reviews rv WHERE r.user_id = l.user_id AND rv.restaurant_id = r.id GROUP BY r.id,r.restaurant_name, l.house_num, l.street, l.city, l.province, rv.stars ORDER BY r.id";
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    name = resultSet.getString(2);
                    id = resultSet.getInt(1);
                    address = resultSet.getInt(3)+" "+
                            resultSet.getString(4)+" "+
                            resultSet.getString(5)+ " "+
                            resultSet.getString(6);
                    rate = resultSet.getFloat(7);
                    SortByDistanceAddress.add(address);
                    SortByDistanceId.add(id);
                    SortByDistanceName.add(name);
                    SortByDistanceRate.add(rate);

                }
                connection.coon.close();

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        sortbydistance(s_id);
    }

    private void sortbydistance(int c_id){
        sortByDistanceAddress = new ArrayList<>();
        sortByDistanceId = new ArrayList<>();
        sortByDistanceName = new ArrayList<>();
        sortByDistanceRate = new ArrayList<>();
        nearbyRestaurant.findRestaurantSameCity(c_id);
        nearbyRestaurant.getCustomerAddress(c_id);
        nearbyRestaurant.getRestaurantAddress(SortByDistanceId);
        GetDistance getDistance =new GetDistance();
        for (int i=0;i<nearbyRestaurant.addressArray.size();i++){
            DistanceItem k = new DistanceItem(Integer.parseInt(nearbyRestaurant.addressArray.get(i).get(2)),getDistance.getFloatDistance(nearbyRestaurant.customerAddress,nearbyRestaurant.addressArray.get(i).get(1)));
            sort.add(k);
        }

        while (flag){
            flag = false;
            for (int i=1;i<sort.size();i++){
                if (sort.get(i).getLength()-sort.get(i-1).getLength()>0){
                    DistanceItem temp = new DistanceItem(sort.get(i).getId(),sort.get(i).getLength());
                    sort.get(i).setId(sort.get(i-1).getId());
                    sort.get(i).setLength(sort.get(i-1).getLength());
                    sort.get(i-1).setId(temp.getId());
                    sort.get(i-1).setLength(temp.getLength());
                    flag = true;
                }
            }
        }

        for (int i=0;i<sort.size();i++){
            for (int m = 0; m < SortByDistanceId.size(); m++) {
                if (SortByDistanceId.get(m) == sort.get(i).getId()){
                    sortByDistanceId.add(SortByDistanceId.get(m));
                    sortByDistanceAddress.add(SortByDistanceAddress.get(m));
                    sortByDistanceName.add(SortByDistanceName.get(m));
                    sortByDistanceRate.add(SortByDistanceRate.get(m));
                    break;

                }
            }
        }

        for (int k =0;k<SortByDistanceId.size();k++){
            if (!sortByDistanceId.contains(SortByDistanceId.get(k))){
                sortByDistanceId.add(SortByDistanceId.get(k));
                sortByDistanceAddress.add(SortByDistanceAddress.get(k));
                sortByDistanceName.add(SortByDistanceName.get(k));
                sortByDistanceRate.add(SortByDistanceRate.get(k));
            }

        }

    }






    public static void main(String arg[])
    {
        SortByDistance sortByDistance = new SortByDistance(19);
        System.out.println(sortByDistance.sortByDistanceId.size());
        for (int i=0;i<sortByDistance.sortByDistanceId.size();i++){
            System.out.println(sortByDistance.sortByDistanceId.get(i));
            System.out.println(sortByDistance.sortByDistanceName.get(i));
            System.out.println(sortByDistance.sortByDistanceAddress.get(i));
            System.out.println(sortByDistance.sortByDistanceRate.get(i));
        }



    }
}

