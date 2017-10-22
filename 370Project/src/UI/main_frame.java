package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class main_frame extends Application {

    Stage windows = new Stage();
    BorderPane MainPane = new BorderPane();
    VBox left = new VBox();
    Pane blank = new Pane();
    Button checkout = new Button("CHECK OUT");




    @Override
    public void start(Stage primaryStage) throws Exception{
        user_account user_account = new user_account();
        search search = new search();
        sort sort = new sort();
        count count = new count();
        primary_button page = new primary_button();





        // set up pane, scene and stage
        checkout.setPrefSize(180,60);
        checkout.getStylesheets().add("css/checkout.css");
        windows = primaryStage;
        left.setPrefHeight(400);
        left.setPrefWidth(180);
        blank.setPrefSize(180,80);
        blank.setStyle("-fx-background-image: url(/pictures/log.png)");
        left.setStyle("-fx-background-image: url(/pictures/background.jpg)");
        left.setPadding(new Insets(10,5,10,5));
        MainPane.setPrefHeight(500);
        MainPane.setPrefWidth(800);

        // add children
        left.getChildren().addAll(blank,user_account,search,sort,count,checkout,page);
        MainPane.setLeft(left);
        Scene scene = new Scene(MainPane);
        //scene.getStylesheets().add("css/page.css");
        windows.setTitle("Food Ordering System");
        windows.setResizable(false);
        windows.setScene(scene);
        windows.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}
