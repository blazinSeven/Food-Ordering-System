package Search;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ttt {


    private StackPane centre() {
        StackPane centre = new StackPane();

        RadioButton rb1 = new RadioButton();
        rb1.setText("Home");

        centre.getChildren().add(rb1);
        centre.setAlignment(Pos.CENTER);

        return centre;
    }

    public static void main(String[] args){

        ArrayList arr = new ArrayList();
        arr.add(1);
        arr.add(4);
        arr.add(6);
        arr.add(333);
        arr.add(8);
        arr.add(2);

        Collections.sort(arr);

        for(int i=0;i<arr.size();i++){
            System.out.println(arr.get(i));
        }



    }


}
