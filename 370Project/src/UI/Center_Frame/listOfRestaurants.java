package UI.Center_Frame;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Yuecheng Rong
 */

public class listOfRestaurants {

    public VBox[] lists;
    public VBox[] listOfRate;
    public VBox[] listOfAddress;

    String cssLayout = "-fx-border-color: black;" +
            "-fx-border-insets: 3;" +
            "-fx-border-width: 1;" +
            "-fx-border-style: solid;";

    public void RestaurantList(ArrayList<ArrayList<String>> result) {

        lists = new VBox[result.size()];
        listOfRate = new VBox[result.size()];
        listOfAddress = new VBox[result.size()];

        if(result.isEmpty()){
            return;
        }

        // If SearchByDish
        if(result.get(0).size() == 9) {
            for (int i = 0; i < result.size(); i++) {
                VBox Temp = new VBox();
                Temp.setPrefSize(260, 110);
                Temp.setStyle(cssLayout);

                Label Temp_up = new Label("Restaurant: " + result.get(i).get(0));
                Label Temp_middle = new Label("Price: " + result.get(i).get(7));
                Label Temp_down = new Label("Dish Name: " + result.get(i).get(8));

                Temp_up.setPrefSize(260, 36);
                Temp_middle.setPrefSize(260, 36);
                Temp_down.setPrefSize(260, 36);

                Temp.getChildren().addAll(Temp_up, Temp_middle, Temp_down);

                lists[i] = Temp;
            }









            // Else, search by restaurant
        } else if (result.get(0).size() == 7 || result.get(0).size() == 5){
            for (int i = 0; i < result.size(); i++) {
                VBox Temp = new VBox();
                Temp.setPrefSize(260, 110);
                Temp.setStyle(cssLayout);

                Label Temp_up = new Label("Restaurant: " + result.get(i).get(0));

                Temp_up.setPrefSize(260, 110);

                Temp.getChildren().addAll(Temp_up);

                lists[i] = Temp;
            }
        }

        for (int i = 0; i < result.size(); i++){
            VBox Temp = new VBox();
            Temp.setPrefSize(260,110);
            Temp.setStyle(cssLayout);

            Label Temp_up = new Label("Rate: " + result.get(i).get(1));
            Label Temp_down = new Label("Waiting time: " + result.get(i).get(2));

            Temp_up.setPrefSize(260,55);
            Temp_down.setPrefSize(260,55);

            Temp.getChildren().addAll(Temp_up,Temp_down);

            listOfRate[i] = Temp;
        }

        for (int i = 0; i < result.size(); i++){
            VBox Temp = new VBox();
            Temp.setPrefSize(260,110);
            Temp.setStyle(cssLayout);

            // Distance
            Label Temp_up = new Label("Distance: " + result.get(i).get(4));
            // Address
            Label Temp_down = new Label(result.get(i).get(3));

            Temp_up.setPrefSize(260,110);
            Temp_down.setPrefSize(260,55);

            Temp.getChildren().addAll(Temp_up,Temp_down);

            listOfAddress[i] = Temp;
        }

    }
}
