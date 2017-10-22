package UI;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class primary_button extends HBox{
    Button pre = new Button("Previous");
    Button next = new Button("Next");
    public primary_button(){
        getStylesheets().add("css/page.css");
        set_button();
        getChildren().addAll(pre,next);
    }

    private void set_button(){
        pre.setPrefSize(90,30);
        next.setPrefSize(90,30);
    }
}
