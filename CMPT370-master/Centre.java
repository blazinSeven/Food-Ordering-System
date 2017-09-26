package sample;


import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Centre extends Pane{

    public Centre(){
        getChildren().add(restaurant());
    }

    private VBox restaurant() {
        VBox show = new VBox();
        TextArea R1 = new TextArea();
        R1.setText("Restaurant 1");
        TextArea R2 = new TextArea();
        TextArea R3 = new TextArea();
        TextArea R4 = new TextArea();
        R2.setText("Restaurant 2");
        R3.setText("Restaurant 3");
        R4.setText("Restaurant 4");
        R1.setWrapText(true);
        R2.setWrapText(true);
        R3.setWrapText(true);
        R4.setWrapText(true);
        R1.setEditable(false);
        R2.setEditable(false);
        R3.setEditable(false);
        R4.setEditable(false);
        R1.setMaxSize(400,125);
        R2.setMaxSize(400,125);
        R3.setMaxSize(400,125);
        R4.setMaxSize(400,125);
        show.getChildren().addAll(R1,R2,R3,R4);
        return show;
    }
}
