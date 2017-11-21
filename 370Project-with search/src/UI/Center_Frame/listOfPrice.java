package UI.Center_Frame;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Yuecheng Rong
 */

public class listOfPrice {

    public VBox[] lists;

    public void listOfThePrice(ArrayList<ArrayList<String>> result){

        lists = new VBox[result.size()];

        String cssLayout = "-fx-border-color: black;" +
                "-fx-border-insets: 3;" +
                "-fx-border-width: 1;" +
                "-fx-border-style: solid;";

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

    }

}
