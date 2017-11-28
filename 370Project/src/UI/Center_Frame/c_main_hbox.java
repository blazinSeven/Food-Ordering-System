package UI.Center_Frame;

import Search_Sort.*;
import UI.Account.userId;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**
 * Create By Hao Li at Oct. 22th
 * Modified by Yuecheng Rong
 */

public class c_main_hbox extends HBox {
    SortByWaitingTime sbt = new SortByWaitingTime();
    SortByRating sbr = new SortByRating();
    // for search use
    public TextField search_field = new TextField();
    // Label blank = new Label("                           ");
    //Button searchDish = new Button("Dish");
    Button searchRestaurant = new Button("Restaura");
    Button reset = new Button("New");

    CheckBox s_w = new CheckBox();
    CheckBox s_a = new CheckBox();
    CheckBox s_d = new CheckBox();
    CheckBox s_p = new CheckBox();


    //SearchDishes searchDishes = new SearchDishes();

    public lists res_lists = new lists();
    Button pre = new Button("Previous");
    Button next = new Button("Next");
    Label name = new Label("Restaurants");
    Button rate = new Button("Rate");
    Label address = new Label("Address");


    VBox restaurant_list;
    VBox rate_list;
    VBox address_list;
    public int page_number = 0;

    // These nodes will be called by other classes
    public ArrayList<ArrayList<String>> searchDishResult = new ArrayList<>();
    public ArrayList<ArrayList<String>> searchRestaurantResult = new ArrayList<>();
    public boolean SearchRestaurantBoolean = false;
    public boolean SearchDishBoolean = false;

    // main accessing for all main list
    public c_main_hbox(){

        sbt.sortByWaitingTime();
        sbr.sortByRate();
        setPrefSize(620,450);
        getChildren().addAll(res_name(),res_rate(),res_address());
        setPadding(new Insets(10,10,10,10));
        //setStyle("-fx-background-color: coral");
    }

    /**
     * Following three functions are the three vertical lists appear at the right of the main_frame
     * @return
     */
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
        rate_list = new VBox();
        rate_list.setPrefSize(140,450);

        rate.setPrefSize(140,30);
        rate.setAlignment(Pos.CENTER);
        rate.setStyle("-fx-background-color: steelblue");
        //rate_list.setStyle("-fx-background-color: aqua");

        rate_list.getChildren().addAll(rate);
        for (int i=0;i<res_lists.Rate.length;i++){
            if (res_lists.Rate[i]!=null && rate_list.getChildren().size()<5){
                rate_list.getChildren().add(res_lists.Rate[i]);
            }
        }
        return rate_list;
    }



    // main layout for restaurant address list
    private VBox res_address(){
        address_list = new VBox();
        address_list.setPrefSize(200,450);

        address.setPrefSize(200,30);
        address.setAlignment(Pos.CENTER);
        address.setStyle("-fx-background-color: steelblue");
       // address_list.setStyle("-fx-background-color: deepskyblue");

        address_list.getChildren().add(address);
        for (int i =0;i<res_lists.Address.length;i++){
            if(res_lists.Address[i]!=null && address_list.getChildren().size()<5){
                address_list.getChildren().add(res_lists.Address[i]);
            }
        }
        return address_list;
    }


    /**
     * Search Function
     *
     * @return
     */
    public VBox search(){
        VBox s_box = new VBox();
        setPrefSize(200,60);
        s_box.getChildren().addAll(search_field,s_holder());
        return s_box;
    }

    private HBox s_holder(){
        HBox holder = new HBox();
        //listener for search button and search field
        /**
         * Same thing can be done when ENTER is pressed
         */
        search_field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()==KeyCode.ENTER) {
                    // For simplicity, we assume the user logged in is user_id 1

                    // tell others that now the customer is searching restaurants
                    SearchDishBoolean = false;
                    SearchRestaurantBoolean = true;
                }
            }
        });
        searchRestaurant.setOnAction(e->{
            // For simplicity, we assume the user logged in is user_id 1

            // Change the appearance of the middle frame

            // tell others that now the customer is searching restaurants
            SearchDishBoolean = false;
            SearchRestaurantBoolean = true;
        });
        reset.setOnAction(e->{
            setLists();
            // Clear Rate and Address
            searchRestaurantResult.clear();
            searchDishResult.clear();
            search_field.clear();
            rate_list.getChildren().clear();
            address_list.getChildren().clear();
            set_button(0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            // tell others that now the customer is starting new search
            SearchDishBoolean = false;
            SearchRestaurantBoolean = false;
        });
        //reset.setPrefWidth(100);
        holder.getChildren().addAll(reset,searchRestaurant);
        return holder;
    }





    /**
     * Preview/Next Button
     *
     * @return
     */
    public HBox main_Button(){
        HBox button_box = new HBox();
        button_box.getStylesheets().add("css/page.css");
        set_button(0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        button_box.getChildren().addAll(pre,next);
        return button_box;
    }
    // Edited: input has been changed
    public void set_button(int execute, ArrayList<Integer> n, ArrayList<Integer> rate, ArrayList<Long> waiting_time,
                           ArrayList<ArrayList<String>> result){

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


    /**
     * Helper functions
     */

    private void setLists(){
        restaurant_list.getChildren().clear();
        rate_list.getChildren().clear();
        address_list.getChildren().clear();
        restaurant_list.getChildren().add(name);
        for (int i =page_number;i<res_lists.res_image.length;i++){
            if (res_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_lists.res_image[i]);
            }
        }
        address_list.getChildren().add(address);
        for (int i =page_number;i<res_lists.Address.length;i++){
            if(res_lists.Address[i]!=null && address_list.getChildren().size()<5){
                address_list.getChildren().add(res_lists.Address[i]);
            }
        }
        rate_list.getChildren().addAll(rate);
        for (int i=page_number;i<res_lists.Rate.length;i++){
            if (res_lists.Rate[i]!=null && rate_list.getChildren().size()<5){
                rate_list.getChildren().add(res_lists.Rate[i]);
            }
        }

    }

    public VBox setsort(){
        VBox sort_hold = new VBox();
        s_a.setText("Sort By Rate");
        s_d.setText("Sort By Distance");
        s_p.setText("Sort By Price");
        s_w.setText("Sort By Waiting Time");
        s_a.getStylesheets().add("css/checkbox.css");
        s_w.getStylesheets().add("css/checkbox.css");
        s_d.getStylesheets().add("css/checkbox.css");
        s_p.getStylesheets().add("css/checkbox.css");

        s_w.setOnAction(e->{
            if (s_w.isSelected()) {
                s_a.setSelected(false);
                s_p.setSelected(false);
                s_d.setSelected(false);
                res_lists.getaddress(sbt.sortByWaitingTimeAddress);
                res_lists.getRate(sbt.sortByWaitingTimeRate);
                res_lists.getRestaurant(sbt.sortByWaitingTimeRestId);
                setLists();
            }else {
                s_w.setSelected(false);
            }
        });

        s_a.setOnAction(e->{
            if (s_a.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                s_d.setSelected(false);
                res_lists.getaddress(sbr.sortByRateRestAddress);
                res_lists.getRate(sbr.sortByRateRestRate);
                res_lists.getRestaurant(sbr.sortByRateRestId);
                setLists();
            }
            else{
                s_a.setSelected(false);
            }

        });

        s_d.setOnAction(e->{
            if (s_d.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                s_a.setSelected(false);
                SortByDistance sbd = new SortByDistance(19);
                res_lists.getaddress(sbd.sortByDistanceAddress);
                res_lists.getRate(sbd.sortByDistanceRate);
                res_lists.getRestaurant(sbd.sortByDistanceId);
                setLists();

            }
            else {
                s_d.setSelected(false);
            }
        });

        //restaurant_list.getChildren().add(res_lists.res_image[n]);
        sort_hold.setPrefSize(100,70);
        sort_hold.getChildren().addAll(s_a,s_p,s_d,s_w);
        return sort_hold;
    }




}
