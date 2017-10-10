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

    ArrayList<TextArea> ListOfTextArea = new ArrayList<>();

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
        right.rb1.setOnAction(event -> SortByPrice());
        right.rb2.setOnAction(event -> SortByRate());


        centre.R1.setOnMouseClicked(event -> changeLeft(centre.R1));
        centre.R2.setOnMouseClicked(event -> changeLeft(centre.R2));
        centre.R3.setOnMouseClicked(event -> changeLeft(centre.R3));
        centre.R4.setOnMouseClicked(event -> changeLeft(centre.R4));


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

    public void changeLeft(TextArea T) {

        if (!T.getText().equals("Restaurant 1") &&
                !T.getText().equals("Restaurant 2") &&
                !T.getText().equals("Restaurant 3") &&
                !T.getText().equals("Restaurant 4")) {
                    left.info_restaurant.setText(T.getText());
        }
    }


    public void searchFunction(String D) {
        sf.ListFood.clear();
        centre.resetAll();

        if (!sf.Search(D)) {
            //JOptionPane.showMessageDialog(null,"Sorry we can't find that kind of food in our system >_<");
            System.out.println("Sorry we can't find that in our system >_<");
        } else {
            /*
            for (int i = 0; i < sf.ListFood.size(); i++) {
                changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
            }
            */
            for (int i = 0; i < 4; i++) {
                changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
            }
            // Show the ratio button
            right.changeMiddle();
        }
    }


    public void changeText(SearchFunction.Food F, TextArea TA) {
        TA.setText("Restaurant: " + F.RestaurantName + "\n" +
                "Food Name: " + F.FoodName + "\n" +
                "Price: " + F.price + "\n" +
                "Rate: " + F.rate + "\n"
        );

    }



    // bubble sort lol
    public void SortByPrice(){

        centre.resetAll();
        SearchFunction.Food temp;

        for (int i = 0; i < sf.ListFood.size(); i++){
            for (int j = 0; j < i; j++){
                if (sf.ListFood.get(j+1).price < sf.ListFood.get(j).price){
                    temp = sf.ListFood.get(j);
                    sf.ListFood.set(j, sf.ListFood.get(j + 1));
                    sf.ListFood.set(j + 1, temp);
                }
            }
        }

        /*
        for (int i = 0; i < sf.ListFood.size(); i++) {
            changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
        }
        */



        for (int i = 0; i < 4; i++) {
            changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
        }

    }



    public void SortByRate(){
        centre.resetAll();
        SearchFunction.Food temp;

        for (int i = 0; i < sf.ListFood.size(); i++){
            for (int j = 0; j < i; j++){
                if (sf.ListFood.get(j+1).rate < sf.ListFood.get(j).rate){
                    temp = sf.ListFood.get(j);
                    sf.ListFood.set(j, sf.ListFood.get(j + 1));
                    sf.ListFood.set(j + 1, temp);
                }
            }
        }

        /*
        for (int i = 0; i < sf.ListFood.size(); i++) {
            changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
        }
        */

        for (int i = 0; i < 4; i++) {
            changeText(sf.ListFood.get(i),ListOfTextArea.get(i));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}


// menu too short
// space!!!
// If enter "Hamburger" should "Burger" comes out
// Search restaurant name
// Have to press any button twice