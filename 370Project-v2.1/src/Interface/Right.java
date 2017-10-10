package Interface;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import Account.*;


public class Right extends BorderPane {

    Button Quit = new Button();
    StackPane centre = new StackPane();
    public RadioButton rb1 = new RadioButton("Price");
    public RadioButton rb2 = new RadioButton("Rate");
    public Button Go = new Button("Go");
    public TextField search = new TextField();


    public Right(){
        setTop(top());
        setCenter(centre());
        setBottom(bottom());
    }


    private GridPane top(){
        GridPane set = new GridPane();
        //buttonSearch.setPrefSize(10,2);
        Label search_label = new Label("F.O.S ");
        Button sign_up = new Button("Sign up");
        Button Log_in = new Button("Log in");

        //Go.setOnAction(event -> new SearchFunction().searchFunction(search.getText()));
        Log_in.setOnAction(e->new Account().account());
        sign_up.setOnAction(e->new Registration().Reg());

        set.add(Go,2,0);
        set.add(search,1,0);
        set.add(search_label,0,0);
        set.add(sign_up,0,1);
        set.add(Log_in,1,1);


        return set;
    }


    private StackPane centre() {

        Label ad = new Label();
        ad.setText("This part will show some commercial advertisements to user");
        ad.setWrapText(true);



        centre.getChildren().add(ad);
        centre.setAlignment(Pos.CENTER);
        centre.setMaxSize(220,600);
        return centre;
    }



    public HBox bottom() {
        HBox bottom = new HBox();

        Quit.setText("      Quit        ");
        //Quit.setStyle("-fx-background-color: red");
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.getChildren().addAll(Quit);
        //bottom.setMaxSize(220,200);
        return bottom;
    }

    public void changeMiddle(){
        final ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);

        BorderPane RatioButtonPane = new BorderPane();
        FlowPane temp = new FlowPane();

        temp.getChildren().add(rb1);
        temp.getChildren().add(rb2);

        TitledPane TP = new TitledPane("Sort by",temp);
        TP.setText("Sort by");
        TP.setContent(temp);


        RatioButtonPane.setCenter(TP);

        //centre.getChildren().add(RatioButtonPane);
        centre.getChildren().setAll(RatioButtonPane);

    }


}
