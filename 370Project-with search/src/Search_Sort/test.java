package Search_Sort;

import java.util.ArrayList;

public class test {

    public static void main(String argcs[]){
        ArrayList<Integer> k = new ArrayList<>();
        k.add(13);
        k.add(14);
        k.add(12);
        k.add(13);

        String Query = "select restaurant_id from menus" + " ORDER BY case menu_id";

        for (int i=0; i<k.size();i++){
            Query += " when " + k.get(i) + " then " + (i+1);
        }
        Query += " end LIMIT " + k.size();


        System.out.println(Query);


        System.out.println("=====================================");

        long a = 4000000000L;

        System.out.println(Integer.parseInt("-061234567"));







    }



}
