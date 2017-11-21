package UI.Center_Frame;

import Search_Sort.SearchRestaurants;
import Search_Sort.SearchDish;
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

import java.util.ArrayList;

/**
 * Create By Hao Li at Oct. 22th
 * Modified by Yuecheng Rong
 */

public class c_main_hbox extends HBox {

    // for search use
    public TextField search_field = new TextField();
    public Label blank = new Label("                           ");
    Button searchDish = new Button("SD");
    Button searchRestaurant = new Button("SR");



    Button reset = new Button("Browse");



    SearchRestaurants searchRestaurants = new SearchRestaurants();
    SearchDish searchDishes = new SearchDish();

    public lists res_lists = new lists();
    Button pre = new Button("Previous");
    Button next = new Button("Next");
    public Label name = new Label("Restaurants");
    public VBox restaurant_list;
    int page_number = 0;

    // These nodes will be called by other classes
    public ArrayList<ArrayList<String>> searchDishResult = new ArrayList<>();
    public boolean SearchRestaurantBoolean = false;
    public boolean SearchDishBoolean = false;

    // main accessing for all main list
    public c_main_hbox(){
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
                    searchRestaurants.SearchRestaurant(search_field.getText());
                    if (searchRestaurants.message.equals("Found")){
                        setsearch(searchRestaurants.restaurant_id);
                    }
                }
            }
        });
        searchRestaurant.setOnAction(e->{
            searchRestaurants.SearchRestaurant(search_field.getText());
            if (searchRestaurants.message.equals("Found")){
                setsearch(searchRestaurants.restaurant_id);
            }
            // tell others that now the customer is searching restaurants
            SearchDishBoolean = false;
            SearchRestaurantBoolean = true;
        });
        searchDish.setOnAction(e->{
            searchDishes.Search(search_field.getText());
            setSortBySuperArray(searchDishes.finalResult);
            // Super array that contains all the information can be sorted
            searchDishResult = searchDishes.finalResult;
            // tell others that now the customer is searching dishes
            SearchDishBoolean = true;
            SearchRestaurantBoolean = false;
        });
        reset.setOnAction(e->{
            setLists();
            set_button(0, new ArrayList<>(), new ArrayList<>());
            // tell others that now the customer is starting new search
            SearchDishBoolean = false;
            SearchRestaurantBoolean = false;
        });
        reset.setPrefWidth(100);
        holder.getChildren().addAll(reset,searchDish,searchRestaurant);
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
        set_button(0, new ArrayList<>(), new ArrayList<>());
        button_box.getChildren().addAll(pre,next);
        return button_box;
    }
    // Edited: input has been changed
    public void set_button(int execute, ArrayList<Integer> n, ArrayList<ArrayList<String>> result){

        pre.setPrefSize(90,30);
        next.setPrefSize(90,30);
        pre.setOnAction(e->{
            if (page_number>=4){
                page_number=page_number-4;
                if (execute == 0){
                    setLists();
                } else if (execute == 1){
                    setSort(n);
                } else if (execute == 2){
                    setSortBySuperArray(result);
                }
            }
            else {
                System.out.println("pre");
            }
        });
        next.setOnAction(e->{
            if (page_number<8){
                page_number=page_number+4;
                if (execute == 0){
                    setLists();
                } else if (execute == 1){
                    setSort(n);
                } else if (execute == 2){
                    setSortBySuperArray(result);
                }
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

    /**
     * This function will change the order of the restaurant pictures showed in the centre of the list
     * by the sorted order
     *
     * @param n, an arraylist of integers that represent the sorted order of the pictures
     *
     * @postcondition n is not null
     */
    public void setSort(ArrayList<Integer> n){
        restaurant_list.getChildren().clear();
        restaurant_list.getChildren().add(name);
        for (int i = page_number;i<n.size();i++){
            // if the imagine exists and restaurant_list does not yet have 4 pictures
            if ((n.get(i) < res_lists.res_image.length) &&
                    (res_lists.res_image[n.get(i)] != null) && (restaurant_list.getChildren().size() < 5)) {
                restaurant_list.getChildren().add(res_lists.res_image[n.get(i)]);
            }
        }
    }

    /**
     * This function will change the order of the restaurant pictures showed in the centre of the list
     * by the sorted order.
     * This function is called when the input is dish
     *
     * @param result
     */
    public void setSortBySuperArray(ArrayList<ArrayList<String>> result){
        listOfPrice P = new listOfPrice();
        P.listOfThePrice(result);
        restaurant_list.getChildren().clear();
        restaurant_list.getChildren().add(name);
        for (int i = page_number;i<result.size();i++){
            // if restaurant_list does not yet have 4 pictures
            if (restaurant_list.getChildren().size() < 5) {
                restaurant_list.getChildren().add(P.lists[i]);
            }
        }
    }

}
