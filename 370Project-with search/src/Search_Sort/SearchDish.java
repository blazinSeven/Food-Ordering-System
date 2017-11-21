package Search_Sort;

import java.sql.SQLException;
import java.util.ArrayList;

import database.GoConnection;

public class SearchDish {
    GoConnection connection = new GoConnection();
    ExecuteNoInput eni = new ExecuteNoInput();
    ExecuteWithInput e = new ExecuteWithInput();
    String Query;

    // Following six arraylists contain detailed information of the dish been searched
    // Restaurant name appears at the first space
    ArrayList<String> restaurantName = new ArrayList<>();
    // Dish price appears at the second space
    ArrayList<Integer> price = new ArrayList<>();
    // Complete dish name appears at the third space
    ArrayList<String> searchResult = new ArrayList<>();
    // Restaurant id appears at the forth space
    ArrayList<Integer> k = new ArrayList<>();
    // Restaurant rate appears at the forth space. Since not added yet, use "user_id" instead
    ArrayList<String> restaurantRate = new ArrayList<>();
    // Restaurant waiting time appears at the forth space. Since not added yet, use "phone_num" instead
    ArrayList<String> restaurantWT = new ArrayList<>();

    // The super array that contains all the information
    // first space: restaurant name
    // second space: dish price
    // third space: complete dish name
    // forth space: restaurant id
    // fifth space: restaurant rate. Since not added yet, use "user_id" instead
    // sixth space: restaurant waiting time. Since not added yet, use "phone_num" instead
    public ArrayList<ArrayList<String>> finalResult = new ArrayList<>();

    public void Search(String search)
    {
        connection.connect();
        k.clear();
        price.clear();
        restaurantName.clear();
        searchResult.clear();
        finalResult.clear();
        Query = null;
        // If input no dish name, then sort cannot be done
        if(search.equals("")|| search.equals(null))
        {
            /**
             * Panel should appear instead of stdout
             */
            System.out.println("Cannot search the empty input");
            try{
                connection.coon.close();
            }catch (SQLException e)
            {
                e.getStackTrace();
            }
        }
        else if (connection.coon!=null){

            search = "%" + search.toLowerCase() + "%";

            Query = "select menus_id from dishes where LOWER(dish_name) like ? ";
            k = e.execute(Query,connection,search,"menus_id");

            /**
             * generate the restaurant_id
             */
            if(k.size() > 0) {
                Query = "select restaurant_id from menus where menu_id in (";
                for (int i = 0; i < k.size() - 1; i++) {
                    Query += k.get(i) + ", ";
                }
                Query += k.get(k.size()-1) + ") LIMIT " + k.size();
                k.clear();
            } else {    // no result
                System.out.println("Nothing can be found");
                return;
            }


            // Since changed to different table, connect again
            //connection.connect();
            k = eni.execute(Query,connection,"restaurant_id");

            /**
             * generate the restaurant_name
             * now Array "restaurantName" = restaurant_name
             */
            Query = "select * from restaurants where id in (";
            for (int i=0; i<k.size()-1;i++){
                Query += k.get(i) + ", ";
            }
            Query += k.get(k.size()-1) + ") LIMIT " + k.size();
            restaurantName = eni.executeName(Query,connection,"restaurant_name");
            restaurantRate = eni.executeName(Query,connection,"user_id");
            restaurantWT = eni.executeName(Query,connection,"phone_num");


            /**
             * generate the price and the complete dish name
             */
            Query = "select * from dishes where LOWER(dish_name) like ?";
            searchResult = e.getName(Query,connection,search,"dish_name");
            price = e.execute(Query,connection,search,"dish_prices");
            // Create the list that contains the result
            for (int i=0;i<price.size();i++){
                ArrayList<String> result = new ArrayList<>();
                // add restaurant name, price and complete dish name respectively into list "result"
                result.add(restaurantName.get(i));
                result.add(price.get(i).toString());
                result.add(searchResult.get(i));
                // add restaurant id, restaurant rate and restaurant waiting time respectively into list "result"
                result.add(k.get(i).toString());
                result.add(restaurantRate.get(i));
                result.add(restaurantWT.get(i));
                // add "result" into "final result"
                finalResult.add(result);
            }



            for (int i=0;i<searchResult.size();i++){
                System.out.print(searchResult.get(i) + " ");
            }
            System.out.println();

            for (int i=0;i<searchResult.size();i++){
                System.out.print(price.get(i) + " ");
            }
            System.out.println();

            System.out.println("-------------------------------");

            for (int i=0;i<finalResult.size();i++){
                System.out.println("next: ");
                System.out.println("Restaurant name: " + finalResult.get(i).get(0));
                System.out.println("Price: " + finalResult.get(i).get(1));
                System.out.println("Dish name: " + finalResult.get(i).get(2));
                System.out.println("rest_id: " + finalResult.get(i).get(3));
                System.out.println("user_jd: " + finalResult.get(i).get(4));
                System.out.println("phone_num: " + finalResult.get(i).get(5));
                System.out.println();
            }
            System.out.println();


            // Problem: if more than one dish fits the result under the same restaurant, corrupt
            // E.g. KFC has oneBurger and twoBurger, and search "burger"


        }
        /*
        print();
        for (int i=0;i<restaurantName.size();i++){
            System.out.print(restaurantName.get(i) + " ");
        }
        System.out.println();
        */
    }

    public static void main(String[] args)
    {
        SearchDish SD = new SearchDish();
        SD.Search("rie");
    }
}
