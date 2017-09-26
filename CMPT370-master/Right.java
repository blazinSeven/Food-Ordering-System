package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class Right extends BorderPane {

    public Right(){
        setTop(top());
        setCenter(centre());
        setBottom(bottom());
    }


    private VBox top(){
        VBox top = new VBox();
        HBox a = new HBox();
        HBox b = new HBox();
        TextField search = new TextField();
        Label search_label = new Label();
        search_label.setText("Search: ");
        Button sign_in = new Button();
        Button Log_in = new Button();
        sign_in.setText("Sign in");
        Log_in.setText("Log in");
        a.getChildren().addAll(search_label,search);
        b.getChildren().addAll(sign_in,Log_in);
        b.setAlignment(Pos.CENTER);
        top.getChildren().addAll(a,b);
        top.setMaxSize(220,200);
        return top;
    }

    private StackPane centre() {
        StackPane centre = new StackPane();
        Label ad = new Label();
        ad.setText("This part will show some commercial advertisements to user");
        ad.setWrapText(true);
        centre.getChildren().add(ad);
        centre.setAlignment(Pos.CENTER);
        centre.setMaxSize(220,600);
        return centre;
    }

    private HBox bottom() {
        HBox bottom = new HBox();
        Button Quit = new Button();
        Quit.setText("      Quit        ");
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.getChildren().addAll(Quit);
        bottom.setMaxSize(220,200);
        return bottom;
    }
}
