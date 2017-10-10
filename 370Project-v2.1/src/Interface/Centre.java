package Interface;


import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Centre extends Pane{

    public TextArea R1 = new TextArea();
    public TextArea R2 = new TextArea();
    public TextArea R3 = new TextArea();
    public TextArea R4 = new TextArea();


    public Centre(){
        getChildren().add(restaurant());
    }

    public VBox restaurant() {
        VBox show = new VBox();

        resetAll();

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



        /*
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
        */



        show.getChildren().addAll(R1,R2,R3,R4);

        return show;
    }

    public void resetAll(){
        R1.setText("Restaurant 1");
        R2.setText("Restaurant 2");
        R3.setText("Restaurant 3");
        R4.setText("Restaurant 4");
    }








}
