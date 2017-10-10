package Interface;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Left extends Pane {

    public TextArea info_restaurant = new TextArea();

    public Left(){
        getChildren().add(info());
        setMinSize(200,500);
    }

    private VBox info() {
        VBox info = new VBox();
        info_restaurant.setText("This part will show the specific information about restaurant which the user have selected");
        info_restaurant.setWrapText(true);
        info_restaurant.setEditable(false);
        info_restaurant.setMaxSize(200,400);
        TextArea prepare_time = new TextArea();
        prepare_time.setText("This part will show the food prepare time");
        prepare_time.setWrapText(true);
        prepare_time.setEditable(false);
        prepare_time.setMaxSize(200,400);
        info.getChildren().addAll(info_restaurant,prepare_time);
        info.setMinSize(200,500);
        return info;
    }
}
