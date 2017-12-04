package UI.Center_Frame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Stack;

public class menus_item extends HBox {
    Label item_name;
    Button item_order;
    menus_item(Label label, Button button){
        item_name = label;
        item_order = button;
        getChildren().addAll(item_name,item_order);
    }

    void setLabel(Label label){
        item_name = label;
    }

    void setButton(Button button){
        item_order = button;
    }

    Label getLabel(){
        return item_name;
    }

    Button getButton(){
        return item_order;
    }
}
