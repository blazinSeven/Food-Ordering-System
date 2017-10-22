package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class search extends VBox {
    TextField search_field = new TextField();
    Label blank = new Label("                           ");
    Button search = new Button("SEARCH");

    public search(){
        getChildren().addAll(search_field,hold());
        setPrefSize(100,60);
    }

    private HBox hold(){
        HBox h = new HBox();
        h.getChildren().addAll(blank,search);
        return h;
    }



}
