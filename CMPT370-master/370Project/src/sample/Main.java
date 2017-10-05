package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Stage windows = new Stage();




    @Override
    public void start(Stage primaryStage) throws Exception{
        windows = primaryStage;
        Right right = new Right();
        Centre centre = new Centre();
        Left left = new Left();
        right.Quit.setOnAction(e->windows.close());
        BorderPane MainPane = new BorderPane();
        MainPane.setMinSize(800,500);
        MainPane.setRight(right);
        MainPane.setCenter(centre);
        MainPane.setLeft(left);
        /*
        //set background fill
        BackgroundFill myBF = new BackgroundFill(Color.rgb(250,235,215), new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        //then you set to your node or container or layout
        MainPane.setBackground(new Background(myBF));
        */
        Scene scene = new Scene(MainPane, Color.BLACK);
        scene.getStylesheets().add("sample/main_style.css");
        windows.setTitle("Food Ordering System");
        windows.setMinHeight(500);
        windows.setMinWidth(800);
        windows.setResizable(false);
        windows.setScene(scene);
        windows.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
