package UI.Center_Frame;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Yuecheng Rong
 */

public class listOfPrice {

    public VBox[] lists;
    public VBox[] listsOfRate;
    String cssLayout = "-fx-border-color: black;" +
            "-fx-border-insets: 3;" +
            "-fx-border-width: 1;" +
            "-fx-border-style: solid;";

    public void listOfThePrice(ArrayList<ArrayList<String>> result){

        lists = new VBox[result.size()];
        listsOfRate = new VBox[result.size()];


        for (int i = 0; i < result.size(); i++){
            VBox Temp = new VBox();
            Temp.setPrefSize(260,110);
            Temp.setStyle(cssLayout);

            Label Temp_up = new Label("Restaurant: " + result.get(i).get(0));
            Label Temp_middle = new Label("Price: " + result.get(i).get(1));
            Label Temp_down = new Label("Dish Name: " + result.get(i).get(2));

            Temp_up.setPrefSize(260,36);
            Temp_middle.setPrefSize(260,36);
            Temp_down.setPrefSize(260,36);

            Temp.getChildren().addAll(Temp_up,Temp_middle,Temp_down);

            lists[i] = Temp;
        }

        /**
         * Should appear at middle
         */
        for (int i = 0; i < result.size(); i++){
            VBox Temp = new VBox();
            Temp.setPrefSize(260,110);
            Temp.setStyle(cssLayout);
            Label Temp_up = new Label("Rate: " + result.get(i).get(4));
            Label Temp_down = new Label("Waiting time: " + result.get(i).get(5));

            Temp_up.setPrefSize(260,55);
            Temp_down.setPrefSize(260,55);

            Temp.getChildren().addAll(Temp_up,Temp_down);

            listsOfRate[i] = Temp;
        }

    }

    public void listOfRate(ArrayList<Integer> rate, ArrayList<Long> waiting_time){
        listsOfRate = new VBox[rate.size()];
        for (int i = 0; i < rate.size(); i++){
            VBox Temp = new VBox();
            Temp.setPrefSize(260,110);
            Temp.setStyle(cssLayout);

            Label Temp_up = new Label("Rate: " + rate.get(i));
            Label Temp_down = new Label("Waiting time: " + waiting_time.get(i));

            Temp_up.setPrefSize(260,55);
            Temp_down.setPrefSize(260,55);

            Temp.getChildren().addAll(Temp_up,Temp_down);

            listsOfRate[i] = Temp;
        }
    }

}
