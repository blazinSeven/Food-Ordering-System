package UI;

import UI.Account.user_account;
import UI.Center_Frame.c_main_hbox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Create By Hao Li at Oct. 15th
 */

public class main_frame extends Application {

    Stage windows = new Stage();
    BorderPane MainPane = new BorderPane();
    VBox left = new VBox();
    StackPane blank = new StackPane();
    Button checkout = new Button("CHECK OUT");




    @Override
    public void start(Stage primaryStage) throws Exception{
        ImageView logo = new ImageView(new Image("/pictures/log.png"));
        logo.setFitHeight(80);
        logo.setFitWidth(160);
        user_account user_account = new user_account();
        //search search = new search();
        sort sort = new sort();
        count count = new count();
        c_main_hbox center = new c_main_hbox();





        // set up pane, scene and stage
        checkout.setPrefSize(180,60);
        checkout.getStylesheets().add("css/checkout.css");
        windows = primaryStage;
        left.setPrefHeight(400);
        left.setPrefWidth(180);
        blank.setPrefSize(180,80);
        blank.getChildren().add(logo);
        left.setStyle("-fx-background-image: url(/pictures/background.jpg)");
        left.setPadding(new Insets(10,5,10,5));
        MainPane.setPrefHeight(500);
        MainPane.setPrefWidth(800);

        // add children
        left.getChildren().addAll(blank,user_account,center.search(),sort,count,checkout,center.main_Button());
        MainPane.setLeft(left);
        MainPane.setCenter(center);
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
