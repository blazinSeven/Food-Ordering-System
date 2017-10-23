package UI.Center_Frame;

import database.SearchRestaurants;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Create By Hao Li at Oct. 22th
 */

public class c_main_hbox extends HBox {

    // for search use
    public TextField search_field = new TextField();
    Label blank = new Label("                           ");
    public Button search = new Button("SEARCH");
    SearchRestaurants searchRestaurants = new SearchRestaurants();

    lists res_lists = new lists();
    Button pre = new Button("Previous");
    Button next = new Button("Next");
    Label name = new Label("Restaurants");
    VBox restaurant_list;
    int page_number = 0;
    // main accessing for all main list
    public c_main_hbox(){
        setPrefSize(620,450);
        getChildren().addAll(res_name(),res_rate(),res_address());
        setPadding(new Insets(10,10,10,10));
        //setStyle("-fx-background-color: coral");
    }


    // main layout for restaurant name list
    private VBox res_name(){
        restaurant_list = new VBox();
        restaurant_list.setPrefSize(260,450);
        name.setPrefSize(260,30);
        name.setAlignment(Pos.CENTER);
        //name.setStyle("-fx-background-color: steelblue");
       // restaurant_list.setStyle("-fx-background-color: brown");
        restaurant_list.getChildren().add(name);
        for (int i =page_number;i<res_lists.res_image.length;i++){
            if (res_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_lists.res_image[i]);
            }
        }

        return restaurant_list;
    }


    // main layout for restaurant rate list
    private VBox res_rate(){
        VBox restaurant_rate = new VBox();
        Label rate = new Label("Rate");
        restaurant_rate.setPrefSize(140,450);
        rate.setPrefSize(140,30);
        rate.setAlignment(Pos.CENTER);
        rate.setStyle("-fx-background-color: steelblue");
        restaurant_rate.setStyle("-fx-background-color: aqua");
        restaurant_rate.getChildren().addAll(rate);
        return restaurant_rate;
    }



    // main layout for restaurant address list
    private VBox res_address(){
        VBox restaurant_address = new VBox();
        Label address = new Label("Address");
        restaurant_address.setPrefSize(200,450);
        address.setPrefSize(200,30);
        address.setStyle("-fx-background-color: steelblue");
        address.setAlignment(Pos.CENTER);
        restaurant_address.setStyle("-fx-background-color: deepskyblue");
        restaurant_address.getChildren().addAll(address);
        return restaurant_address;
    }


    // search functions
    public VBox search(){
        VBox s_box = new VBox();
        setPrefSize(100,60);
        s_box.getChildren().addAll(search_field,s_holder());
        return s_box;
    }

    private HBox s_holder(){
        HBox holder = new HBox();

        //listener for search button and search field
        search_field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()==KeyCode.ENTER) {
                    searchRestaurants.SearchRestaurant(search_field.getText());
                    if (searchRestaurants.message.equals("Found")){
                        setsearch(searchRestaurants.k);
                    }
                }
            }
        });
        search.setOnAction(e->{
            searchRestaurants.SearchRestaurant(search_field.getText());
            if (searchRestaurants.message.equals("Found")){
                setsearch(searchRestaurants.k);
            }
        });

        holder.getChildren().addAll(blank,search);
        return holder;
    }








    public HBox main_Button(){
        HBox button_box = new HBox();
        button_box.getStylesheets().add("css/page.css");
        set_button();
        button_box.getChildren().addAll(pre,next);
        return button_box;
    }
    private void set_button(){

        pre.setPrefSize(90,30);
        next.setPrefSize(90,30);
        pre.setOnAction(e->{
            if (page_number>=4){
                page_number=page_number-4;
                setLists();
            }
            else {
                System.out.println("pre");
            }
        });
        next.setOnAction(e->{
            if (page_number<8){
                page_number=page_number+4;
                setLists();
            }
            else {
                System.out.println("next");
            }
        });

    }

    private void setLists(){
        restaurant_list.getChildren().clear();
        restaurant_list.getChildren().add(name);
        for (int i =page_number;i<res_lists.res_image.length;i++){
            if (res_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_lists.res_image[i]);
            }
        }
    }

    public void setsearch(int n){
        restaurant_list.getChildren().clear();
        restaurant_list.getChildren().add(name);
        restaurant_list.getChildren().add(res_lists.res_image[n]);
    }

}
