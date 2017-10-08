package Search;


import javafx.css.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ttt {



    public static void main(String[] args){


    String haha = "Deep Fried Chicken";
    String D = "Deep";

    Pattern P = Pattern.compile(D.toLowerCase());
    Matcher M = P.matcher(haha.toLowerCase());
    if (M.find()){
        System.out.println("caonima");
    }



    }


}
