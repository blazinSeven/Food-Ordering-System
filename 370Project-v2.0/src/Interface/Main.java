package Interface;

import Search.SearchFunction;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class Main extends Application {

    Stage windows = new Stage();
    Centre centre = new Centre();
    Right right = new Right();
    Left left = new Left();

    SearchFunction sf = new SearchFunction();
    public ArrayList<TextArea> ListOfTextArea = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        windows = primaryStage;
        right.Quit.setOnAction(e -> windows.close());
        BorderPane MainPane = new BorderPane();
        MainPane.setMinSize(800, 500);
        MainPane.setRight(right);
        MainPane.setCenter(centre);
        MainPane.setLeft(left);

        ListOfTextArea.add(centre.R1);
        ListOfTextArea.add(centre.R2);
        ListOfTextArea.add(centre.R3);
        ListOfTextArea.add(centre.R4);


        right.Go.setOnAction(event -> searchFunction(right.search.getText()));

        /*
        //set background fill
        BackgroundFill myBF = new BackgroundFill(Color.rgb(250,235,215), new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        //then you set to your node or container or layout
        MainPane.setBackground(new Background(myBF));
        */
        Scene scene = new Scene(MainPane, Color.BLACK);
        scene.getStylesheets().add("Interface/main_style.css");
        windows.setTitle("Food Ordering System");
        windows.setMinHeight(500);
        windows.setMinWidth(800);
        windows.setResizable(false);
        windows.setScene(scene);
        windows.show();
    }


    public void searchFunction(String D) {
        sf.ListFood.clear();
        centre.resetAll();

        if (!sf.Search(D)) {
            //JOptionPane.showMessageDialog(null,"Sorry we can't find that kind of food in our system >_<");
            System.out.println("Sorry we can't find that in our system >_<");
        } else {
            for (int i = 0; i < sf.ListFood.size(); i++) {
                changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
            }
        }
    }


    public void changeText(SearchFunction.Food F, TextArea TA) {
        TA.setText("Restaurant: " + F.RestaurantName + "\n" +
                "Food Name: " + F.FoodName + "\n" +
                "Price: " + F.price + "\n" +
                "Rate: " + F.rate + "\n"
        );

    }





    public static void main(String[] args) {
        launch(args);
    }
}


// menu too short