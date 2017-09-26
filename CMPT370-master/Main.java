package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    Stage windows = new Stage();




    @Override
    public void start(Stage primaryStage) throws Exception{
        windows = primaryStage;
        Right right = new Right();
        Centre centre = new Centre();
        Left left = new Left();
        BorderPane MainPane = new BorderPane();
        MainPane.setMinSize(800,500);
        MainPane.setRight(right);
        MainPane.setCenter(centre);
        MainPane.setLeft(left);
        Scene scene = new Scene(MainPane);
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
