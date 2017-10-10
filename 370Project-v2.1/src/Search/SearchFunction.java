package Search;

//import Interface.Main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFunction {

    private boolean ans = false;

    public ArrayList<Food> ListFood = new ArrayList<>();

    public class Food{
        public String RestaurantName;
        public String FoodName;
        public int rate;
        public double price;
    }

    /*

    private Main M = new Main();

    public void searchFunction(String D) {
        ListFood.clear();
        //centre.resetAll();
        if (!Search(D)) {
            //JOptionPane.showMessageDialog(null,"Sorry we can't find that kind of food in our system >_<");
            System.out.println("Sorry we can't find that kind of food in our system >_<");
        } else {
            for (int i = 0; i < ListFood.size(); i++) {
                M.changeText(ListFood.get(i),M.ListOfTextArea.get(i));
            }
        }
    }
    */


    public boolean Search(String D){
        ans = false;

        menu m = new menu();
        //ArrayList<menu.Restaurant> L = new ArrayList<>();

        searchHelper(m.r1, D);
        searchHelper(m.r2, D);
        searchHelper(m.r3, D);
        searchHelper(m.r4, D);
        searchHelper(m.r5, D);
        searchHelper(m.r6, D);
        searchHelper(m.r7, D);
        searchHelper(m.r8, D);
        searchHelper(m.r9, D);
        searchHelper(m.r10, D);

        return ans;
    }

    public void searchHelper (menu.Restaurant R, String D) {

        Pattern P = Pattern.compile(D.toLowerCase());

        Helperhelper(R,R.a,D,P);
        Helperhelper(R,R.b,D,P);
        Helperhelper(R,R.c,D,P);
        Helperhelper(R,R.d,D,P);

    }


    private void Helperhelper(menu.Restaurant R, menu.Restaurant.Food F, String D, Pattern P){

        Matcher M = P.matcher(F.name.toLowerCase());

        if (M.find()){

            ans = true;

            Food temp = new Food();

            temp.RestaurantName = R.name;
            temp.FoodName = F.name;
            temp.price = F.price;
            temp.rate = F.rate;

            ListFood.add(temp);
        }


    }

}
