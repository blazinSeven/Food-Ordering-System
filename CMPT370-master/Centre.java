package sample;


import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        /*
        TextArea R2 = new TextArea();



        R2.setText("Restaurant 2");
        R2.setWrapText(true);
        R2.setEditable(false);
        R2.setMaxSize(400,125);
        */
        TextArea R3 = new TextArea();
        TextArea R4 = new TextArea();
        R3.setText("Restaurant 3");
        R4.setText("Restaurant 4");
        R1.setWrapText(true);

        R3.setWrapText(true);
        R4.setWrapText(true);
        R1.setEditable(false);

        R3.setEditable(false);
        R4.setEditable(false);
        R1.setMaxSize(400,125);

        R3.setMaxSize(400,125);
        R4.setMaxSize(400,125);


        //set up pic of Restaurants
        Image image_RL = new Image(Main.class.getResourceAsStream("RL.png"));
        ImageView view_RL = new ImageView();
        // test the pic allowed to get action
        view_RL.setOnMousePressed(e->System.out.println("haha"));

        Image image_kfc = new Image(Main.class.getResourceAsStream("kfc.png"));
        ImageView view_kfc = new ImageView();
        view_kfc.setFitHeight(125);
        view_kfc.setFitWidth(400);
        view_RL.setFitHeight(125);
        view_RL.setFitWidth(400);
        view_RL.setImage(image_RL);
        view_kfc.setImage(image_kfc);




        show.getChildren().addAll(R1,view_RL,R3,R4);

        return show;
    }
}
